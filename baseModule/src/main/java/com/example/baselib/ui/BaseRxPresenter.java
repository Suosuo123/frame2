package com.example.baselib.ui;


import com.example.baselib.net.HttpManager;

public class BaseRxPresenter<T extends BaseView> implements BasePresenter<T> {
    protected T mView;
    protected HttpManager mHttpManager;


    @Override
    public void start() {

    }

    @Override
    public void attachView(T view) {
        this.mView = view;
        mHttpManager = HttpManager.getInstance();
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
