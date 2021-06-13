package com.github.tmquotebot.searchspider.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuoteDto {

    private String quote;
    private List<String> tags;
}


