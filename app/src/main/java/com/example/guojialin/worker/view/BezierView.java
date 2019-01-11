package com.example.guojialin.worker.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BezierView extends View {
    private int mCenterX;
    private int mCenterY;
    private Paint mPaint;
    private Paint mPaintCircle;
    private int mCircleRadius;
    private Paint mPaintPoint;
    private List<PointF> mPointDatas; //放置四个数据点的集合
    private List<PointF> mPointControlls;//方式8个控制点的集合
    private int mDuration = 1000; //动画总时间
    private int mCurrTime = 0;  //当前已进行时间
    private int mCount = 100;//将总时间划分多少块
    private float mPiece = mDuration / mCount; //每一块的时间 ；
    public BezierView(Context context) {
        super(context);
        init();
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化画笔
     */

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(3);
        mPaint.setAntiAlias(true);

        mPaintCircle = new Paint();
        mPaintCircle.setColor(Color.RED);
        mPaintCircle.setStrokeWidth(10);
        mPaintCircle.setStyle(Paint.Style.FILL);
        mPaintCircle.setAntiAlias(true);
        mCircleRadius = 150;

        mPaintPoint = new Paint();
        mPaintPoint.setColor(Color.BLACK);
        mPaintPoint.setStrokeWidth(8);
        mPaintPoint.setStyle(Paint.Style.FILL);
        mPaintPoint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterY = getHeight() / 2;
        mCenterX = getWidth() / 2;
        mPointDatas = new ArrayList<>();
        mPointControlls = new ArrayList<>();
        mPointDatas.add(new PointF(mCenterX, mCenterY - mCircleRadius));
        mPointDatas.add(new PointF(mCenterX + mCircleRadius, mCenterY));
        mPointDatas.add(new PointF(mCenterX, mCenterY + mCircleRadius));
        mPointDatas.add(new PointF(mCenterX - mCircleRadius, mCenterY));

        mPointControlls.add(new PointF(mCenterX + mCircleRadius / 2, mCenterY - mCircleRadius));
        mPointControlls.add(new PointF(mCenterX + mCircleRadius, mCenterY - mCircleRadius / 2));

        mPointControlls.add(new PointF(mCenterX + mCircleRadius, mCenterY + mCircleRadius / 2));
        mPointControlls.add(new PointF(mCenterX + mCircleRadius / 2, mCenterY + mCircleRadius));

        mPointControlls.add(new PointF(mCenterX - mCircleRadius / 2, mCenterY + mCircleRadius));
        mPointControlls.add(new PointF(mCenterX - mCircleRadius, mCenterY + mCircleRadius / 2));

        mPointControlls.add(new PointF(mCenterX - mCircleRadius, mCenterY - mCircleRadius / 2));
        mPointControlls.add(new PointF(mCenterX - mCircleRadius / 2, mCenterY - mCircleRadius));


    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制x,y 轴坐标
        canvas.drawLine(mCenterX, 0, mCenterX, getHeight(), mPaint);
        canvas.drawLine(0, mCenterY, getWidth(), mCenterY, mPaint);

        //绘制数据点
        for (int i = 0; i < mPointDatas.size(); i++) {
            canvas.drawPoint(mPointDatas.get(i).x,mPointDatas.get(i).y,mPaintPoint);
        }

        //绘制控制点
        for (int i = 0; i < mPointControlls.size(); i++) {
            canvas.drawPoint(mPointControlls.get(i).x, mPointControlls.get(i).y, mPaintPoint);
        }

        //利用贝塞尔三阶画圆
        Path path = new Path();
        path.moveTo(mPointDatas.get(0).x, mPointDatas.get(0).y);
        for (int i = 0; i < mPointDatas.size(); i++) {
            if (i == mPointDatas.size() - 1) {
                path.cubicTo(mPointControlls.get(2 * i).x, mPointControlls.get(2 * i).y, mPointControlls.get(2 * i + 1).x, mPointControlls.get(2 * i + 1).y, mPointDatas.get(0).x, mPointDatas.get(0).y);

            } else {
                path.cubicTo(mPointControlls.get(2 * i).x, mPointControlls.get(2 * i).y, mPointControlls.get(2 * i + 1).x, mPointControlls.get(2 * i + 1).y, mPointDatas.get(i + 1).x, mPointDatas.get(i + 1).y);
            }

        }
        canvas.drawPath(path, mPaintCircle);
        //动态改变数据点和辅助点
        mCurrTime += mPiece;
        if (mCurrTime < mDuration) {
            mPointDatas.get(0).y += 120 / mCount;
            mPointControlls.get(2).x -= 20.0 / mCount;

            mPointControlls.get(3).y -= 80.0 / mCount;
            mPointControlls.get(4).y -= 80.0 / mCount;
            mPointControlls.get(5).x += 20.0 / mCount;

            postInvalidateDelayed((long) mPiece);
        }
    }
}
