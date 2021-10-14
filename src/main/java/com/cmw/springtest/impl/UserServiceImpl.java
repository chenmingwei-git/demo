package com.cmw.springtest.impl;

import com.cmw.springtest.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: cmw
 * @date: 2021/10/14
 */
@Component
public class UserServiceImpl implements UserService {
    @Override
    public void test() {
        System.out.println("test()");
    }
}
