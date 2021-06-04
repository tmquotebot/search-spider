package com.github.tmquotebot.searchspider.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;

@Table
@Data
@AllArgsConstructor
public class Quote {

    @PrimaryKey
    private final String id;

    private final String quote;

    private List<String> tags;
}
