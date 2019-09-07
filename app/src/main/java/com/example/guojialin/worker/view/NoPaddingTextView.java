package com.example.guojialin.worker.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author  就是一阵风而已
 */

public class NoPaddingTextView extends android.support.v7.widget.AppCompatTextView {
    //设置是否remove间距，true为remove
    private boolean noDefaultPadding = true;
    private Paint.FontMetricsInt fontMetricsInt;
    private Rect minRect;

    public NoPaddingTextView(Context context) {
        super(context);
    }

    public NoPaddingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoPaddingTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (noDefaultPadding) {
            if (fontMetricsInt == null) {
                //fontMetricsInt包含的是text文字四条线的 距离，
                //此四条线距离也是以text文字baseline为基准的
                fontMetricsInt = new Paint.FontMetricsInt();
            }
            getPaint().getFontMetricsInt(fontMetricsInt);
            if (minRect == null) {
                //minRect用来获取文字实际显示的时候的左上角和右下角  坐标
                //该坐标是以text文字baseline为基准的
                minRect = new Rect();
            }
            getPaint().getTextBounds(getText().toString(), 0, getText().length(), minRect);
            canvas.translate(-minRect.left, fontMetricsInt.bottom - minRect.bottom);
        }
        super.onDraw(canvas);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        this.requestLayout();
    }
}


