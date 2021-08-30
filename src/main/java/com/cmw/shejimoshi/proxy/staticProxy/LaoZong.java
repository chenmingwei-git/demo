package com.cmw.shejimoshi.proxy.staticProxy;

import org.aspectj.lang.annotation.Aspect;

/**
 * @autor:
 * @create:
 * @description: 静态代理: 真实要访问的目标对象
 */

@Aspect
public class LaoZong implements GongNeng{

    @Override
     public void talk(){
         System.out.println("把酒言欢");
     }

    @Override
    public void eat() {
        System.out.println("吃饭");
    }
}

