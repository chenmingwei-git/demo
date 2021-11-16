package com.cmw.reflex;

/**
 * @description: 测试私有化构造函数
 * @author: cmw
 * @date: 2021/11/16
 */
public class Test3 {
    private String name;
    private String address;
    private Test3(){

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
        return "Test3{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static void main(String[] args) {
        try {
            Class c = Class.forName(Test3.class.getName());
            Test3 instance = (Test3)c.newInstance();//即使私有化构造函数 也能实例化对象
            instance.setAddress("北京市");
            System.out.println(instance.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
