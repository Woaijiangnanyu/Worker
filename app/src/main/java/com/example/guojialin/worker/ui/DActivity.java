package com.example.guojialin.worker.ui;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.example.guojialin.worker.R;
import com.example.guojialin.worker.base.BaseActivity;

public class DActivity extends BaseActivity {

    private final String Tag = DActivity.class.getCanonicalName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("key",1);
        Log.i(Tag,new Exception().getStackTrace()[0].getMethodName());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        int i = savedInstanceState.getInt("key");
        Log.i(Tag,"key: "  + i);
        Log.i(Tag,new Exception().getStackTrace()[0].getMethodName());
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.i(Tag,new Exception().getStackTrace()[0].getMethodName() + " : " + newConfig.orientation);
        super.onConfigurationChanged(newConfig);
    }
}
