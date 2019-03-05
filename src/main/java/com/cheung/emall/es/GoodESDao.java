package com.cheung.emall.es;

import com.cheung.emall.pojo.Good;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
* 
*/
public interface GoodESDao extends ElasticsearchRepository<Good, Integer>{
    
}