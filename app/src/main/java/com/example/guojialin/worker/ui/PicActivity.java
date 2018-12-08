package com.example.guojialin.worker.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.guojialin.worker.R;
import com.example.guojialin.worker.view.PaintView;

public class PicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        PaintView paintView = findViewById(R.id.paint);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.homwork);
        paintView.initBitmap(bitmap);
        paintView.paint();
    }
}
