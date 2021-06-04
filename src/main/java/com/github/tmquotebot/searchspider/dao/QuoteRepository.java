package com.github.tmquotebot.searchspider.dao;

import com.github.tmquotebot.searchspider.dao.entity.Quote;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends CassandraRepository<Quote, String> {

}
