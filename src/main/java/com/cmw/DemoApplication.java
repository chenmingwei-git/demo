package com.cmw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @autor:
 * @create:
 * @description:  @SpringBootApplication 只会扫描其本包 和子包  cat  在beans包下  所以需要加入扫描路径
 *                才会被纳入spring容器
 */

@SpringBootApplication
@MapperScan(basePackages = {"com.cmw.mybatis.mapper"})
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
