package com.cmw.lambda;

import java.util.Arrays;

/**
 * @autor:
 * @create:
 * @description:
 */
public class LambdaTest {

    static  int i ;

    public static void main(String[] args) {

        for ( i=0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() +" i= "+i);
            }).start();
        }


//        String[] ss ={"好好学习java"};
//
//        for(int i=0;i<ss.length;i++){
//            String s=ss[i];
//            System.out.println(s);
//        }
//
//
//        for(String s: ss){
//            System.out.println(s);
//        }
//
//        Arrays.asList(ss).forEach(s -> System.out.println(s));



    }

}
