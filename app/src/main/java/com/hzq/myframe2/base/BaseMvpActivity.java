package com.hzq.myframe2.base;

import android.os.Bundle;

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView {
    protected P mPresenter;


    @Override
    protected void onCreate() {
        super.onCreate();
        if (mPresenter != null) {
            mPresenter.attachView(this);
            mPresenter.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    @Override
    public void showToast(String msg) {
        showMessage(msg);
    }
}
