package com.cmw.concurrent.demo2;

/**
 * @autor:
 * @create:
 * @description:
 */
public class Demo implements Runnable{

    private volatile int count = 10 ;

    @Override
    public  void run() {

        count--;

        System.out.println(Thread.currentThread().getName()+" count = "+count);
    }

    public static void main(String[] args) {

        Demo demo= new Demo();

        for(int i = 0; i < 5; i++){
            new Thread(demo,"Thread"+i).start();
        }
    }


}
