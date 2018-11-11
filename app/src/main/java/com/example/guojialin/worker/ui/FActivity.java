package com.example.guojialin.worker.ui;

import android.os.Bundle;
import android.util.Log;

import com.example.guojialin.worker.R;
import com.example.guojialin.worker.base.BaseActivity;
import com.example.guojialin.worker.bean.User;

public class FActivity extends BaseActivity {

    private final String Tag = FActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f);
        User user = getIntent().getParcelableExtra("user");
        Log.i(Tag,user.toString());
    }
}
