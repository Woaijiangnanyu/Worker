package com.example.guojialin.worker.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class WaveView extends View {
    private int width = 0;
    private int height = 0;
    private int baseLine = 0; // 基线，用于控制水位上涨的
    private Paint mPaint;
    private int waveHeight = 100; //波浪的最高度
    private int waveWidth; // 波长
    private float offset = 0f; // 偏移量

    public WaveView(Context context) {
        super(context);
        initView();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getMeasuredWidth(); // 获取屏幕宽度
        height = getMeasuredHeight(); // 获取屏幕的高度
        waveWidth = width;
        baseLine = height/2;
        updateXControl();
    }

    /**
     * 不断更新偏移量，并且循环
     */
    private void updateXControl() {
        // 设置一个波长的偏移
        ValueAnimator mAnimator = ValueAnimator.ofFloat(0,waveWidth);
        mAnimator.setInterpolator( new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatorValue = (float) animation.getAnimatedValue();
                offset = animatorValue; // 不断的设置偏移量，并重画
                //工作线程调用
                postInvalidate();
            }
        });
        mAnimator.setDuration(1000);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(getPath(),mPaint);
    }

    /**
     * 核心代码，计算path
     * @return
     */
    private Path getPath(){
        int itemWidth = waveWidth/2;//半个波长
        Path mPath = new Path();
        mPath.moveTo(-itemWidth * 3, baseLine); // 起始坐标
        //核心代码
        for (int i = -3; i < 2; i ++){
            int startX = i * itemWidth;
            mPath.quadTo(startX + itemWidth/2 + offset, // 控制点的X
                    getWaveHeight(i), // 控制点的Y
                    startX + itemWidth + offset, //结束点的X
                    baseLine//结束点的Y
                    );//半个波长
        }
        //形成封闭区间
        mPath.lineTo(width,height);
        mPath.lineTo(0,height);
        mPath.close();
        return mPath;
    }

    private int getWaveHeight(int num){
        if (num % 2 == 0){
          return baseLine + waveHeight;
        }
        return  baseLine - waveHeight;
    }
}
