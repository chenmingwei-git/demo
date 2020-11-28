package com.cmw.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @描述 正则表达式
 * @author: ChenMingWei
 * @create: 2020-08-27 15:28
 */
public class RegexDemo {

    /**
     * Integer.parseUnsignedInt(String s,int radix)
     * 使用第二个参数指定的基数(进制)，将字符串参数解析为无符号的整数  没有第二个参数的默认是返回10进制
     */
    private static String converter(String hexWithZeroX) {
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile("0x(\\d{2})");
        final Matcher matcher = p.matcher(hexWithZeroX);
        while (matcher.find()) {
            sb.append((char) Integer.parseUnsignedInt(matcher.group(1), 16));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String testHexWithZeroX = "0x250x26";
        System.out.println(converter(testHexWithZeroX));
    }
}
