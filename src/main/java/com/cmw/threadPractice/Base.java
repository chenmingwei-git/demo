package com.cmw.threadPractice;

/**
 * @autor:
 * @create:
 * @description:
 */
public class Base {

    private   String baseName="base";

    public Base(){

        callName();
    }

    public void callName(){

        System.out.println(baseName);
    }

    static class Sub extends Base{

        private  String baseName="sub";



        public void callName(){
            System.out.println(baseName);

        }

    }

    public static void main(String[] args) {
        Base b =new Sub();
        /**
         *
         * new Sub()时 首先走的父类的无参构造，此时父类的callName方法因为被子类重写 所以父类的callName方法被隐藏 此时指向的
         * 的是子类的callName方法  当子类的callName方法被调用时  此时子类的成员变量baseName 在jvm中指向为null尚未被实例化
         * 所以输出的是null
         *
         * */
    }

}



