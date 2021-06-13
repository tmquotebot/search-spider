package com.github.tmquotebot.searchspider.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@FavqsModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavqsPagination {

    private long page;
    private boolean lastPage;
    private List<QuoteModel> quotes;

}
