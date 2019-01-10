package com.example.guojialin.worker.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.guojialin.worker.R;
import com.example.guojialin.worker.utils.FileUtils;

import java.io.File;
import java.io.InputStream;

import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class MActivity extends Activity {

    private final String TAG = MActivity.class.getSimpleName();
    private ImageView imageView;
    private Handler mHandler;
    private final int COMPRESS_PIC = 0x001;
    private File filePic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m);
        imageView = (ImageView) findViewById(R.id.image);
        mHandler = new Handler(){
            @Override
            public void dispatchMessage(Message msg) {
                switch (msg.what){
                    case COMPRESS_PIC:
                        showPic(filePic);
                        break;
                }
            }
        };
    }

    private void showPic(File file) {
        if (file == null) return;
        Bitmap bm = BitmapFactory.decodeFile(file.getPath());
        imageView.setImageBitmap(bm);
    }

    public void loadPic(View view) {
        final File file = new File("/storage/emulated/0/jiaWorker/1544348690904_garden_pic.jpeg");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                startCompressPic(file);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void startCompressPic(File file) {

        Luban.with(this)
                .load(file)
                .ignoreBy(100)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        Log.i(TAG,"loading UI start...");
                    }

                    @Override
                    public void onSuccess(File file) {
                        Log.d(TAG,"图片的大小为："+file.length()/1024+"KB");
                        filePic = file;
                        mHandler.sendEmptyMessage(COMPRESS_PIC);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG,"failed compress ...");
                    }
                }).launch();
    }
}
