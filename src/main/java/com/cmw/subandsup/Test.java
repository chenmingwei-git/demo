package com.cmw.subandsup;

/**
 * @autor: cmw
 * @create: 19/3/19
 * @description: 父子类的初始化调用
 */

// 子类引用父类的静态变量 不会导致子类的初始化

// 初始化某个类的子类。当初始化子类的时候，该子类的所有父类都会被初始化。

// 用final修饰某个类变量时，它的值在编译时就已经确定好放入常量池了，
// 所以在访问该类变量时，等于直接从常量池中获取，并没有初始化该类。
public class Test {
    public static void main(String[] args) {
        System.out.println(Subclass.b);

        System.out.println(ContClass.c);//输出结果只有1 原因：引用常量时,不会触发该类的初始化


    }
}

class SupClass{
   public static int a=1;

   static {
       System.out.println("SupClass init");
   }
}

class Subclass extends SupClass{
    public static int b=1;

    static {
        System.out.println("SubClass init");
    }
}

class ContClass{

    public static final int c=1;

    static {
        System.out.println("ContClass init");
    }

}
