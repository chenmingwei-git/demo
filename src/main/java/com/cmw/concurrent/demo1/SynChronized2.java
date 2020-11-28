package com.cmw.concurrent.demo1;

/**
 * @autor:
 * @create:
 * @description:
 */
public class SynChronized2 {
    private int count= 10;


    public void test(){

        synchronized (this){
            count--;
            System.out.println(Thread.currentThread().getName()+"count = "+count);
        }
    }
}
