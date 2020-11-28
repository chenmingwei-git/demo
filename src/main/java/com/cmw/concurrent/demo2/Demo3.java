package com.cmw.concurrent.demo2;

import java.util.concurrent.TimeUnit;



/**
 * @autor:
 * @create:
 * @description: 测试脏读
 */
public class Demo3 {

    String name;
    double balance;

    public synchronized void set(String name,double balance){

        this.name =name;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.balance =balance;

    }

    public  double getBalance(String name){ //读取没加锁会造成脏读的数据
        return this.balance;
    }


    public static void main(String[] args) {
        Demo3 demo3 = new Demo3();

        new Thread(()->demo3.set("cmw",100.00)).start();


        try {
            TimeUnit.SECONDS.sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(demo3.getBalance("cmw"));//0.0 脏读


        try {
            TimeUnit.SECONDS.sleep(2);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(demo3.getBalance("cmw"));//100.0
    }
}
