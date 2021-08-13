package com.cmw.example;


/**
 * @description:  单例
 * @author: cmw
 * @data: 2021/8/11
 */
//饿汉模式 线程安全的
public class Single {
    private static Single o= new Single();
    private Single(){}
    public static Single getInstance(){
        return o;
    }


}

//懒汉模式
class SingletonDemo {
    private static SingletonDemo instance;
    private SingletonDemo(){

    }
    public static  SingletonDemo getInstance(){
        if(instance==null){
            synchronized (SingletonDemo.class){
                if(instance==null){
                    instance=new SingletonDemo();
                }
            }

        }
        return instance;
    }
}

//内部类
class Singleton {
    private static class SingletonHolder{
        private static Singleton instance=new Singleton();
    }
    private Singleton(){
        System.out.println("Singleton has loaded");
    }
    public static Singleton getInstance(){
        return SingletonHolder.instance;
    }
}




