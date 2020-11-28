package com.cmw.concurrent.demo1;

/**
 * @autor:
 * @create:
 * @description: SynChronized关键字测试
 */
public class SynChronized {

    private int count= 10;

    private Object object = new Object();

    public void test(){

        synchronized (object){
            count--;
            System.out.println(Thread.currentThread().getName()+"count = "+count);
        }
    }


}
