package com.cmw.pojo;

/**
 * @autor:
 * @create:
 * @description:
 */
public class People {
    private int id;
    private String name;

   public People(){

    }

   public People(int id,String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        People p1= new People(1,"张三");
        People p2= p1;
        System.out.println("p1:"+p1.toString());
        System.out.println("p2:"+p2.toString());
        p1.setId(2);
        p1.setName("李四");
        p2.setId(3);
        p2.setName("王五");
        System.out.println("p1:"+p1.toString());
        System.out.println("p2:"+p2.toString());
    }
}
