package com.example.guojialin.worker.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.guojialin.worker.R;

public class OActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o);
    }

    public void printTriangle(View view) {
        int line = 20;//定义行数
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            //打印*的个数
            for (int j = 0; j < 2 * (line - i) - 1; j++) {
                System.out.print(" *");
            }
            //打印完* 记得换行
            System.out.println();
        }
    }

    public void printThombus(View view) {
        int line = 6;
        //打印一行
        //打印上半部分
        for (int i = 0; i < line - 1; i++) {
            //打印i个空格
            for (int j = 0; j < line - 1 - i; j++) {
                System.out.print(" ");
            }
            //打印*的个数
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print("*");
            }
            //打印完* 记得换行
            System.out.println();
        }
        //打印下半部分
        for (int i = 0; i < line; i++) {
            //打印i个空格
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            //打印*的个数
            for (int j = 0; j < (2 * line - 1) - 2 * i; j++) {
                System.out.print("*");
            }
            //打印完* 记得换行
            System.out.println();
        }
    }
}
