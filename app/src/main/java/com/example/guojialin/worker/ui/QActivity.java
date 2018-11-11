package com.example.guojialin.worker.ui;

import android.os.Bundle;

import com.example.guojialin.worker.R;
import com.example.guojialin.worker.base.BaseActivity;
import com.example.guojialin.worker.base.GetRequest_Interface;
import com.example.guojialin.worker.base.PostRequest_Interface;
import com.example.guojialin.worker.bean.Book;
import com.example.guojialin.worker.bean.ResultMessage;
import com.example.guojialin.worker.bean.Translation;
import com.example.guojialin.worker.bean.Translation1;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q);
//        request();
//         request2();
        request3();

        //使用Retrofit封装方法
    }
public void request3(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.199.213:8080/books/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetRequest_Interface request_interface = retrofit.create(GetRequest_Interface.class);
        Call<ResultMessage<Book>> call = request_interface.getBook();
        call.enqueue(new Callback<ResultMessage<Book>>() {
            @Override
            public void onResponse(Call<ResultMessage<Book>> call, Response<ResultMessage<Book>> response) {
                Book book = response.body().getData();
                System.out.println(book.toString());
            }

            @Override
            public void onFailure(Call<ResultMessage<Book>> call, Throwable t) {
                System.out.println("请求失败");
            }
        });
}

    public void request() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") //设置网络请求 URL
                .addConverterFactory(GsonConverterFactory.create())
                //使用GSon 解析
                .build();
        //创建网络请求接口实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
        //对发送请求进行封装
        Call<Translation> call = request.getCall();
        //发送异步网络请求
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                response.body().show();
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                System.out.println("连接失败");
            }
        });

    }

    public void request2() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        PostRequest_Interface request = retrofit.create(PostRequest_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        Call<Translation1> call = request.getCall("I love you");

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation1>() {

            //请求成功时回调
            @Override
            public void onResponse(Call<Translation1> call, Response<Translation1> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                System.out.println(response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation1> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
            }
        });
    }
}
