package com.cmw.shejimoshi.proxy.jdkProxy;

import java.lang.reflect.Proxy;

/**
 * @autor:
 * @create:
 * @description: jdk动态代理
 */
public class Visitor {

    public static void main(String[] args) {

        GongNeng gongNeng = new LaoZong();
        MiShu miShu =new MiShu();
        GongNeng gongNeng1= (GongNeng) Proxy.newProxyInstance(Visitor.class.getClassLoader(),gongNeng.getClass().getInterfaces(),miShu);

         gongNeng.eat();
         gongNeng.talk();
    }



}
