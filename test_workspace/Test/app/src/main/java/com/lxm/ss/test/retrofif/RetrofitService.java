package com.lxm.ss.test.retrofif;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by lxm on 2017/9/29.
 */

public interface RetrofitService {

    @GET("filter/search")
    Call<Categories> getCategories();

    @FormUrlEncoded
    @POST("club_factory/version/android")
    Call<HttpJsonResult> updateVersion(@Query("version") String version_id, @FieldMap Map<String,String> map);




}
