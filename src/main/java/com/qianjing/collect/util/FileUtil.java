package com.qianjing.collect.util;

import com.qianjing.collect.exception.FileException;
import org.apache.catalina.manager.util.SessionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {
    //获取后缀名
    public static String getSuffix(String filename) throws FileException {
        if (StringUtils.isEmpty(filename)) {
            throw new FileException("文件名为空");
        }
        int index = filename.lastIndexOf(".");
        if (index == -1 || index == filename.length() - 1) {
            throw new FileException("后缀名不存在");
        }
        return filename.substring(index + 1);
    }

    public static void zipFiles(File[] srcFiles, File target) {
        ZipEntry zipEntry;
        FileInputStream fis = null;
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(target))) {
            int len;
            for (File file : srcFiles) {
                fis = new FileInputStream(file);
                zipEntry = new ZipEntry(file.getName());
                zos.putNextEntry(zipEntry);
                byte[] buffer = new byte[1024];//每次读取的大小
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
            }
            zos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
