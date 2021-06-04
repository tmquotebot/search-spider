package com.github.tmquotebot.searchspider.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterType {

    private Integer count;
    private String permalink;
    private String name;

}
