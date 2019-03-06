package com.cheung.emall;

// import com.cheung.emall.util.PortUtil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//  @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan

// @EnableCaching  //  启动缓存
@SpringBootApplication
@EnableElasticsearchRepositories( basePackages = "com.cheung.emall.es")
@EnableJpaRepositories( basePackages = {"com.cheung.emall.dao", "com.cheung.emall.pojo"})
public class BootApplication {
    public static void main(String[] args) {
        //  没有该类的引用实例时，使用.class获取类运行时的有关信息
        //  有该类的引用实例时，使用 getClass()
        SpringApplication.run(BootApplication.class,args);
    }
}
