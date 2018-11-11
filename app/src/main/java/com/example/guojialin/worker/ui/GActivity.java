package com.example.guojialin.worker.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.guojialin.worker.R;

public class GActivity extends Activity implements View.OnClickListener{

    private Button button0,button1,button2,button3,button4,button5;
    private String Tag = GActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g);
        initViews();
    }

    private void initViews() {
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                button0.scrollTo(10,0);
                Log.d(Tag, button0.getX() + "," + button0.getY());//注意这里getX和getY获取到的值永远都不会变，
                break;
            case R.id.button2:
                button0.scrollBy(10,0);
                break;
            case R.id.button3:
                button0.scrollTo(0,10);
                break;
            case R.id.button4:
                button0.scrollBy(0,10);
                break;
            case R.id.button5:
                button0.scrollBy(0, getResources().getDimensionPixelSize(R.dimen._size_6dp));
                break;
        }
    }
}
