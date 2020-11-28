package com.cmw.threadPractice;

/**
 * @autor:
 * @create:
 * @description:
 */
public class ThreadWait extends Thread{
    private Object lock;

    public ThreadWait(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock){
            System.out.println("开始进入ThreadWait方法");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("结束ThreadWait方法");
        }

    }
}
