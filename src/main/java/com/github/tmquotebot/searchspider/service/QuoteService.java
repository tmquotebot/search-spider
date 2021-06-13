package com.github.tmquotebot.searchspider.service;

import com.github.tmquotebot.searchspider.client.FavqsClient;
import com.github.tmquotebot.searchspider.client.model.FavqsPagination;
import com.github.tmquotebot.searchspider.dto.QuoteDto;
import com.github.tmquotebot.searchspider.dto.QuoteFilter;
import com.github.tmquotebot.searchspider.service.mapper.FavqsQuoteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuoteService {

    private final FavqsClient favqsClient;
    private final FavqsQuoteMapper mapper;

    public List<QuoteDto> getQuotesByFilter(QuoteFilter quoteFilter){
        FavqsPagination quotes = favqsClient.getQuotes(quoteFilter);
        log.info("response {}", quotes);
        return quotes.getQuotes().stream().map(mapper::favqsToDto).collect(Collectors.toList());
    }

}
