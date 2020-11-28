package com.cmw.tools.pattern;


import org.springframework.util.StringUtils;

import java.util.Random;

public class CommonUtils {
    // 手机号码前三后四脱敏
    public static String mobileEncrypt(String mobile) {
        if (StringUtils.isEmpty(mobile) || (mobile.length() != 11)) {
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    //身份证前三后四脱敏
    public static String idEncrypt(String id) {
        if (StringUtils.isEmpty(id) || (id.length() < 8)) {
            return id;
        }
        return id.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*");
    }

    //护照前2后3位脱敏，护照一般为8或9位
    public static String idPassport(String id) {
        if (StringUtils.isEmpty(id) || (id.length() < 8)) {
            return id;
        }
        return id.substring(0, 2) + new String(new char[id.length() - 5]).replace("\0", "*") + id.substring(id.length() - 3);
    }

    /**
     * 名字脱敏
     * 规则，张三丰，脱敏为：张*丰
     * @param name
     * @return
     */
    public static String nameEncrypt(String name){
        if(name==null || name.isEmpty()){
            return "";
        }
        return name.replaceFirst(name.substring(0),"*")+name.substring(1);

//        String myName = null;
//        char[] chars = name.toCharArray();
//        if(chars.length==1){
//            myName=name;
//        }
//        if(chars.length==2){
//            myName=name.replaceFirst(name.substring(1), "*");
//        }
//        if(chars.length>2){
//            myName=name.replaceAll(name.substring(1, chars.length-1), "*");
//        }
//        return myName;
    }

    /**
     *  生成16位随机字符
     */
    public static String random(){

        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<16; i++){
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = CommonUtils.random();
        System.out.println(s);
    }
}
