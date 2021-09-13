package com.cmw.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: cmw
 * @date: 2021/9/13
 */
@Component
public class Pricate {

    void toTest(String s){
        try {
            FundHandleFactory.authHandleMap.get(s).todo("你好");
        } catch (Exception e) {
            System.out.println("存在异常");
        }
    }

    void toTest2(String s){
        try {
            FundHandleFactory.authQueryHandleMap.get(s).todo("你好");
        } catch (Exception e) {
            System.out.println("存在异常");
        }
    }

    public static void main(String[] args) {
        ApplicationContext s = new AnnotationConfigApplicationContext("com.cmw.demo");
        Pricate p = (Pricate)s.getBean(Pricate.class);

//        p.toTest("Test1");
//        p.toTest("Test2");
//        p.toTest2("QueryTest1");
        p.toTest2("QueryTest2");

    }
}
