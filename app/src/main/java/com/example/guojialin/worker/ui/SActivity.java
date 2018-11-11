package com.example.guojialin.worker.ui;

import android.app.Activity;
import android.os.Bundle;

import com.example.guojialin.worker.R;
import com.example.guojialin.worker.ja.AObserver;
import com.example.guojialin.worker.ja.BObserver;
import com.example.guojialin.worker.ja.ServerManager;

public class SActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s);
        ServerManager sm = new ServerManager();
        AObserver a    = new AObserver(sm);
        BObserver b = new BObserver(sm);
        sm.setData(5);
        sm.deleteObserver(a);//注销观察者，以后被观察者有数据变化就不再通知这个已注销的观察者
        sm.setData(10);
    }
}
