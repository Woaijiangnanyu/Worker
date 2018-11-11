package com.example.guojialin.worker.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.guojialin.worker.R;
import com.example.guojialin.worker.ja.StackQueue;


public class KActivity extends Activity {
    StackQueue stackQueue = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_k);
        stackQueue = new StackQueue();
    }

    public void pop(View view) {
        Integer integer = stackQueue.pop();
        System.out.println("pop top value : " + integer);
    }

    public void push(View view) {
        stackQueue.push(3);
        System.out.println("push value : " + 3);
    }
}
