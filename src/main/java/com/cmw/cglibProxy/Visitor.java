package com.cmw.cglibProxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * @autor:
 * @create:
 * @description: cglib动态代理
 */
public class Visitor {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(LaoZong.class);
        enhancer.setCallback(new MiShu());

        LaoZong laoZong =(LaoZong)enhancer.create();
        laoZong.eat();
    }
}
