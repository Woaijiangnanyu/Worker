package com.example.guojialin.worker.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

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
    public static File getExistFile(String dir, String fileName) {
        File targetFile;
        File dirF = new File(FileCache.getAppCacheDirectory() + dir);
        if (dirF.exists()) {
            dirF.delete();
        }
        dirF.mkdirs();
        targetFile = new File(dirF, fileName);
        return targetFile;
    }

    // 使用系统当前日期加以调整作为照片的名称
    public static String getPhotoFileName() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return timeStamp + ".txt";
    }

    /**
     * 将uri转换成真实路径
     *
     * @param selectedVideoUri
     * @param contentResolver
     * @return
     */
    public static String getFilePathFromContentUri(Uri selectedVideoUri,
                                                   ContentResolver contentResolver) {
        String filePath = "";
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};

        Cursor cursor = contentResolver.query(selectedVideoUri, filePathColumn,
                null, null, null);
        // 也可用下面的方法拿到cursor
        // Cursor cursor = this.context.managedQuery(selectedVideoUri,
        // filePathColumn, null, null, null);

//        cursor.moveToFirst();
//
//        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//        filePath = cursor.getString(columnIndex);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int id = cursor.getColumnIndex(filePathColumn[0]);
                if (id > -1)
                    filePath = cursor.getString(id);
            }
            cursor.close();
        }

        return filePath;
    }


    public static boolean isExists(String path) {
        File file = new File(path);
        return file.exists();
    }
}
