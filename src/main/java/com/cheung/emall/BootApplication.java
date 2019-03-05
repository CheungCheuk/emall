package com.cheung.emall;

// import com.cheung.emall.util.PortUtil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
// import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//  @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan

// @EnableCaching  //  启动缓存
@SpringBootApplication
@EnableJpaRepositories( basePackages = {"com.chueng.emall.dao", "com.cheung.emall.pojo"})
@EnableElasticsearchRepositories( basePackages = "com.chueng.emall.es")
public class BootApplication {
    public static void main(String[] args) {
        //  没有该类的引用实例时，使用.class获取类运行时的有关信息
        //  有该类的引用实例时，使用 getClass()
        SpringApplication.run(BootApplication.class,args);
    }
}
