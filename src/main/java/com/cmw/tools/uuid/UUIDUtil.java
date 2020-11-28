package com.cmw.tools.uuid;



import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.util.UUID;

/**
 *  UUID工具类
 */
public class UUIDUtil {

    private static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    public static String createUUID32() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * uuid  32位, 小写
     *
     * @return
     */
    public static String uuidLower() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }


    /**
     * 8位uuid
     *
     * @return
     */
    public static String createUUID8() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }


    /**
     * 取得一个指定范围内的随机整数
     *
     * @param min 最小值（包含此最小值）
     * @param max 最大值（不包含此最大值）
     */

    public static Integer getRandomNumber(int min, int max) {
        /*Random random = new Random((new Date()).getTime());// 指定种子数*/
    	SecureRandom random = new SecureRandom();
        return min + random.nextInt(max - min);
    }


    /**
     * 将18位身份证号降位转成15位返回<br />
     * 此函数是直接将多余的位数删除后返回，如果输入的id_code不是18位，则直接返回id_code本身
     */
    public static String id18To15(String id_code) {
        if (StringUtils.isEmpty(id_code))// 输入字符为空则返回空字符
            return "";

        if (id_code.length() != 18)// 输入字符不是18位则返回原字符串
            return id_code;

        StringBuffer sb = new StringBuffer(id_code);
        sb.replace(17, 18, "");// 删除校验位
        sb.replace(6, 8, "");// 删除年份的前两位
        return sb.toString();
    }


    /**
     * 返回六位数的纯数字验证码
     *
     * @return
     */
    public static String numbercheckcode() {
        char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String res = "";
        for (int i = 0; i < 6; i++) {
            int id = (int) Math.ceil(new SecureRandom().nextInt(1000) * 10);
            res += chars[id];
        }
        return res;
    }

    /**
     * 返回四位数的验证码
     *
     * @return
     */
    public static String verification() {
        char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String res = "";
        for (int i = 0; i < 4; i++) {
            int id = (int) Math.ceil(new SecureRandom().nextInt(1000) * 35);
            res += chars[id];
        }
        return res;
    }


    /**
     * 获取指定长度的随机数字字符串
     *
     * @param n  字符串的长度
     * @param sp 每个数字之间的分割符
     * @return
     */
    public static String genRandom(int n, String sp) {
        sp = sp == null ? "" : sp;
        SecureRandom rand = new SecureRandom();
        String result = "";
        for (int i = 0; i < n; i++) {
            Float f = rand.nextFloat() * 10;
            if (i > 0)
                result += sp;
            result += f.intValue();
        }
        return result;
    }

    public static String random6Number() {
        return (int) ((new SecureRandom().nextInt(1000) * 9 + 1) * 100000) + "";
    }

    public static String randomNumberLetters(int length) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] rands = new char[length];
        for (int i = 0; i < length; i++) {
            int rand = (int) (new SecureRandom().nextInt(1000) * 36);
            rands[i] = chars.charAt(rand);
        }
        return String.valueOf(rands);
    }

   /* public static String[] args) {
        System.out.println(genRandom(6,""));
    }*/
}
