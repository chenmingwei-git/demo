package com.cmw.demo;

import com.cmw.beans.Cat;
import com.cmw.concurrent.demo2.Demo;
import com.cmw.springaop.MyAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;
import java.util.Map;

/**
 * @autor:
 * @create:
 * @description:  @SpringBootApplication 只会扫描其本包 和子包  cat  在beans包下  所以需要加入扫描路径
 *                才会被纳入spring容器
 */

@SpringBootApplication
@ComponentScan("com.cmw")
public class DemoApplication {

 /*   @Bean(name ="myAspect")
    public MyAspect initMyAspect(){

        return new MyAspect();
    }*/

    public static void main(String[] args) {
    //    ConfigurableApplicationContext  ctx= SpringApplication.run(DemoApplication.class, args);
//
//        Cat cat =ctx.getBean(Cat.class);
//
//        System.out.println(cat);

        SpringApplication.run(DemoApplication.class,args);

    }
}
