package com.cmw.tools.rsa;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.tomcat.util.codec.binary.Base64;


/**
 * @autor: cmw
 * @create:
 * @description: 主要是 公私钥加密(采用分段加密)
 */

public class RSAUtil {

    /**
     * 加密算法
     */
    public static final String ALGORITHM = "RSA";

    /**
     * 秘钥位数1024位
     */
    public static final Integer KEYSIZE = 1024;

    /** */
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** */
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 由16进制RSA字符串生成RSA公钥对象
     *
     * @param publicStr
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static RSAPublicKey parsePublicKeyFromString(String publicStr) throws InvalidKeySpecException, NoSuchAlgorithmException {
	if (publicStr == null)
	    return null;
	RSAPublicKey publicKey = (RSAPublicKey) KeyFactory.getInstance(ALGORITHM).generatePublic(new X509EncodedKeySpec(toBytes(publicStr)));
	return publicKey;
    }

    /**
     * 由16进制RSA字符串生成RSA私钥对象
     *
     * @param privateStr
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static RSAPrivateKey parsePrivateKeyFromString(String privateStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
	if (privateStr == null)
	    return null;
	KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
	RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(toBytes(privateStr)));
	return privateKey;
    }

    /**
     * 生成RSA的1024位秘钥对
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static KeyPair generateKey(int keySize) throws NoSuchAlgorithmException {
	KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);
	keyPairGen.initialize(keySize, new SecureRandom());
	return keyPairGen.generateKeyPair();
    }

    /**
     * 使用系统自带的RSA公钥加密
     *
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IOException
     */
    public static byte[] encrypt(String data, String rsaPublicKey) throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
	RSAPublicKey key = RSAUtil.parsePublicKeyFromString(rsaPublicKey);
	return encrypt(key, data.getBytes("UTF-8"));
    }

    /**
     * Encrypt String.
     *
     * @return byte[]
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws IOException
     */
    public static byte[] encrypt(RSAPublicKey publicKey, byte[] data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
	if (publicKey != null) {
	    Cipher cipher = Cipher.getInstance(ALGORITHM);
	    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	    int inputLen = data.length;
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    int offSet = 0;
	    byte[] cache;
	    int i = 0;
	    // 对数据分段加密
	    while (inputLen - offSet > 0) {
		if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
		    cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
		} else {
		    cache = cipher.doFinal(data, offSet, inputLen - offSet);
		}
		out.write(cache, 0, cache.length);
		i++;
		offSet = i * MAX_ENCRYPT_BLOCK;
	    }
	    byte[] encryptedData = out.toByteArray();
	    out.close();
	    return encryptedData;
	}
	return null;
    }

    /**
     * 解密
     *
     * @param data
     * @param rsaPrivateKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws IOException
     * @throws InvalidKeySpecException
     */
    public static byte[] decrypt(String data, String rsaPrivateKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeySpecException {
	RSAPrivateKey key = RSAUtil.parsePrivateKeyFromString(rsaPrivateKey);
	return decrypt(key, toBytes(data));
    }

    /**
     * Basic decrypt method
     *
     * @return byte[]
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws IOException
     */
    public static byte[] decrypt(RSAPrivateKey privateKey, byte[] encryptedData) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
	if (privateKey != null) {
	    Cipher cipher = Cipher.getInstance(ALGORITHM);
	    cipher.init(Cipher.DECRYPT_MODE, privateKey);

	    int inputLen = encryptedData.length;
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    int offSet = 0;
	    byte[] cache;
	    int i = 0;
	    // 对数据分段解密
	    while (inputLen - offSet > 0) {
		if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
		    cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
		} else {
		    cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
		}
		out.write(cache, 0, cache.length);
		i++;
		offSet = i * MAX_DECRYPT_BLOCK;
	    }
	    byte[] decryptedData = out.toByteArray();
	    out.close();
	    return decryptedData;
	}

	return null;
    }

    public static String toHexString(byte[] b) {
	StringBuilder sb = new StringBuilder(b.length * 2);
	for (int i = 0; i < b.length; i++) {
	    sb.append(HEXCHAR[(b[i] & 0xf0) >>> 4]);
	    sb.append(HEXCHAR[b[i] & 0x0f]);
	}
	return sb.toString();
    }

    public static final byte[] toBytes(String s) {
	byte[] bytes;
	bytes = new byte[s.length() / 2];
	for (int i = 0; i < bytes.length; i++) {
	    bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
	}
	return bytes;
    }

    private static char[] HEXCHAR = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public static String hexStr2Str(String hexStr) {
	String str = "0123456789ABCDEF";
	char[] hexs = hexStr.toCharArray();
	byte[] bytes = new byte[hexStr.length() / 2];
	int n;

	for (int i = 0; i < bytes.length; i++) {
	    n = str.indexOf(hexs[2 * i]) * 16;
	    n += str.indexOf(hexs[2 * i + 1]);
	    bytes[i] = (byte) (n & 0xff);
	}
	return new String(bytes);
    }

    /**
     * 私钥加密
     * 
     * @param
     * @param privateKey
     *            私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(String src, String privateKey) throws Exception {
	byte[] data = src.getBytes();
	byte[] keyBytes = Base64.decodeBase64(privateKey);
	PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
	KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
	Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
	cipher.init(Cipher.ENCRYPT_MODE, privateK);
	int inputLen = data.length;
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	int offSet = 0;
	byte[] cache;
	int i = 0;
	// 对数据分段加密
	while (inputLen - offSet > 0) {
	    if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
		cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
	    } else {
		cache = cipher.doFinal(data, offSet, inputLen - offSet);
	    }
	    out.write(cache, 0, cache.length);
	    i++;
	    offSet = i * MAX_ENCRYPT_BLOCK;
	}
	byte[] encryptedData = out.toByteArray();
	out.close();
	return Base64.encodeBase64String(encryptedData);
    }

    /**
     * 公钥解密
     *
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String str, String publicKeyString) throws Exception {

	byte[] encryptedData = Base64.decodeBase64(str);
	byte[] keyBytes = Base64.decodeBase64(publicKeyString);
	X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
	KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	Key publicK = keyFactory.generatePublic(x509KeySpec);
	Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
	cipher.init(Cipher.DECRYPT_MODE, publicK);
	int inputLen = encryptedData.length;
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	int offSet = 0;
	byte[] cache;
	int i = 0;
	// 对数据分段解密
	while (inputLen - offSet > 0) {
	    if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
		cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
	    } else {
		cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
	    }
	    out.write(cache, 0, cache.length);
	    i++;
	    offSet = i * MAX_DECRYPT_BLOCK;
	}
	byte[] decryptedData = out.toByteArray();
	out.close();
	return new String(decryptedData);
    }

    /**
     * base64编码的密钥对
     *
     * @return
     */
    public static Map<String, String> genKeyPair() throws Exception {
	// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
	KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
	// 初始化密钥对生成器，密钥大小为96-1024位
	keyPairGen.initialize(1024, new SecureRandom());
	// 生成一个密钥对，保存在keyPair中
	KeyPair keyPair = keyPairGen.generateKeyPair();
	RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); // 得到私钥
	RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); // 得到公钥
	String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
	// 得到私钥字符串
	String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
	// 将公钥和私钥保存到Map
	Map<String, String> map = new HashMap<String, String>();
	map.put("publicKey", publicKeyString); // 0表示公钥
	map.put("privateKey", privateKeyString); // 1表示私钥
	return map;
    }

    /**
     * RSA公钥加密
     *
     * @param str
     *            加密字符串 公钥
     * @return 密文
     * @throws Exception
     *             加密过程中的异常信息
     */
    public static String encryptBypublicKey(String str, String publicKeyString) throws Exception {
	byte[] data = str.getBytes();
	byte[] keyBytes = Base64.decodeBase64(publicKeyString);
	X509EncodedKeySpec pkcs8KeySpec = new X509EncodedKeySpec(keyBytes);
	KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	Key publicKey = keyFactory.generatePublic(pkcs8KeySpec);

	Cipher cipher = Cipher.getInstance(ALGORITHM);
	cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	int inputLen = data.length;
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	int offSet = 0;
	byte[] cache;
	int i = 0;
	// 对数据分段加密
	while (inputLen - offSet > 0) {
	    if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
		cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
	    } else {
		cache = cipher.doFinal(data, offSet, inputLen - offSet);
	    }
	    out.write(cache, 0, cache.length);
	    i++;
	    offSet = i * MAX_ENCRYPT_BLOCK;
	}
	byte[] encryptedData = out.toByteArray();
	out.close();
	return Base64.encodeBase64String(encryptedData);

    }

    /**
     * 私钥解密
     *
     * @param str
     * @param privateKeyString
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String str, String privateKeyString) throws Exception {

	byte[] encryptedData = Base64.decodeBase64(str);
	byte[] keyBytes = Base64.decodeBase64(privateKeyString);
	PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
	KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	Key privateK = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
	Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
	cipher.init(Cipher.DECRYPT_MODE, privateK);
	int inputLen = encryptedData.length;
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	int offSet = 0;
	byte[] cache;
	int i = 0;
	// 对数据分段解密
	while (inputLen - offSet > 0) {
	    if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
		cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
	    } else {
		cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
	    }
	    out.write(cache, 0, cache.length);
	    i++;
	    offSet = i * MAX_DECRYPT_BLOCK;
	}
	byte[] decryptedData = out.toByteArray();
	out.close();
	return new String(decryptedData);
    }

}