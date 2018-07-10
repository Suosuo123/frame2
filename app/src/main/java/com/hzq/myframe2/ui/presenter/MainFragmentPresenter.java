package com.hzq.myframe2.ui.presenter;

import android.content.Context;

import com.hzq.myframe2.base.BaseRxPresenter;
import com.hzq.myframe2.requestApi.RequestTest;
import com.hzq.myframe2.requestApi.ResultTest;
import com.hzq.myframe2.ui.contract.MainFragmentContract;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import java.util.List;


public class MainFragmentPresenter extends BaseRxPresenter<MainFragmentContract.MainView> implements MainFragmentContract.Presenter {

    private Context context;

    public MainFragmentPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void loadDataByRetrofitRxjava(RxAppCompatActivity rxAppCompatActivity) {
        //   回调一一对应
        HttpOnNextListener simpleOnNextListener = new HttpOnNextListener<List<ResultTest>>() {
            @Override
            public void onNext(List<ResultTest> model) {
                mView.getDataSuccess(model);
            }

            /*缓存回调*/
            @Override
            public void onCacheNext(String cache) {
            }

            /*用户主动调用，默认是不需要覆写该方法*/
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.getDataFail(e.toString());
            }

            /*用户主动调用，默认是不需要覆写该方法*/
            @Override
            public void onCancel() {
                super.onCancel();
            }
        };

        RequestTest postEntity = new RequestTest(simpleOnNextListener, rxAppCompatActivity);
        postEntity.setAll(true);
        mHttpManager.doHttpDeal(postEntity);
    }

}
