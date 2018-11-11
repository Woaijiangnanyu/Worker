package com.example.guojialin.worker.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.guojialin.worker.R;
import com.example.guojialin.worker.ja.Consumer;
import com.example.guojialin.worker.ja.PCData;
import com.example.guojialin.worker.ja.Producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class LActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l);

    }

    public void mainClick(View view) {
        BlockingQueue<PCData> queue = new LinkedBlockingDeque<>(10);
        Producer p1 = new Producer(queue);
        Producer p2 = new Producer(queue);
        Producer p3 = new Producer(queue);
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(p1);
        service.execute(p2);
        service.execute(p3);
        service.execute(c1);
        service.execute(c2);
        service.execute(c3);
        try {
            Thread.sleep(10 * 1000);
            p1.stop();
            p2.stop();
            p3.stop();
            Thread.sleep(3000);
        } catch (Exception e) {

        } finally {
            service.shutdown();
        }

    }
}
