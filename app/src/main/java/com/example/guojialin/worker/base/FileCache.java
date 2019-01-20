package com.example.guojialin.worker.base;

import android.content.Context;
import android.os.Environment;

public class FileCache {
    public static String getRootDirectory() {
        Context context = TApplication.getInstance();
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return Environment.getExternalStorageDirectory().getPath();
        } else {
            return context.getCacheDir().toString();
        }
    }
    //app 目录
    public static String getAppCacheDirectory() {
            return getRootDirectory()+"/jiaWorker/";
    }
    //登录证书 目录
    public static String getLoginCertDirectory(){
        return getAppCacheDirectory()+"text/";
    }
}