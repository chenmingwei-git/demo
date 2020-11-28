package com.cmw.factory;

/**
 * @autor: Mr.Chen
 * @create: 2018/11/19
 * @description: 中国店铺子类
 */
public class ChinaStore extends BreadStoreFactory{
    @Override
    BreadFactory createBread(String type) {
        BreadFactory breadFactory=null;
        if("cream".equalsIgnoreCase(type)){
            System.out.println("创建中国奶油口味面包");
            breadFactory=new ChinaCreamBread();
        }else if("apple".equalsIgnoreCase(type)){
            System.out.println("创建中国苹果口味面包");
            breadFactory=new ChinaAppleBread();
        }else {
            System.out.println("无法确认的面包类型");
            return null;
        }

        return breadFactory;
    }
}
