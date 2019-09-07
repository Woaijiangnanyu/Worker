package com.example.guojialin.worker.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.guojialin.worker.R;
import com.example.guojialin.worker.utils.FileUtils;
import com.example.guojialin.worker.utils.ProviderUtils;

public class MainActivity extends Activity {

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String action = intent.getAction();
        if (Intent.ACTION_SEND.equals(action)) {
            Uri uri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
//            Uri uri = intent.getData();
            String filename = uri.getPath();
            if (String.valueOf(uri) != null
                    && String.valueOf(uri).contains("content")) {
                boolean kkk = false;
                try {
                    filename = FileUtils.getFilePathFromContentUri(uri, this.getContentResolver());
                    Log.i(TAG, "file path : " + filename);
                    if (!FileUtils.isExists(filename)) {
                        kkk = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    kkk = true;
                }
                if (kkk) {
                    filename = ProviderUtils.getFPUriToPath(this, uri);
                    Log.i(TAG, "file path by invoke : " + filename);
                }
            } else {
                if (String.valueOf(uri) != null) {
                    Log.i(TAG, "file path with other app share : " + filename);
                }
            }
        }

    }
}
