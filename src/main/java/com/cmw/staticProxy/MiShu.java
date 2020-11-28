package com.cmw.staticProxy;

/**

 */
/**
 * @autor:
 * @create:
 * @description: 静态代理
 *                 特点：
 *                     1. 目标对象必须要实现接口
 *                     2. 代理对象，要实现与目标对象一样的接口
 */
public class MiShu implements GongNeng{
    private LaoZong laoZong =new LaoZong();

    @Override
    public void talk() {
        System.out.println("请问您有预约么？");
        laoZong.talk();
        System.out.println("老总让我送送您");
    }

    @Override
    public void eat() {
        System.out.println("你是来吃饭的么");
        laoZong.eat();
        System.out.println("吃好慢走");
    }



}
