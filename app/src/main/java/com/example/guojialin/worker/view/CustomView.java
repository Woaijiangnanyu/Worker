package com.example.guojialin.worker.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.guojialin.worker.R;

public class CustomView extends View {
    private Paint mPaint;
    private Drawable mCustomImage;

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCustomImage = context.getResources().getDrawable(R.drawable.fan_gao);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.rgb(255,0,0));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        mPaint.setAlpha(1000);
    }


    protected void onDraw(Canvas canvas) {
        Rect imageBounds = canvas.getClipBounds();  // Adjust this for where you want it
        mCustomImage.setBounds(imageBounds);
        mCustomImage.draw(canvas);
        canvas.drawRect(100,100,200,200,mPaint);
    }
}
