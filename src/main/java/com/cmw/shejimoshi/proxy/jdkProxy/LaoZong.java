package com.cmw.shejimoshi.proxy.jdkProxy;

/**
 * @autor:
 * @create:
 * @description:  真实要访问的对象
 */

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

