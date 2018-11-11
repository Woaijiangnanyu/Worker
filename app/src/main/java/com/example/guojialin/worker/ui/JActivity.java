package com.example.guojialin.worker.ui;

import android.app.Activity;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.example.guojialin.worker.R;

public class JActivity extends Activity {

    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_j);
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.lottie_layer_name);
        lottieAnimationView.setAnimation("lock_loading.json");
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();
    }
}
