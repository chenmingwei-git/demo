package com.cmw.tools.base64;

import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 *
 * @author ruidongfei
 * @version 1.0
 * @date 2021/1/25 11:25
 */
public class Base64Test {

    public static void main(String[] args) {
        try {
            String fileStr = file2Base64String("C:\\Users\\Administrator\\Desktop\\资金路由\\tmp\\802584682752741376.pdf");
            base64String2File(fileStr,"C:\\Users\\Administrator\\Desktop\\资金路由\\tmp\\a.pdf");

          //  file2Base64("C:\\Users\\Administrator\\Desktop\\资金路由\\tmp\\802584682752741376.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件转base64
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String file2Base64String(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] fileByte = new byte[fileInputStream.available()];
        fileInputStream.read(fileByte);
        String encode = new BASE64Encoder().encode(fileByte);
        System.out.println(encode);

        FileWriter fileWriter = null;
        try {
            // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
            filePath = filePath.replaceAll(".pdf",".txt");
            fileWriter = new FileWriter(filePath, true);
            fileWriter.write(encode);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                fileInputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return encode;
    }

    /**
     * base64 转到指定文件夹下
     * @param fileStr
     * @param filePath
     * @throws IOException
     */
    public static void base64String2File(String fileStr,String filePath) throws IOException {
        File file = new File(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] dncode = new BASE64Decoder().decodeBuffer(fileStr);
        fileOutputStream.write(dncode);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public static String file2Base64(String filePath) throws FileNotFoundException {
        FileInputStream f = new FileInputStream(filePath);
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len = 0;
        try {
            while (((len = f.read(bytes)) > 0)) {
                byteArrayOutputStream.write(bytes,0,len);
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes1 = byteArrayOutputStream.toByteArray();
        return Base64Utils.encodeToString(bytes1);
    }
}
