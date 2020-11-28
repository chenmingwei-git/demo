package com.cmw.threadPractice;

/**
 * @autor:
 * @create:
 * @description:
 */
public class ThreadNotify extends Thread{
    private Object lock;

    public ThreadNotify(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock){
            System.out.println("开始进入ThreadNotify方法");
                lock.notify();
            System.out.println("结束ThreadNotify方法");
        }

    }
}
