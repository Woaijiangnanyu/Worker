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
    private float widthScale = 1.0f; // 比例
    private float heightScale = 1.0f; // 比例
    private int picHeight;  // 图片高度
    private int picWidth; // 图片宽度
    private int viewHeight; // 控件高度
    private int viewWidth;  // 控件宽度

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "Width: " + getWidth() + " \nHeight: " + getHeight());
        orignBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.homework_1);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        mPaint.setAlpha(1000);
        errorIcon = BitmapFactory.decodeResource(getResources(), R.drawable.iconfontwenhao1);
        //精确缩放到指定大小
        errorIcon = Bitmap.createScaledBitmap(errorIcon, IconScaleSize, IconScaleSize, true);
        correctIcon = BitmapFactory.decodeResource(getResources(), R.drawable.iconcorrect_2);
        correctIcon = Bitmap.createScaledBitmap(correctIcon, IconScaleSize, IconScaleSize, true);
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

    private void onMeasureScale(int viewWidth, int viewHeight, int picWidth, int picHeight) {
        heightScale = (float) viewHeight / picHeight;
        widthScale = (float) viewWidth / picWidth;
        Log.d(TAG, "heightScale:" + heightScale + "widthScale:" + widthScale);
    }

    private void reDrawCanvas(Canvas canvas) {
        onMeasureScale(viewWidth, viewHeight, picWidth, picHeight);
        newBitmap = Bitmap.createScaledBitmap(orignBitmap, (int) (picWidth * widthScale), (int) (picHeight * heightScale), false);
        canvas.drawBitmap(newBitmap, new Matrix(), new Paint());
        for (int i = 0; i < questionImgsBeans.size(); i++) {
            PicDiscernBean.DataBean.QuestionImgsBean aiDiscern = questionImgsBeans.get(i);
            float left = aiDiscern.getLeftX() * widthScale;
            float top = aiDiscern.getTopY() * heightScale;
            float right = left + aiDiscern.getQuestionWidth() * widthScale;
            float bottom = top + aiDiscern.getQuestionHeight() * heightScale;
            float iconLeft = right + IconOffset; // 图标坐标
            float iconTop = top + mHeightError / 2;
            if (aiDiscern.getAnsResult() == 1) {
                mPaint.setColor(Color.rgb(0, 255, 0));
                canvas.drawBitmap(correctIcon, iconLeft, iconTop, mPaint);
            } else {
                mPaint.setColor(Color.rgb(255, 0, 0));
                canvas.drawBitmap(errorIcon, iconLeft, iconTop, mPaint);
            }
            canvas.drawRect(left, top, right, bottom, mPaint);

        }
    }

    public void updateCanvasData(PicDiscernBean picDiscernBean) {
        PicDiscernBean.DataBean dataBean = picDiscernBean.getData();
        picHeight = dataBean.getImgHeight();
        picWidth = dataBean.getImgWidth();
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
        viewWidth = widthSize - getPaddingLeft() - getPaddingRight();// 去掉左右两边的padding
        int heightMode = MeasureSpec.getMode(heightMeasureSpec); // 模式
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);// 高度大小
        viewHeight = heightSize - getPaddingTop() - getPaddingBottom();// 去掉上下两边的padding
        Log.d(TAG, "View-Width: " + viewWidth + " \nView-Height: " + viewHeight);
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
