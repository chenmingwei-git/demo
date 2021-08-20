package com.cmw.reflex;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 通过反射校验返回值类型
 * @author: cmw
 * @data: 2021/8/20
 */
public class Test2 {

    public static void main(String[] args) {
        AAA aaa = new AAA();
        aaa.setA(1);
        aaa.setB("qw");
        aaa.setC("er");

        BBB bbb = new BBB();
        bbb.setC(3);
        bbb.setD("rr");

        BBB bbb2 = new BBB();
        bbb2.setD("rr");
        List<BBB> list = new ArrayList<>();
        list.add(bbb);
        list.add(bbb2);
        aaa.setBbb(list);

        testRerurn(aaa);

    }

    /**
     校验返回值类型当不为String的返回false
     当返回值类型为List的进行遍历校验

     */
    public static boolean testRerurn(Object o) {
        Class aClass = o.getClass();
        //获取所有声明的字段
        Field[] fields = aClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            //允许对 private声明的字段进行访问
            field.setAccessible(true);

            try {
                //获取字段值
                Object value = field.get(o);
                if (value instanceof String) {
                    continue;
                } else if (value instanceof List) {
                    List list = (List) value;
                    for (Object obj : list) {
                        if (!testRerurn(obj)) {
                            System.out.println("返回不符合要求的字段为：" + field.getName() + "--字段的值为：" + value);
//                            return false;
                        }
                    }
                } else {
                    System.out.println("返回不符合要求的字段为：" + field.getName() + "--字段的值为：" + value);
//                    return false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}

@Data
class AAA {
    int a;
    String b;
    String c;
    List<BBB> bbb;

}

@Data
class BBB {
    int c;
    String d;
}
