package com.cmw.factory;

/**
 * @autor: Mr.Chen
 * @create: 2018/11/19
 * @description:面包商店抽象类
 */
public abstract class BreadStoreFactory {

    public BreadFactory orderBread(String type){
        return createBread(type)
                .stir()
                .rubbingRound()
                .machining()
                .bake();
    }
    abstract BreadFactory createBread(String type);
}
