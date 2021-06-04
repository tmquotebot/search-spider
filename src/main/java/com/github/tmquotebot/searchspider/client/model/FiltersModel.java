package com.github.tmquotebot.searchspider.client.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiltersModel {

    private List<FilterType> authors;
    private List<FilterType> tags;

}
