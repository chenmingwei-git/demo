package com.cmw.demo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description: 组装bean的工厂类
 * @author: cmw
 * @date: 2021/9/13
 */
@Service("fundHandleFactory")
public class FundHandleFactory implements InitializingBean {
    @Autowired
    ApplicationContext applicationContext;

    public static final String AUTH_BEAN = "AUTH_BEAN";
    public static final String AUTH_BEAN_QUERY = "AUTH_BEAN_QUERY";

    public static  Map<String,FundHandleServer> authHandleMap;

    public static  Map<String,FundHandleServer> authQueryHandleMap;
    @Override
    public void afterPropertiesSet() throws Exception {

        Map<String,FundHandleServer> beandOfTYpe = applicationContext.getBeansOfType(FundHandleServer.class);

        authHandleMap = beandOfTYpe.values().stream().filter(bean->bean.test(AUTH_BEAN)).
                collect(Collectors.toMap(t->t.get(), Function.identity()));

        authQueryHandleMap = beandOfTYpe.values().stream().filter(bean->bean.test(AUTH_BEAN_QUERY)).
                collect(Collectors.toMap(t->t.get(), Function.identity()));

    }
}
