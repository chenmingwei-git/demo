package com.cmw.reflex;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @描述 反射的相关demo
 * @author: ChenMingWei
 * @create: 2020-08-31 10:55
 */
public class Reflection {
    public static void main(String[] args) {

     //   Class c  = Test1.class;//反射获取类
        Class c  = null;//反射获取类
        try {
            c = Class.forName("com.cmw.reflex.Test1");//反射获取类
         //   Test1 t = (Test1) c.newInstance();//创建对象
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Field[] fields = c.getDeclaredFields();//获得某个类的所有声明的字段，即包括public、private和proteced，但是不包括父类的申明字段
        Field[] fields = c.getFields();//获取类的成员变量(只获取public修饰的包括父类中的字段 )
        for (Field f:fields){
            System.out.println(f.toString());
        }

       // Method[] method=c.getDeclaredMethods();//该方法是获取本类中的所有方法，包括私有的(private、protected、默认以及public)的方法
        Method[] method=c.getMethods();//获取本类以及父类或者父接口中所有的公共方法(public修饰符修饰的)
        for(Method m:method){
            System.out.println(m.toString());
        }


        Constructor[] constructor=c.getDeclaredConstructors();//所有的构造函数
       // Constructor[] constructor=c.getConstructors();//公共构造函数
        for(Constructor co:constructor){
            System.out.println(co.toString());
        }
    }
}
