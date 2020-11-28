package com.cmw.recursion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: cmw
 * @Description: 递归算法
 * @Date: 2019/12/25
 */
public class Recursion {
    /** 一元一瓶矿泉水，喝完后两个空瓶换一瓶矿泉水
      * 问：你有20元，最多可以喝几瓶矿泉水
      */

    public static void main(String[] args) {

       // System.out.println(test(20,20));

        Map a = new HashMap();
        a.put("aaa",new BigDecimal(0.00));

        BigDecimal b = new BigDecimal("0");

        BigDecimal c = (BigDecimal) a.get("aaa") ;
        System.out.println(c.compareTo(b));

        if(c.compareTo(b)==1){
            System.out.println("11111");
        }else {
            System.out.println("22222");
        }
    }

    //20代表你初始的瓶子,y代表最终喝多少瓶
    public static int test(int x,int y){
        int s1 = x/2;//兑换的矿泉水数

        int s2 = x%2;//剩余的瓶子

        int s3 =s1+y;//每次兑换结束后一共喝了多少瓶
        if(x<2){  //结束递归的条件就是最终瓶子数小于2 无法兑换
            return s3;
        }else {
            return test(s1+s2,s3);
        }

    }
}
