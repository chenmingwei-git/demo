package com.cmw.tools.rsa;


import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @描述 RSA加密 未采用分段加密
 * @author: ChenMingWei
 * @create: 2020-08-07 10:03
 */
public class RsaUtils {

    public static final String KEY_ALGORITHM = "RSA";
    public static final String RSA_PADDING_KEY = "RSA/ECB/PKCS1Padding";

    /**
     * 获取RSA公钥编码
     *
     * @param key
     * @return
     * @throws Exception
     */
    private static PublicKey getPublicKey(String key) throws Exception{
        byte[] keyBytes = Base64.decodeBase64(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory =KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 获取RSA私钥编码
     *
     * @param key
     * @return
     * @throws Exception
     */
    private static PrivateKey getPrivateKey(String key) throws Exception{
        byte[] keyBytes = Base64.decodeBase64(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        KeyFactory keyFactory =KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 公钥加密
     *
     * @param plainText 待加密数据
     * @param publicKeyStr 公钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String plainText,String publicKeyStr){
        if(plainText ==null || publicKeyStr == null){
            return null;
        }
        try{
            PublicKey publicKey = getPublicKey(publicKeyStr);
            Cipher cipher =Cipher.getInstance(RSA_PADDING_KEY);
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
            byte[] enBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
            return formatString(Base64.encodeBase64String(enBytes));
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 私钥解密
     *
     * @param enStr 待解密数据
     * @param privateKeyStr 私钥
     * @return
     * @throws Exception
     */
    public static String decrypt(String enStr,String privateKeyStr){
        if(enStr ==null || privateKeyStr==null){
            return null;
        }
        try{
            PrivateKey privateKey =getPrivateKey(privateKeyStr);
            Cipher cipher = Cipher.getInstance(RSA_PADDING_KEY);
            cipher.init(Cipher.DECRYPT_MODE,privateKey);
            byte[] deBytes = cipher.doFinal(Base64.decodeBase64(enStr));
            return new String(deBytes,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式化RSA加密字符串  去掉换行符和渐近符号
     *
     * @param sourceStr 加密后数据
     * @return
     * @throws Exception
     */
    private static String formatString (String sourceStr){
        if(sourceStr ==null){
            return null;
        }
        return sourceStr.replaceAll("\\r","").replaceAll("\\n","");
    }


    /**
     * 私钥加密
     *
     * @param enStr 待加密数据
     * @param privateKeyStr 私钥
     * @return
     * @throws Exception
     */
    public static String encryptPrivate(String enStr,String privateKeyStr){
        if(enStr ==null || privateKeyStr == null){
            return null;
        }
        try{
            byte[] keyBytes = Base64.decodeBase64(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            Cipher cipher =Cipher.getInstance(RSA_PADDING_KEY);
            cipher.init(Cipher.ENCRYPT_MODE,privateKey);
            byte[] enBytes = cipher.doFinal(enStr.getBytes("UTF-8"));
            return formatString(Base64.encodeBase64String(enBytes));
        }catch (Exception e){

        }
        return null;
    }


    /**
     * 公钥解密
     *
     * @param enStr 待解密数据
     * @param publicKeyStr 公钥
     * @return
     * @throws Exception
     */
    public static String decryptPublic(String enStr,String publicKeyStr){
        if(enStr ==null || publicKeyStr==null){
            return null;
        }
        try{
            byte[] keyBytes = Base64.decodeBase64(publicKeyStr);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            Cipher cipher = Cipher.getInstance(RSA_PADDING_KEY);
            cipher.init(Cipher.DECRYPT_MODE,publicKey);
            byte[] deBytes = cipher.doFinal(Base64.decodeBase64(enStr));
            return new String(deBytes,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
