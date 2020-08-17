package com.example.baselib.net;

import com.example.baselib.constants.ConstantData;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import rx.Observable;

public abstract class BaseRequest extends BaseApi {

    public Map<String, String> mBaseParams = new HashMap<>();

    public BaseRequest(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setBaseUrl(ConstantData.DEFAULT_HOST);
        setShowProgress(true);
        setCancel(true);
        setCache(false);
//        setMothed("AppFiftyToneGraph/videoLink");
//        setCookieNetWorkTime(60);
//        setCookieNoNetWorkTime(24 * 60 * 60);

        //添加请求的一些基本参数
//        mBaseParams.put("token", "123");
    }

    public void setParams(Map<String, String> params) {
        this.mBaseParams.putAll(params);
    }

    public Map<String, String> getParams() {
        return mBaseParams;
    }

    public abstract Observable getObservable(Retrofit retrofit);
}
