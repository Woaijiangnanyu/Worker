package com.example.guojialin.worker.utils;

import com.example.guojialin.worker.base.FileCache;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {


    public static File creatNewFile() {
        File targetFile;
        File dir = new File(FileCache.getAppCacheDirectory());
        if (dir.exists()) {
            dir.delete();
        }
        dir.mkdirs();

        targetFile = new File(dir, getPhotoFileName());

        if (targetFile.exists()) {
            targetFile.delete();
        }
        try {
            targetFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("file path " + targetFile.getAbsolutePath());
        return targetFile;
    }

    //不包含根目录
    public static File getExistFile(String dir,String fileName){
        File targetFile;
        File dirF = new File(FileCache.getAppCacheDirectory() + dir);
        if (dirF.exists()) {
            dirF.delete();
        }
        dirF.mkdirs();
        targetFile = new File(dirF,fileName);
        return targetFile;
    }
    // 使用系统当前日期加以调整作为照片的名称
    public static String getPhotoFileName() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return timeStamp + ".txt";
    }
}
