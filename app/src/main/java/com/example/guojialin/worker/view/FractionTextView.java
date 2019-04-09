package com.example.guojialin.worker.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.guojialin.worker.R;

/**
 * @author guojialin
 * @apiNote 分式表达
 */


public class FractionTextView extends LinearLayout {

    /**
     * numerator 分子 or denominator 分母
     * prefix 前缀
     */
    private final String TAG = FractionTextView.class.getSimpleName();

    private float numeratorSize;     //分子大小
    private String numeratorValue;    //分子数值
    private int numeratorColor = 0xFF3E434C;    //分子颜色
    private float denominatorSize;
    private String denominatorValue;
    private int denominatorColor = 0xFFAFB2BA;  // 分母颜色
    private String prefixValue;       //前缀内容
    private int prefixColor = 0xFFAFB2BA;       //前缀颜色
    private float prefixSize;        //前缀大小
    private TextView numeratorTv;
    private TextView denominatorTv;
    private TextView prefixTv;
    private boolean prefixVisible; //前缀是否可见


    public FractionTextView(Context context) {
        super(context);
        init__(context, null);
    }

    public FractionTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init__(context, attrs);
    }

    public FractionTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init__(context, attrs);
    }

    /**
     * 初始化参数
     */

    private void init__(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.fraction_expression, this);
        numeratorTv = findViewById(R.id.numerator_tv);
        denominatorTv = findViewById(R.id.denominator_tv);
        prefixTv = findViewById(R.id.prefix_tv);

        //初始化配置
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.FractionTextView);
            numeratorSize = typedArray.getDimension(R.styleable.FractionTextView_numeratorSize, 16);
            numeratorValue = typedArray.getString(R.styleable.FractionTextView_numeratorValue);
            numeratorColor = typedArray.getColor(R.styleable.FractionTextView_numeratorColor, numeratorColor);

            denominatorSize = typedArray.getDimension(R.styleable.FractionTextView_denominatorSize, 16);
            denominatorColor = typedArray.getColor(R.styleable.FractionTextView_denominatorColor, denominatorColor);
            denominatorValue = typedArray.getString(R.styleable.FractionTextView_denominatorValue);

            prefixSize = typedArray.getDimension(R.styleable.FractionTextView_prefixSize, 16);
            prefixColor = typedArray.getColor(R.styleable.FractionTextView_prefixColor, prefixColor);
            prefixValue = typedArray.getString(R.styleable.FractionTextView_prefixValue);
            prefixVisible = typedArray.getBoolean(R.styleable.FractionTextView_prefixVisible, false);
            initView();
        }
    }

    private void initView() {

        if (numeratorValue!= null)numeratorTv.setText(numeratorValue);
        numeratorTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, numeratorSize);
        numeratorTv.setTextColor(numeratorColor);
        if (denominatorValue!=null)denominatorTv.setText(fitSingleNumber(denominatorValue));
        denominatorTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, denominatorSize);
        denominatorTv.setTextColor(denominatorColor);
        if (prefixValue!=null)prefixTv.setText(prefixValue);
        prefixTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, prefixSize);
        prefixTv.setTextColor(prefixColor);

        if (prefixVisible) {
            prefixTv.setVisibility(VISIBLE);
        } else prefixTv.setVisibility(GONE);
    }

    private String fitSingleNumber(String numberStr) {
        int number = Integer.parseInt(numberStr);
        if (number < 10) {
            return "/  " + numberStr;
        } else return "/" + numberStr;
    }

    public void setFractionParams(String numerator,String denominator,boolean showPrefix){
        this.numeratorValue = numerator;
        this.denominatorValue = denominator;
        this.prefixVisible = showPrefix;
        initView();
    }
}
