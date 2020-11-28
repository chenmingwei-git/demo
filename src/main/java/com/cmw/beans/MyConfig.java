package com.cmw.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @autor:
 * @create:
 * @description:
 */

@Configuration
public class MyConfig {

    @Bean
    public Cat cat(){
     return new Cat();
    }
}
