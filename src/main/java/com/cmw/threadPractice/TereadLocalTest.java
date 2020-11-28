package com.cmw.threadPractice;

/**
 * @autor: Mr.Chen
 * @create:
 * @description: 测试ThreadLocal
 */
public class TereadLocalTest {
    /**
     * ThreadLocal 线程容器 给线程绑定一个object内容后 只要线程不变，可以随时取出
     *  但是只要线程改变 则无法取出内容
     *
     * */
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal =new ThreadLocal<>();
        threadLocal.set("测试");

        new Thread(){
            @Override
            public void run() {
                String result= threadLocal.get();
                System.out.println("结果:"+result); //结果:null
            }
        }.start();

        String result= threadLocal.get();
        System.out.println("结果:"+result);//结果:测试

    }
}
