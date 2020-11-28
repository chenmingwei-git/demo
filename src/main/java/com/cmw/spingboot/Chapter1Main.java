package com.cmw.spingboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @autor: Mr.Chen
 * @create: 2018/11/21
 * @description: 测试springboot
 */
@RestController
@RequestMapping("/test")
//@EnableAutoConfiguration  //启用Spring boot自动装配
public class Chapter1Main {

    @RequestMapping("/er")
    public Map<String,String> test(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("key","value");
        return map;
    }

    @RequestMapping("/test1")
    public Map<String,String> test1(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("hello","world");
        return map;
    }



//    public static void main(String[] args) {
//        SpringApplication.run(Chapter1Main.class,args);
//    }
}
