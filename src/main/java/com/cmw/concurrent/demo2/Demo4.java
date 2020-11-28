package com.cmw.concurrent.demo2;

/**
 * @autor:
 * @create:
 * @description: 一个同步方法调用另一个同步方法可以得到锁,
 *               因为synchronized本身支持可重入锁
 */
public class Demo4 {

    public  synchronized void test1(){
        System.out.println("test1.start");

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        test2();
    }

    public synchronized void test2(){

        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("test2.start..");
    }

    public static void main(String[] args) {
        Demo4 demo4 = new Demo4();
        demo4.test1();
    }
}
