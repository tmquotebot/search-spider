package com.github.tmquotebot.searchspider.controller;

import com.github.tmquotebot.searchspider.dto.QuoteDto;
import com.github.tmquotebot.searchspider.dto.QuoteFilter;

import javax.validation.Valid;
import java.util.List;


public interface QuoteController {


    List<QuoteDto> getQuotesByFilter(@Valid QuoteFilter filter);
}
