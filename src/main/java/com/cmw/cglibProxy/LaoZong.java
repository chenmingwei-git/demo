package com.cmw.cglibProxy;


import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @autor:
 * @create:
 * @description:  真实要访问的对象
 */

public class LaoZong {


     public void talk(){
         System.out.println("把酒言欢");
     }


    public void eat() {
        System.out.println("吃饭");
    }


    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
        calendar.set(Calendar.MONTH, 8);
        calendar.set(Calendar.DATE,31);



        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()) );

    }
}

