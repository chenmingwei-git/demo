package com.cmw.tools.compress;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.util.Base64Utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cmw
 * @date 2021/9/23
 */
@Log4j2
public class TarUtils {
    /**
     * 压缩为tar格式
     *
     * @param entrys 待压缩文件流
     * @param targetPath 压缩文件保存地址
     * @return tar压缩文件路径
     */
    public static String compressToTar(Map<String,InputStream> entrys,String targetPath) {
        //校验解压路径是否存在
        File tarFile = new File(targetPath);
        try (TarArchiveOutputStream tos = new TarArchiveOutputStream(new FileOutputStream(tarFile))) {
            tos.setLongFileMode(TarArchiveOutputStream.LONGFILE_POSIX);  //解决长路径问题
            for (Map.Entry<String,InputStream> e:entrys.entrySet()) {
                compressFileToTar(tos, e.getValue(), e.getKey());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回tar压缩文件路径
        return tarFile.getAbsolutePath();
    }
    /**
     *  获取文件字节数组，使用base64编码
     * @param path
     * @return
     */
    public static String getTarFile(String path){
        byte[] data = null;
        File file = new File(path);
        try (InputStream in = new FileInputStream(file)){
            data = new byte[in.available()];
            in.read(data);
        } catch (IOException e) {
            log.warn("文件读取异常",e);
        }
        //file.delete();
        return Base64Utils.encodeToString(data);
    }
    /**
     * 压缩为tar格式进行base64编码
     *
     * @return tar压缩文件路径
     */
    public static String compressToString(Map<String,InputStream> entrys) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (TarArchiveOutputStream tos = new TarArchiveOutputStream(byteArrayOutputStream)) {
            tos.setLongFileMode(TarArchiveOutputStream.LONGFILE_POSIX);
            for (Map.Entry<String,InputStream> e:entrys.entrySet()) {
                compressFileToTar(tos, e.getValue(), e.getKey());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回tar压缩文件的base64编码
        byte[] bytes = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            log.warn("文件流关闭异常",e);
        }
        return Base64Utils.encodeToString(bytes);
    }

    /**
     * 文件压缩为tar包
     *
     * @param tos
     * @param sourceFile
     * @throws IOException
     */
    private static void compressFileToTar(TarArchiveOutputStream tos, InputStream sourceFile, String basePath) throws IOException {
        TarArchiveEntry tEntry = new TarArchiveEntry(basePath);
        tEntry.setSize(sourceFile.available());
        tos.putArchiveEntry(tEntry);

        try (BufferedInputStream bis = new BufferedInputStream(sourceFile)) {

            byte[] buffer = new byte[1024];
            int read;
            while ((read = bis.read(buffer)) != -1) {
                tos.write(buffer, 0, read);
            }
        }
        sourceFile.close();
        tos.closeArchiveEntry();
    }

    /**
     * 解压tar格式的压缩包为tar压缩包
     *
     * @param sourcePath 待解压文件路径
     * @param targetPath 解压路径
     */
    public static void unpackTar(String sourcePath, String targetPath) {
        File sourceFile = new File(sourcePath);
        try (FileInputStream fileInputStream = new FileInputStream(sourceFile);
             TarArchiveInputStream tarArchiveInputStream = new TarArchiveInputStream(fileInputStream, "UTF-8")) {
            File targetFile = new File(targetPath);
            TarArchiveEntry entry;
            while ((entry = tarArchiveInputStream.getNextTarEntry()) != null) {
                if (entry.isDirectory()) {
                    continue;
                }

                File curFile = new File(targetFile, entry.getName());
                File parent = curFile.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }

                try (FileOutputStream outputStream = new FileOutputStream(curFile)) {
                    IOUtils.copy(tarArchiveInputStream, outputStream);
                }
            }
        } catch (IOException e) {
           log.warn("解压缩tar失败",e);
        }
    }
    /**
     * base64字符串生成对应文件
     * @param resource
     * @param path
     */
    private static void saveTarFile(String resource,String path){
        byte[] buffer = Base64Utils.decodeFromString(resource);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
        try(FileOutputStream fileOutputStream = new FileOutputStream(path);) {
            int available = byteArrayInputStream.available();
            byte[] data = new byte[available];
            byteArrayInputStream.read(data);
            fileOutputStream.write(data);
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
        String path = "C:\\Users\\Administrator\\Desktop\\蓝海银行\\蓝海项目\\C端协议（非终稿）\\模板\\511321199611231351.tar";
        Map<String,InputStream> param = new HashMap<>();
        param.put("123.pdf",fileInputStream);
        param.put("1234.pdf",fileInputStream2);
        String s = compressToString(param);
        System.out.println(s);
        FileInputStream fileInputStream3 = new FileInputStream("C:\\Users\\Administrator\\Desktop\\蓝海银行\\蓝海项目\\C端协议（非终稿）\\模板\\890295917901737984.pdf");
        FileInputStream fileInputStream4 = new FileInputStream("C:\\Users\\Administrator\\Desktop\\蓝海银行\\蓝海项目\\C端协议（非终稿）\\模板\\890295913493524480.pdf");
        Map<String,InputStream> param1 = new HashMap<>();
        param1.put("123.pdf",fileInputStream3);
        param1.put("1234.pdf",fileInputStream4);
        String s1 = compressToTar(param1, path);
        System.out.println(getTarFile(s1));
        System.out.println(s1);
        unpackTar("C:\\Users\\Administrator\\Desktop\\蓝海银行\\蓝海项目\\C端协议（非终稿）\\模板\\511321199611231351.tar","C:\\Users\\Administrator\\Desktop\\蓝海银行\\蓝海项目\\C端协议（非终稿）\\模板\\");
        saveTarFile(s,"C:\\Users\\Administrator\\Desktop\\蓝海银行\\蓝海项目\\C端协议（非终稿）\\模板\\5113211996112313511.tar");
    }
}
