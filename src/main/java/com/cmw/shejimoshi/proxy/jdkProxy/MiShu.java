package com.cmw.shejimoshi.proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @autor:
 * @create:
 * @description: jdk动态代理
 */
public class MiShu implements InvocationHandler {
    private LaoZong laoZong = new LaoZong();



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        Object result = method.invoke(laoZong,args);


        return result;
    }
}
