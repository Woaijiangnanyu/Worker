package com.example.guojialin.worker.ui;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.guojialin.worker.R;

public class RActivity extends Activity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r);
        webView = (WebView) findViewById(R.id.wv);
        setWebView();
    }

    private void setWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);  //读取js 节点 很关键！！！！
        webView.addJavascriptInterface(new ShowToast(),"toastandroid");
        webView.loadUrl("file:///android_asset/index.html");
    }

    public final class ShowToast{
        @JavascriptInterface
        public void show(){
            Toast.makeText(RActivity.this,"js->java",Toast.LENGTH_LONG).show();
        }
    }
}
