package com.example.guojialin.worker.ui;

import android.app.Activity;
import android.os.Bundle;

import com.example.guojialin.worker.R;
import com.example.guojialin.worker.view.FractionTextView;

public class FractionActivity extends Activity {

    private FractionTextView fractionTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraction);
        fractionTextView = findViewById(R.id.fraction);
        fractionTextView.setFractionParams("2","8",true);
    }
}
