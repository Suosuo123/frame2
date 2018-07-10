package com.hzq.myframe2.requestApi;

import com.hzq.myframe2.constants.ConstantData;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import retrofit2.Retrofit;
import rx.Observable;

public abstract class CommonRequest extends BaseApi{

    public CommonRequest(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setBaseUrl(ConstantData.DEFAULT_HOST);
        setShowProgress(true);
        setCancel(true);
        setCache(false);
//        setMothed("AppFiftyToneGraph/videoLink");
//        setCookieNetWorkTime(60);
//        setCookieNoNetWorkTime(24 * 60 * 60);
    }

    public abstract Observable getObservable(Retrofit retrofit);
}
