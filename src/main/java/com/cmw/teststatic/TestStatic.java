package com.cmw.teststatic;

/**
 * @autor:
 * @create:
 * @description:
 */
public class TestStatic {
    static int a;
    int b;
    static int c;

    public int aMethod(){
        a++;
        return a;
    }
    public int bMethod(){
        b++;
        return b;
    }
    public static int cMethod(){
        c++;
        return c;
    }

    public static void main(String[] args) {
        TestStatic test1 =new TestStatic();
        TestStatic test2 =new TestStatic();
        TestStatic test3 =new TestStatic();

        int s1=test1.aMethod();
        System.out.println(s1);

        int s2=test2.bMethod();
        System.out.println(s2);

        int s3=test3.cMethod();
        System.out.println(s2);

    }
}
