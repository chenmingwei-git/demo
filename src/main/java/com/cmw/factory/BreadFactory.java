package com.cmw.factory;

/**
 * @autor: Mr.Chen
 * @create: 2018/11/19
 * @description: 面包口味的抽象类
 */
public abstract class BreadFactory {
    protected String name;
    protected String type;

    public BreadFactory stir(){
        System.out.println("搅拌");
        return this;
    }

    public BreadFactory rubbingRound(){
        System.out.println("搓圆");
        return this;
    }

    public BreadFactory machining(){
        System.out.println("加工");
        return this;
    }

    public BreadFactory bake(){
        System.out.println("烘烤");
        return this;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}
