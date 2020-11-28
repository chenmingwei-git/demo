package com.cmw.threadPractice;

/**
 * @autor:
 * @create:
 * @description:
 */
public class TestWaitAndNotify {
    public static void main(String[] args) {
        Object obc= new Object();
    ThreadWait threadWait =new ThreadWait(obc);
    ThreadNotify threadNotify = new ThreadNotify(obc);
        threadWait.start();
        threadNotify.start();
}
}
