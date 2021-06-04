package com.github.tmquotebot.searchspider.client;

import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tmquotebot.searchspider.client.model.FiltersModel;
import com.github.tmquotebot.searchspider.exception.WebException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class FavqsClient {

    private final RestTemplate restTemplate;

    private final String baseUrl;

    public FavqsClient(RestTemplate restTemplate,
            @Value("${client.favqs.base.url}") String baseUrl,
            @Value("${client.favqs.api.key}") String apiKey,
            ObjectMapper objectMapper)
    {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;

        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        interceptors.add((request, body, execution) -> {
            ClientHttpResponse resp = execution.execute(request, body);
            String respBody = IOUtils.toString(resp.getBody(), StandardCharsets.UTF_8);
            JsonNode parsed = objectMapper.readTree(respBody);
            if (parsed.has("error_code")) {
                throw new WebException(parsed.asText("message"), HttpStatus.BAD_REQUEST);
            }
            return resp;
        });
        interceptors.add((request, body, execution) -> {
            request.getHeaders().add(HttpHeaders.AUTHORIZATION, String.format("Token token=\"%s\"", apiKey));
            return execution.execute(request, body);
        });
        restTemplate.setInterceptors(interceptors);
    }

    public FiltersModel getFilters() {
        UriBuilder builder = getBuilder();
        MultiValueMap<String, String> headers = new HttpHeaders();
        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, builder.build());
        return restTemplate.exchange(requestEntity, FiltersModel.class).getBody();
    }


    private UriBuilder getBuilder() {
        return UriComponentsBuilder.fromUriString(baseUrl);
    }


}
