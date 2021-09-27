package com.cmw.lambdaStreamDemo;

import com.cmw.pojo.People;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @autor: cmw
 * @create:
 * @description: 这个demo主要描述java8新特性lambda表达式(函数式编程)和stream的用法
 */
public class StreamTest {
    public static void main(String[] args) {

//        List<String> list1 = Arrays.asList("java", "php", "python", "5five");
//
//        list1.stream() //1.得到容器stream
//                .filter(s -> Character.isDigit(s.charAt(0))) //2.选出以数字开头的元素
//                .forEach(s -> System.out.println(s));//3.输出元素
//
//
//        //将集合中的元素转为全大写
//        list1.stream()
//                .map(s -> s.toUpperCase())
//                .forEach(System.out::println);
//
//        //会将集合中重复的元素去除
//        list1.stream()
//                .distinct().forEach(s -> System.out.println());
//
//        //输出长度大于三的元素
//        list1.forEach(str->{
//            if(str.length()>3){
//                System.out.println(str);
//            }
//        });
//
//        //和上面的写法等同
//        list1.stream()
//                .filter(s -> s.length()>3)  //选出长度大于3的字符串
//                //.forEach(s -> System.out.println(s)) 输出元素
//                .forEach(System.out::println); //输出元素 和上面那行等效
//
//        //按照长度大小排序
//        list1.stream()
//                .sorted((s1,s2)->s1.length()-s2.length())
//                .forEach(System.out::println);
//
//
//        List<String> list2 = new ArrayList<>(Arrays.asList("1one", "two", "three", "4four"));
//
//
//        //字符串长度大于3的全部转换为大写
//        list2.replaceAll(s -> {
//            if(s.length()>3)
//                return s.toUpperCase();
//            return s;
//        });
//        list2.forEach(System.out::println);
//
//        //删除元素长度大于3的字符串,并返回true/false
//        //使用removeIf方法的集合需要来自ArrayList才可以
//        System.out.println(list2.removeIf(s -> s.length() > 3));
//        list2.forEach(System.out::println);
//
//        Set<String> newList =
//                list2.stream()//1.得到Stream
//                        .filter(s -> !Character.isDigit(s.charAt(0))) //2.选出不以数字开头的字符串
//                        .map(String::toUpperCase) //3.转换成大写形式
//                        .collect(Collectors.toSet());//4.生成结果集
//
//        List<String> names = Arrays.asList("张三", "李四", "王老五", "李三", "刘老四", "王小二", "张四", "张五六七");
//        //找出姓张的最长名字的长度
////        List ll = new ArrayList();
////        for(String name:names){
////            if(name.startsWith("张")){
////                ll.add(name.length());
////            }
////        }
////        int maxLenZ = (int) Collections.max(ll);
//        //     System.out.println(maxLenZ);
//        //使用stream一行代码解决了。
//        int maxLenZ = names.parallelStream()
//                .filter(name -> name.startsWith("张"))
//                .mapToInt(String::length)
//                .max()
//                .getAsInt();
//        System.out.println(maxLenZ);

        Student s1 = new Student();
        Student s2 = new Student();
        s1.setAge(1);
        s1.setName("第一个");
        s1.setScore("88");

        s2.setAge(2);
        s2.setName("第二个");
        s2.setScore("99");

        List<Student> listStudent =new ArrayList<>();
        listStudent.add(s1);
        listStudent.add(s2);

        List<String> nameList = listStudent.stream().map(t->t.getName()).collect(Collectors.toList());
        nameList.stream().forEach(System.out::println);

        //输出学生中的姓名和分数
        Map<String,String>  map = listStudent.stream().collect(Collectors.toMap(Student::getName,Student::getScore));
        System.out.println(map);

    }
}
@Data
class Student{
    private int age;
    private String name;
    private String score;
}
