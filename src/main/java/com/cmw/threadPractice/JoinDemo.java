package com.cmw.threadPractice;

/**
 * @autor:
 * @create:
 * @description:
 */
public class JoinDemo implements Runnable{

    private static int count=1;


    @Override
    public  void run() {
        for(int i=0;i<5;i++){
            System.out.println(Thread.currentThread().getName()+":"+count++);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new JoinDemo(),"t1").start();
        new Thread(new JoinDemo(),"t2").start();
    }
}
