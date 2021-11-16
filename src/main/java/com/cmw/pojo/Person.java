package com.cmw.pojo;

/**
 * @描述
 * @author: ChenMingWei
 * @create: 2020-07-19 13:26
 */
public class Person {
    public static void main(String[] args) {
        Person person = new Person(1,"张三");
//        person.work();
        int s = 1+1;
        String ss = "work";

        tests(s,ss,person);
        System.out.println("看看=: "+s+" : "+ss+" : "+person.toString());

    }

    public  int work(){
        int x =1;
        int y =2;
        int z =(x+y)*10;
        return z;
    }

    public  static void tests(int s ,String ss,Person person){
        s = s+2;
        ss =ss +ss;
        person.setAge(2);
        person.setName("李四");
        System.out.println("看看@: "+s+" : "+ss+" : "+person.toString());
    }

    private int age;

    private String name;

//    public Person(){
//
//    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
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

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

}
