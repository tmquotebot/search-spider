package com.github.tmquotebot.searchspider.client;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.github.tmquotebot.searchspider.client.model.FavqsModel;
import com.github.tmquotebot.searchspider.client.model.FavqsPagination;
import com.github.tmquotebot.searchspider.client.model.FiltersModel;
import com.github.tmquotebot.searchspider.dto.QuoteFilter;
import lombok.extern.slf4j.Slf4j;
import org.atteo.classindex.ClassIndex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
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

        ObjectMapper mapper = objectMapper.copy();


        Iterable<Class<?>> favqsModels = ClassIndex.getAnnotated(FavqsModel.class);
        String classNames = StreamSupport.stream(favqsModels.spliterator(), false)
                .map(Class::getSimpleName)
                .collect(Collectors.joining(",", "(", ")`"));
        log.debug("Favqs supported deserializing classes {}", classNames);
        Map<Class<?>, JsonDeserializer<?>> collect = StreamSupport.stream(favqsModels.spliterator(), false)
                .collect(Collectors.toMap(c -> c, c -> new ErrorCodeDeserializer<>(c, mapper), (a, b) -> a));

        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.deserializersByType(collect);
        builder.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter(builder.build());
        restTemplate.setMessageConverters(List.of(messageConverter));

        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();

        interceptors.add((request, body, execution) -> {
            request.getHeaders().add(HttpHeaders.AUTHORIZATION, String.format("Token token=%s", apiKey));
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

    public FavqsPagination getQuotes(QuoteFilter quoteFilter) {
        UriBuilder builder = getBuilder();
        builder.path("quotes");
        if (!CollectionUtils.isEmpty(quoteFilter.getTags())) {
            quoteFilter.getTags().forEach(tag -> builder.queryParam("filter", tag));
            builder.queryParam("type", "tag");
        }
        return restTemplate.getForObject(builder.build(), FavqsPagination.class);
    }


    private UriBuilder getBuilder() {
        return UriComponentsBuilder.fromUriString(baseUrl);
    }


}
