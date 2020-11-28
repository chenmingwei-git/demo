package com.cmw.threadPractice;

/**
 * @autor: Mr.Chen
 * @create:
 * @description: 多线程练习
 */
public class ThreadLing {
    public static void main(String[] args) {
        Thread t =new Thread1();
        Thread r =new Thread(new Runnable1());
        t.start();
        r.start();
    }
}

class Thread1 extends Thread{
    public void run(){
        for(int i=0;i<50;i++){
            System.out.println("线程1");
        }
    }
}

class Runnable1 implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<50;i++){
            System.out.println("线程2");
        }
    }
}

