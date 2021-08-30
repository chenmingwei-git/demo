package com.cmw.shejimoshi.factory;

/**
 * @autor:
 * @create:
 * @description:
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("购买中国苹果口味面包");
        BreadStoreFactory chinaBreadStoreFactory = new ChinaStore();
        chinaBreadStoreFactory.orderBread("apple");

    }
}
