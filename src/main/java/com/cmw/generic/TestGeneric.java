package com.cmw.generic;

/**
 * @description: 泛型的练习
 * @author: cmw
 * @date: 2021/10/27
 */


/**
 * java中通配符
 *
 * ？表示不确定的 java 类型
 * T (type) 表示具体的一个java类型
 *
 * K V (key value) 分别代表java键值中的Key Value
 * E (element) 代表Element
 */
public class TestGeneric {


    public static <T> T createInstance(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        return tClass.newInstance();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        A a = createInstance(A.class);
        B b = createInstance(B.class);
    }

}

class A{}

class B{}
