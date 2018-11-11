package com.example.guojialin.worker.base;

import com.example.guojialin.worker.bean.Book;
import com.example.guojialin.worker.bean.ResultMessage;
import com.example.guojialin.worker.bean.Translation;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetRequest_Interface {

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<Translation> getCall();
    @GET("null")
    Call<ResultMessage<Book>> getBook();
}
