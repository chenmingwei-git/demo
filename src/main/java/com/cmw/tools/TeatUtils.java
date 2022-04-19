package com.cmw.tools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @description:  密钥进行加解密
 * @author: cmw
 * @data: 2021/8/11
 */

public class TeatUtils {

    public static void main(String[] args) throws Exception {
        String privateKey = getkey();
        String str = "{\n" +
                "\t\"channel\": \"XH10001\",\n" +
                "\t\"fundno\": \"F008\",\n" +
                "\t\"productid\": \"BJQJ001\",\n" +
                "\t\"querydate\": \"2021-11-18\",\n" +
                "\t\"reqdate\": \"20211118\",\n" +
                "\t\"reqtime\": \"145932\",\n" +
                "\t\"signature\": \"QJ\",\n" +
                "\t\"trancode\": \"QLZS000000018\",\n" +
                "\t\"transerno\": \"XH100012021111818244300000011815\"\n" +
                "}";
        // json串加密
        byte[] bytes1 = SM4Util.encryptSm4EcbPadding(str, "UTF-8",privateKey );
        byte[] bytes2 = SM4Util.encodeBase64(bytes1);
        String string = new String(bytes2);
        System.out.println(string);

        // 解密
        byte[] decodeBase64Bytes = SM4Util.decodeBase64(string, "UTF-8");
        byte[] bytes = SM4Util.decryptSm4EcbPadding(decodeBase64Bytes, "UTF-8", privateKey);
        System.out.println(new String(bytes,"UTF-8"));


    }

    /**
     * 密钥生成
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     */
    public static String getkey() throws NoSuchProviderException, NoSuchAlgorithmException {
        byte[] bytes1 = SM4Util.generateKey();

        final String HEX = "0123456789ABCDEF";
        StringBuilder sb = new StringBuilder(bytes1.length * 2);
        for (byte b : bytes1) {
            // 取出这个字节的高4位，然后与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            sb.append(HEX.charAt((b >> 4) & 0x0f));
            // 取出这个字节的低位，与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            sb.append(HEX.charAt(b & 0x0f));
        }
        System.out.println(sb.toString());
        return sb.toString();

    }

}
