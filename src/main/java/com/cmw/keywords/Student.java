package com.cmw.keywords;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: transient关键字练习
 * @author: cmw
 * @data: 2021/8/9
 */
@Data
public class Student implements Serializable {

    private static final long serialVersionUID = -451890624132934012L;
    private int age;
    private transient String name;

    public Student() {
    }

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" + "age=" + age + ", name='" + name + '\'' + '}';
    }
}
