package com.github.tmquotebot.searchspider.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@FavqsModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiltersModel {

    private List<FilterType> authors;
    private List<FilterType> tags;
    private String filter;
    private int page;

}
