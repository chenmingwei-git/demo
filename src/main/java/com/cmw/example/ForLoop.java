package com.cmw.example;

/**
 * @autor: Mr.Chen
 * @create: 2018/11/21
 * @description: 双层for循环时continue 与 break的 用法
 */
public class ForLoop {
    public static void main(String[] args) {

        for(int i=0;i<3;i++){

            for(int j=0;j<3;j++){
                System.out.println("i="+i);
                if(i==1){
                    continue;/*此处用用continue只是跳过内层的一次循环
                                用break则是跳过外层的一次循环*/
                }
                System.out.println("j="+j);

            }
        }

    }
}
