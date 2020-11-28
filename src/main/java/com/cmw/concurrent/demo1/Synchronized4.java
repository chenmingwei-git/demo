package com.cmw.concurrent.demo1;

/**
 * @autor:
 * @create:
 * @description:
 */
public class Synchronized4 {

    private static int count= 10;


    //synchronized用于静态方式的时候锁住的是 xxx.class
    public synchronized static void test(){

        count--;
        System.out.println(Thread.currentThread().getName()+"count = "+count);

    }

    public static void test2(){

        synchronized (Synchronized4.class){
            /**此处不能够锁this,因为this是锁放在堆里的对象 而静态方法只依赖于类
             * 此处这种锁发是锁的通过反射加载的该类的字节码对象
             */
            count--;
        }
    }
}
