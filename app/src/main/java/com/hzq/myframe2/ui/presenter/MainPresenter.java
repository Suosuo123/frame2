package com.hzq.myframe2.ui.presenter;

import android.app.Activity;
import android.content.Context;

import com.hzq.myframe2.base.BaseRxPresenter;
import com.hzq.myframe2.requestApi.SubjectPostApi;
import com.hzq.myframe2.requestApi.SubjectResulte;
import com.hzq.myframe2.ui.contract.MainContract;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import java.util.List;


public class MainPresenter extends BaseRxPresenter<MainContract.MainView> implements MainContract.Presenter {

    private Context context;

    public MainPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void loadDataByRetrofitRxjava(RxAppCompatActivity rxAppCompatActivity) {
        //   回调一一对应
        HttpOnNextListener simpleOnNextListener = new HttpOnNextListener<List<SubjectResulte>>() {
            @Override
            public void onNext(List<SubjectResulte> model) {
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

        SubjectPostApi postEntity = new SubjectPostApi(simpleOnNextListener, rxAppCompatActivity);
        postEntity.setAll(true);
        mHttpManager.doHttpDeal(postEntity);
    }

}
