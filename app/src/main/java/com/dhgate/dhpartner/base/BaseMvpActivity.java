package com.dhgate.dhpartner.base;

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView {
    protected P mPresenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate() {
        super.onCreate();
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        mPresenter.start();
    }

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
