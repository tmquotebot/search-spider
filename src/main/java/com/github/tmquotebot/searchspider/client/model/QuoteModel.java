package com.github.tmquotebot.searchspider.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuoteModel {

    private List<String> tags;
    @JsonProperty("author_permalink")
    private String authorLink;
    private String body;
    private String author;

}
