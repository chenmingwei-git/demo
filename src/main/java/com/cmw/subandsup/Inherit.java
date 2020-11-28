package com.cmw.subandsup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: cmw
 * @Description:
 * @Date:
 */
public class Inherit {
    public static void main(String[] args) {
//        Father father = new Son();
//        father.talk();
//        Son s = new Son();
//        s.speak();

    }

}





class Father{
    public void talk(){
        System.out.println("我要说话");

    }

    public void speak(){
        System.out.println("我要吃饼");
    }

}

class Son extends Father {

    public void speak(){

        super.speak();
        System.out.println("儿子要吃饼");
    }


}



