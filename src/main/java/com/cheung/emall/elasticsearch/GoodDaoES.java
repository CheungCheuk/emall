package com.cheung.emall.elasticsearch;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.elasticsearch.client.RestHighLevelClient;

/**
* 
*/
public class GoodDaoES { 
    private final String INDEX = "gooddata";    //..index 必须是小写，不支持驼峰格式
    private final String TYPE = "goods";
    private RestHighLevelClient restHighLevelClient;
    private ObjectMapper objectMapper;


    public GoodDaoES(ObjectMapper objectMapper, RestHighLevelClient restHighLevelClient) {
        this.objectMapper = objectMapper;
        this.restHighLevelClient = restHighLevelClient;
    }

    /**
     * 插入
     */
    
    
}