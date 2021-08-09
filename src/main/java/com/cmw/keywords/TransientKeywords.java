package com.cmw.keywords;

import java.io.*;

/**
 * @description: transient 关键字练习
 * @author: cmw
 * @data: 2021/8/9
 */
public class TransientKeywords {

    /**
     * 使用transient修饰的变量不参与序列化与反序列化
     * 由下面的例子可以参考 当Student对象中的name属性使用 transient修饰时
     * 先序列化再反序列化则name值不会反显  age属性没有进行修饰则成功反显
     */
    public static void main(String[] args) {

        Student s = new Student(11, "zhangshan");
        System.out.println("序列化前的学生:"+s);//序列化前的学生:Student{age=11, name='zhangshan'}

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            // 将student对象写入磁盘文件(序列化)
            oos = new ObjectOutputStream(new FileOutputStream("student.txt"));
            oos.writeObject(s);

            // 从磁盘文件读取student对象(反序列化)
            ois = new ObjectInputStream(new FileInputStream("student.txt"));
            Student student = (Student) ois.readObject();
            System.out.println("序列化后的学生:"+student);//序列化后的学生:Student{age=11, name='null'}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
