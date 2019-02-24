package com.cheung.emall;

// import com.cheung.emall.util.PortUtil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//  @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan

@EnableCaching  //  启动缓存
@SpringBootApplication
public class BootApplication {
    // static{
    //     PortUtil.checkPort(6379,"Redis服务器",true);
    // }
    public static void main(String[] args) {
        //  没有该类的引用实例时，使用.class获取类运行时的有关信息
        //  有该类的引用实例时，使用 getClass()
        SpringApplication.run(BootApplication.class,args);
    }
}
