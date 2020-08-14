package com.hzq.frame.net;

import com.hzq.frame.entity.ResponseTest;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseResultEntity;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 测试接口service-post相关
 */
public interface HttpPostService {

    @FormUrlEncoded
    @POST("AppFiftyToneGraph/videoLink")
    Observable<BaseResultEntity<List<ResponseTest>>> getTestData(@FieldMap Map<String, String> params);

}
