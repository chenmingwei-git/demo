package com.cmw.tools.json;

import com.cmw.pojo.People;

/**
 * @Author: cmw
 * @Description:
 * @Date:
 */
public class Test {

    protected static  People send()  {

        People people = new People();
        try {

            if (true){
                throw new ErrorException("都是我的错");
            }
        }catch (Exception e){
            e.getMessage();
            people.setName("1111");
            return people;
        }
        people.setName("222");
        return people;

    }

    public static void main(String[] args) {
        People p = Test.send();
        System.out.println(p.getName());
    }
}

