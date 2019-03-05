package com.cheung.emall.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Configuration;

/**
 * ElasticSearchConfig
 */
@Configuration
public class ElasticSearchConfig extends AbstractFactoryBean{

    private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchConfig.class);

    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clusterName;

    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNodes;

    private RestHighLevelClient restHighLevelClient;

    @Override
    public void destroy() throws Exception {
        try{
            if ( restHighLevelClient != null ){
                restHighLevelClient.close();
            }
        }catch( final Exception e){
            LOG.error("Error closing ElasticSearch client", e);
        }
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public Class<RestHighLevelClient> getObjectType() {
        return RestHighLevelClient.class;
    }

    @Override
    protected RestHighLevelClient createInstance() throws Exception {
        return buildClient();
    }

    private RestHighLevelClient buildClient(){
        try{
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(
                new HttpHost("localhost", 9200, "http"),
                new HttpHost("localhost", 9201, "http")
            ));
        }catch(Exception e){
            LOG.error(e.getMessage());
        }
        return restHighLevelClient;
    }
    
}