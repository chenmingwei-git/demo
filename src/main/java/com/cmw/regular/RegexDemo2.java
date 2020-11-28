package com.cmw.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @描述
 * @author: ChenMingWei
 * @create: 2020-08-27 15:35
 */
public class RegexDemo2 {

    /**
     * 字符串中只能包含指定的字符 数字,大小写字母,汉字 _ ,-,以及 $ 其他的均不合法
     */
    private static Boolean specialChar(String str) {
        String regEx = "^[A-Za-z0-9\\u4E00-\\u9FA5_-]+$";
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(str);
        return matcher.find();
    }

    public static void main(String[] args) {
        String specialStr = "*#sdfsei";
        System.out.println(specialChar(specialStr));
    }
}
