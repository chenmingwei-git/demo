package com.cmw.concurrent.demo2;

/**
 * @autor:
 * @create:
 * @description: 同步方法和非同步方法可以同时调用
 */
public class Demo2 {

    public synchronized void test1(){
        System.out.println(Thread.currentThread().getName()+"。test1().start");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"。test1().end");
    }

    public void test2(){
        System.out.println(Thread.currentThread().getName()+"。test2().start");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"。test2().end");
    }

    public static void main(String[] args) {

        Demo2 demo2 =new Demo2();

        new Thread(demo2::test1,"test1").start();
        new Thread(demo2::test2,"test2").start();

    }
}
