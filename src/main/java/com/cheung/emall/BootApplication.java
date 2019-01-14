package com.cheung.emall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//  @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan


@SpringBootApplication
public class BootApplication {
    public static void main(String[] args) {
        //  没有该类的引用实例时，使用.class获取类运行时的有关信息
        //  有该类的引用实例时，使用 getClass()
        SpringApplication.run(BootApplication.class,args);
    }
}
