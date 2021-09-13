package com.cmw.demo;

import org.springframework.stereotype.Service;

import static com.cmw.demo.FundHandleFactory.AUTH_BEAN;

/**
 * @description:
 * @author: cmw
 * @date: 2021/9/13
 */
@Service
public class AuthTest1 implements FundHandleServer{
    @Override
    public Object todo(String object) throws Exception {
        System.out.println("AuthTest1:"+object);
        return null;
    }

    @Override
    public boolean test(String s) {
        return s.equals(AUTH_BEAN);
    }

    @Override
    public String get() {
        return "Test1";
    }
}
