package com.cmw.threadPractice;

/**
 * @autor:
 * @create:
 * @description: volatile 关键字
 */
public class VolatileOneDemo {
    private volatile static boolean stop= false;

    public static void main(String[] args) throws InterruptedException{
        new Thread(()->{
            int i=0;
            while (!stop){
                i++;
                System.out.println("i的值："+i);
            }

        }).start();

        Thread.sleep(1000);

        System.out.println("执行");
        stop=true;
    }

}
