package com.hzq.frame.ui.base;

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements BaseView {

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
    protected void onFragmentDestroy() {
        super.onFragmentDestroy();
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

