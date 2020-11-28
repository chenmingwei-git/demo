package com.cmw.reflex;

/**
 * @描述
 * @author: ChenMingWei
 * @create: 2020-08-31 10:54
 */
public class Test1 {
    public String name;
    public int age;
    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void add(){

    }

    @Override
    public String toString() {
        return "Test1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
