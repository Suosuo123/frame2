package com.hzq.myframe2.base;

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.HttpManager;

/**
 */
public class BasePresenter<V> {

    public V mvpView;

    public HttpManager mHttpManager;

    public void attachView(V mvpView) {
        this.mvpView = mvpView;

        mHttpManager=HttpManager.getInstance();
    }


    public void detachView() {
        this.mvpView = null;
    }
}
