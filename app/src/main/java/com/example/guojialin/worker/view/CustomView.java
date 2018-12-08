package com.example.guojialin.worker.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.guojialin.worker.R;
import com.example.guojialin.worker.bean.PicDiscernBean;

import java.util.ArrayList;

public class CustomView extends View {
    private final String TAG = CustomView.class.getSimpleName();
    private Bitmap newBitmap;
    private final Bitmap orignBitmap;
    private Paint mPaint;
    private Bitmap errorIcon, correctIcon;
    private float mWithError, mHeightError, mWithCorrect, mHeightCorrect;
    ArrayList<PicDiscernBean.DataBean.QuestionImgsBean> questionImgsBeans;
    private float IconOffset = 25f; // 图标偏移量
    private int IconScaleSize = 20;
    private int canvasHeight;
    private int canvasWidth;
    private float ratio = 2.43f;
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "Width: " + getWidth() + " \nHeight: " + getHeight());
        orignBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.homwork);
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
        errorIcon = Bitmap.createScaledBitmap(errorIcon, IconScaleSize, IconScaleSize, true);
        correctIcon = BitmapFactory.decodeResource(getResources(), R.drawable.iconcorrect_2);
        //获取结果图标icon宽高
        mWithError = errorIcon.getWidth();
        mHeightError = errorIcon.getHeight();
        mHeightCorrect = correctIcon.getHeight();
        mWithCorrect = correctIcon.getWidth();
    }


    protected void onDraw(Canvas canvas) {
        if (questionImgsBeans != null && questionImgsBeans.size() != 0) {
            reDrawCanvas(canvas);
        }
    }

    private void reDrawCanvas(Canvas canvas) {
        newBitmap = Bitmap.createScaledBitmap(orignBitmap, canvasWidth, canvasHeight, false);
        canvas.drawBitmap(newBitmap, new Matrix(), new Paint());
        for (int i = 0; i < questionImgsBeans.size(); i++) {
            PicDiscernBean.DataBean.QuestionImgsBean aiDiscern = questionImgsBeans.get(i);
            float left = aiDiscern.getLeftX();
            float top = aiDiscern.getTopY();
            float right = left + aiDiscern.getQuestionWidth();
            float bottom = top + aiDiscern.getQuestionHeight();
            float iconLeft = right + IconOffset; // 图标坐标
            float iconTop = top + mHeightError / 2;
            canvas.drawRect(left, top, right, bottom, mPaint);
            canvas.drawBitmap(errorIcon, iconLeft, iconTop, mPaint);
        }
    }

    public void updateCanvasData(PicDiscernBean picDiscernBean) {
        PicDiscernBean.DataBean dataBean = picDiscernBean.getData();
        canvasHeight = dataBean.getImgHeight();
        canvasWidth = dataBean.getImgWidth();
        ArrayList<PicDiscernBean.DataBean.QuestionImgsBean> questionImgsBeans = (ArrayList<PicDiscernBean.DataBean.QuestionImgsBean>) dataBean.getQuestionImgs();
        this.questionImgsBeans = questionImgsBeans;
        invalidate();
    }

    @SuppressLint("Range")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // widthMeasureSpec 宽度的规则 包含了两部分 模式 值
        int widthMode = MeasureSpec.getMode(widthMeasureSpec); // 模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);// 宽度大小
        int width = widthSize - getPaddingLeft() - getPaddingRight();// 去掉左右两边的padding
        int heightMode = MeasureSpec.getMode(heightMeasureSpec); // 模式
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);// 高度大小
        int height = heightSize - getPaddingTop() - getPaddingBottom();// 去掉上下两边的padding
        Log.d(TAG, "Width: " + canvasWidth + " \nHeight: " + canvasHeight);
        // 如果width是match_parent
//        if (widthMode == MeasureSpec.EXACTLY &&
//                heightMode != MeasureSpec.EXACTLY) {
//            // 修正一下 高度的值 让高度=宽度/比例
//            height = (int) (width / ratio + 0.5f); // 保证4舍五入
//
//        } else if (widthMode != MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) { // 由于高度是精确的值 ,宽度随着高度的变化而变化
//            width = (int) ((height * ratio) + 0.5f);
//        }
//        // 重新制作了新的规则
//        widthMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.EXACTLY, width + getPaddingLeft() + getPaddingRight());
//        heightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.EXACTLY, height + getPaddingTop() + getPaddingBottom());
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
