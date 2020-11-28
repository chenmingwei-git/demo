package com.cmw.springaop;

/**
 * @autor: cmw
 * @create:
 * @description: JDK动态代理对象代理了UserServiceImpl 该方法是连接点
 */
public interface UserService {
    public void printUser(User user);
}
