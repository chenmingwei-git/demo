package com.cmw.threadPractice;

/**
 * @autor:
 * @create:
 * @description:
 */
public class StringFormat {
    private static int x=100;
    public static void main(String[] args) {
//        String ss="你好,请输入密码:%s,谢谢配合";
//        String s= String.format(ss,"302189");
//        System.out.println(s);

        Integer i1= 1;
        int i2 =1;
        Integer i3= Integer.valueOf(1);
        Integer i4 = new Integer(1);
        System.out.println(i1==i2);
        System.out.println(i1==i3);
        System.out.println(i3==i4);
        System.out.println(i2==i4);

//        StringFormat hs1=new StringFormat();
//                 hs1.x++;
//        StringFormat  hs2=new StringFormat();
//                 hs2.x++;
//                hs1=new StringFormat();
//                hs1.x++;
//        StringFormat.x--;
//               System.out.println("x="+x);
    }
}
