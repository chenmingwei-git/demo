package com.cmw.pojo;

/**
 * @描述
 * @author: ChenMingWei
 * @create: 2020-07-19 13:26
 */
public class Person {
    public static void main(String[] args) {
        Person person = new Person();
        person.work();

    }

    public  int work(){
        int x =1;
        int y =2;
        int z =(x+y)*10;
        return z;
    }
}
