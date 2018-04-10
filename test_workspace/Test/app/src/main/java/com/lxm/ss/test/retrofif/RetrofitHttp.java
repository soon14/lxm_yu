package com.lxm.ss.test.retrofif;

import com.google.gson.GsonBuilder;
import com.lxm.ss.test.util.Zlog;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lxm on 2017/9/29.
 */

public class RetrofitHttp {

    public RetrofitService service ;

    public RetrofitHttp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://app.fromfactory.club/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();
        service = retrofit.create(RetrofitService.class);
    }

    public void getCategories() {

        Call<Categories> call =  service.getCategories();
        call.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                Zlog.ii("lxm getCategories:" +response.body().toString());
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {

            }
        });
    }

    public void updateVersion(String androidId) {
        //设置参数，访问url获取json
        Map<String, String> params = new HashMap<String, String>();
        params.put("jsonrpc", "2.0");
        params.put("method", "call");
        params.put("id", "183584515");
        params.put("parame", "");

        Call<HttpJsonResult> call = service.updateVersion(androidId,params);

        call.enqueue(new Callback<HttpJsonResult>() {
            @Override
            public void onResponse(Call<HttpJsonResult> call, Response<HttpJsonResult> response) {
                Zlog.ii("lxm updateVersion:" +response.body().toString());
            }

            @Override
            public void onFailure(Call<HttpJsonResult> call, Throwable t) {

            }
        });

    }



}
