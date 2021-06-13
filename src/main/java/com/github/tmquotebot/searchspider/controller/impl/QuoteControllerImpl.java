package com.github.tmquotebot.searchspider.controller.impl;

import com.github.tmquotebot.searchspider.controller.QuoteController;
import com.github.tmquotebot.searchspider.dto.QuoteDto;
import com.github.tmquotebot.searchspider.dto.QuoteFilter;
import com.github.tmquotebot.searchspider.service.QuoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class QuoteControllerImpl implements QuoteController {

    private final QuoteService quoteService;

    @Override
    @GetMapping("quote")
    public List<QuoteDto> getQuotesByFilter(@Valid QuoteFilter filter) {
        log.info("filter {}", filter);
        return quoteService.getQuotesByFilter(filter);
    }
}
