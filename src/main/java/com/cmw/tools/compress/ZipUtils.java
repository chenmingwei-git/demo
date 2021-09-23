package com.cmw.tools.compress;

import lombok.extern.log4j.Log4j2;
import org.springframework.util.Base64Utils;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 文件压缩
 * @author cmw
 * @date 2021/9/22
 */
@Log4j2
public class ZipUtils {

    public static void zipFile(String targetPath, Map<String,InputStream> entrys){
        ZipOutputStream zipOutputStream = null;
        try {
            zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(targetPath)));
            Set<Map.Entry<String, InputStream>> entries = entrys.entrySet();
            for (Map.Entry<String, InputStream> e:entries) {
                String entryName = e.getKey();
                InputStream ins = e.getValue();
                zipOutputStream.putNextEntry(new ZipEntry(entryName));
                int len = 0;
                byte[] bytes = new byte[1024];
                while ((len = ins.read(bytes)) > 0) {
                    zipOutputStream.write(bytes, 0, len);
                }
                zipOutputStream.flush();
                zipOutputStream.closeEntry();
                // 关闭输入流
                ins.close();
            }
        } catch (IOException e) {
           log.warn("文件压缩异常",e);
        }finally {
            if(zipOutputStream != null){
                try {
                    zipOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 输入流直接压缩并进行base64编码
     * @param entrys 多个输入流，key为压缩文件内的子项名称
     * @return
     */
    public static String zipToString(Map<String,InputStream> entrys){
        ZipOutputStream zipOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
            Set<Map.Entry<String, InputStream>> entries = entrys.entrySet();
            for (Map.Entry<String, InputStream> e:entries) {
                String entryName = e.getKey();
                InputStream ins = e.getValue();
                zipOutputStream.putNextEntry(new ZipEntry(entryName));
                int len = 0;
                byte[] bytes = new byte[1024];
                while ((len = ins.read(bytes)) > 0) {
                    zipOutputStream.write(bytes, 0, len);
                }
                zipOutputStream.closeEntry();
                // 关闭输入流
                ins.close();
            }
        } catch (IOException e) {
            log.warn("文件压缩编码异常",e);
        }finally {
            if(zipOutputStream != null) {
                try {
                    zipOutputStream.close();
                } catch (IOException e) {
                    log.warn("文件流关闭异常",e);
                }
            }
        }
        byte[] bytes = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            log.warn("文件流关闭异常",e);
        }
        return Base64Utils.encodeToString(bytes);
    }

    /**
     *  获取文件字节数组，使用base64编码
     * @param path
     * @return
     */
    public static String getZipFile(String path){
        InputStream in = null;
        File file = new File(path);
        byte[] data = null;
        try {
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            log.warn("文件读取异常",e);
        }
        file.delete();
        //return new String(Base64.encodeBase64(data));
        return Base64Utils.encodeToString(data);
    }

    /**
     * 解压文件
     * @param zipFile
     * @param descDir
     */
    public static void unzipFile(File zipFile, String descDir) {
        try {
            ZipFile zip = new ZipFile(zipFile) ;
            for(Enumeration entries = zip.entries(); entries.hasMoreElements() ; ){
                ZipEntry entry = (ZipEntry) entries.nextElement();
                File file = new File(descDir + File.separator + entry.getName()) ;
                if( entry.isDirectory() ){
                    file.mkdirs() ;
                }else{
                    File parent = file.getParentFile() ;
                    if( !parent.exists() ){
                        parent.mkdirs() ;
                    }
                    InputStream in = zip.getInputStream(entry);
                    OutputStream out = new FileOutputStream(file) ;
                    int len = 0 ;
                    byte[] bytes = new byte[1024];
                    while( (len = in.read(bytes)) > 0){
                        out.write(bytes, 0, len);
                    }
                    in.close();
                    out.flush();
                    out.close();
                }
            }
        } catch (IOException e) {
            log.warn("解压文件失败",e);
        }
    }

    /**
     * base64字符串生成对应文件
     * @param resource
     * @param path
     */
    private static void saveZipFile(String resource,String path){
        byte[] buffer = Base64Utils.decodeFromString(resource);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            int available = byteArrayInputStream.available();
            byte[] data = new byte[available];
            byteArrayInputStream.read(data);
            fileOutputStream.write(data);
            fileOutputStream.close();
        } catch (IOException e) {
            log.warn("文件解码失败",e);
        }finally {
            try {
                byteArrayInputStream.close();
            } catch (IOException e) {
                log.warn("文件流close失败");
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\蓝海银行\\蓝海项目\\C端协议（非终稿）\\模板\\890295917901737984.pdf");
        FileInputStream fileInputStream2 = new FileInputStream("C:\\Users\\Administrator\\Desktop\\蓝海银行\\蓝海项目\\C端协议（非终稿）\\模板\\890295913493524480.pdf");
        String path = "C:\\Users\\Administrator\\Desktop\\蓝海银行\\蓝海项目\\C端协议（非终稿）\\模板\\511321199611231351.zip";
        Map<String,InputStream> param = new HashMap<>();
        param.put("123.pdf",fileInputStream);
        param.put("1234.pdf",fileInputStream2);
        ZipUtils.zipFile(path,param);
        String zipFile = ZipUtils.getZipFile(path);
        System.out.println(zipFile);
        FileInputStream fileInputStream3 = new FileInputStream("C:\\Users\\Administrator\\Desktop\\蓝海银行\\蓝海项目\\C端协议（非终稿）\\模板\\890295917901737984.pdf");
        FileInputStream fileInputStream4 = new FileInputStream("C:\\Users\\Administrator\\Desktop\\蓝海银行\\蓝海项目\\C端协议（非终稿）\\模板\\890295913493524480.pdf");
        Map<String,InputStream> param1 = new HashMap<>();
        param1.put("123.pdf",fileInputStream3);
        param1.put("1234.pdf",fileInputStream4);
        String s = ZipUtils.zipToString(param1);
        System.out.println(s);
        saveZipFile(s,"C:\\Users\\Administrator\\Desktop\\蓝海银行\\蓝海项目\\C端协议（非终稿）\\模板\\a.zip");
        saveZipFile(zipFile,"C:\\Users\\Administrator\\Desktop\\蓝海银行\\蓝海项目\\C端协议（非终稿）\\模板\\b.zip");

        unzipFile(new File("C:\\Users\\Administrator\\Desktop\\蓝海银行\\蓝海项目\\C端协议（非终稿）\\模板\\a.zip"),"C:\\Users\\Administrator\\Desktop\\蓝海银行\\蓝海项目\\C端协议（非终稿）\\模板\\");
    }
}
