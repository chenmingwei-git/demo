package com.cmw.listdemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @autor:
 * @create:
 * @description:
 */
public class Test {
    public static void main(String[] args) {

        List<String> listArray = new ArrayList<>();

        listArray.add("1");
        listArray.add("2");
        listArray.add("3");
        listArray.add("4");
        listArray.add("5");

//        for(int i=0; i < listArray.size(); i++){
//
//            if("3".equals(listArray.get(i))){
//                listArray.remove(i);
//                i--;
//            }
//
//            System.out.println(i);
//        }


        Iterator<String> it = listArray.iterator();
        while (it.hasNext()) {
            if ("3".equals(it.next())) {
                it.remove();
            }
        }

        listArray.forEach(s -> System.out.println(s));
        listArray.stream().filter(s -> "2".equals(s)).forEach(s -> System.out.println(s));
    }


}
