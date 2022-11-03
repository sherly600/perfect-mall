package com.pmall.search.repository;

import com.pmall.search.entity.ItemDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface ProductRepository extends ElasticsearchRepository<ItemDocument, Integer> {
}
