package com.example.guojialin.worker.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.guojialin.worker.R;
import com.example.guojialin.worker.bean.AiDiscern;

import java.util.ArrayList;

public class CustomView extends View {
    private Paint mPaint;
    private Drawable mCustomImage;
    private Bitmap errorIcon, correctIcon;
    private float mWithError, mHeightError, mWithCorrect, mHeightCorrect;
    private ArrayList<AiDiscern> aiDiscerns;
    private float IconOffset = 50f; // 图标偏移量
    private int IconScaleSize = 45;

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCustomImage = context.getResources().getDrawable(R.drawable.fan_gao);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.rgb(255, 0, 0));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        mPaint.setAlpha(1000);
        errorIcon = BitmapFactory.decodeResource(getResources(), R.drawable.iconfontwenhao1);
        //精确缩放到指定大小
        errorIcon = Bitmap.createScaledBitmap(errorIcon,IconScaleSize,IconScaleSize, true);
        correctIcon = BitmapFactory.decodeResource(getResources(), R.drawable.iconcorrect_2);
        //获取结果图标icon宽高
        mWithError = errorIcon.getWidth();
        mHeightError = errorIcon.getHeight();
        mHeightCorrect = correctIcon.getHeight();
        mWithCorrect = correctIcon.getWidth();
    }


    protected void onDraw(Canvas canvas) {
        Rect imageBounds = canvas.getClipBounds();  // Adjust this for where you want it
        mCustomImage.setBounds(imageBounds);
        mCustomImage.draw(canvas);
        if (aiDiscerns != null && aiDiscerns.size() != 0) {
            reDrawCanvas(canvas);
        }
    }

    private void reDrawCanvas(Canvas canvas) {
        for (int i = 0; i < aiDiscerns.size(); i++) {
            AiDiscern aiDiscern = aiDiscerns.get(i);
            float left = aiDiscern.getLeftX();
            float top = aiDiscern.getTopY();
            float right = left + aiDiscern.getQuestionWidth();
            float bottom = top + aiDiscern.getQuestionHeight();
            float iconLeft = right + IconOffset; // 图标坐标
            float iconTop = top + mHeightError / 2;
            canvas.drawRect(left,top,right,bottom,mPaint);
            canvas.drawBitmap(errorIcon,iconLeft,iconTop,mPaint);
        }
    }

    public void updateCanvasData(ArrayList<AiDiscern> aiDiscerns) {
        this.aiDiscerns = aiDiscerns;
        invalidate();
    }
}
