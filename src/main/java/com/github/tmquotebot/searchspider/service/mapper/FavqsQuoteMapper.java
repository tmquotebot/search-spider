package com.github.tmquotebot.searchspider.service.mapper;

import com.github.tmquotebot.searchspider.client.model.QuoteModel;
import com.github.tmquotebot.searchspider.dto.QuoteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FavqsQuoteMapper {

    @Mapping(source = "body", target = "quote")
    @Mapping(source = "tags", target = "tags")
    QuoteDto favqsToDto(QuoteModel model);

}
