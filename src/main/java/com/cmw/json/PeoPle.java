package com.cmw.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @description: 进行JSON转换测试的实体
 * @author: cmw
 * @date: 2021/11/3
 */
public class PeoPle implements Serializable {

    private static final long serialVersionUID = -5447619573150487762L;

    @JSONField(name = "AGE",ordinal = 2)
    private int age;

    @JSONField(ordinal = 1)
    private String name;

    @JSONField(ordinal = 3)
    private String address;

    public int getAge() {
        return age;
    }

    public PeoPle(){

    }

    public PeoPle(int age, String name, String address) {
        this.age = age;
        this.name = name;
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PeoPle{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static void main(String[] args) {
        PeoPle peoPle = new PeoPle(13, "张三", "河南");
        String ss = JSON.toJSONString(peoPle);
        System.out.println(JSON.toJSONString(peoPle));
        PeoPle peoPle1 = JSON.parseObject(ss,PeoPle.class);
        System.out.println(peoPle1);
    }
}
