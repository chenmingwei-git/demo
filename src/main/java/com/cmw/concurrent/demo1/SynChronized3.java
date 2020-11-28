package com.cmw.concurrent.demo1;

/**
 * @autor:
 * @create:
 * @description:
 */
public class SynChronized3 {

    private int count= 10;


    //相当于synchronized(this)
    public synchronized void test(){

            count--;
            System.out.println(Thread.currentThread().getName()+"count = "+count);

    }
}
