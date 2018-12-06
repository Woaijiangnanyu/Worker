package com.example.guojialin.worker.ui;

import android.app.Activity;
import android.os.Bundle;

import com.example.guojialin.worker.R;
import com.example.guojialin.worker.bean.AiDiscern;
import com.example.guojialin.worker.view.CustomView;

import java.util.ArrayList;

public class FanGaoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan_gao);
        CustomView customView = findViewById(R.id.ai_discern);
        ArrayList<AiDiscern> aiDiscerns = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            AiDiscern aiDiscern = new AiDiscern();
            aiDiscern.setLeftX(100 + i * 100 );
            aiDiscern.setTopY(100 + i * 100);
            aiDiscern.setQuestionHeight(80);
            aiDiscern.setQuestionWidth(200);
            aiDiscern.setAnsResult(false);
            aiDiscerns.add(aiDiscern);
        }
        customView.updateCanvasData(aiDiscerns);
    }
}
