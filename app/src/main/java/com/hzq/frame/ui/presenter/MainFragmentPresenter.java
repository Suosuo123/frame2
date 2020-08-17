package com.hzq.frame.ui.presenter;

import android.content.Context;

import com.example.baselib.ui.BaseRxPresenter;
import com.hzq.frame.net.requestApi.RequestTest;
import com.hzq.frame.entity.ResponseTest;
import com.hzq.frame.ui.contract.MainFragmentContract;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainFragmentPresenter extends BaseRxPresenter<MainFragmentContract.MainView> implements MainFragmentContract.Presenter {

    private Context context;

    public MainFragmentPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void loadDataByRetrofitRxjava(RxAppCompatActivity rxAppCompatActivity,boolean showProgress) {
        //   回调一一对应
        HttpOnNextListener simpleOnNextListener = new HttpOnNextListener<List<ResponseTest>>() {
            @Override
            public void onNext(List<ResponseTest> model) {
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

        RequestTest requestTest = new RequestTest(simpleOnNextListener, rxAppCompatActivity);
        requestTest.setShowProgress(showProgress);
        Map<String, String> params = new HashMap<>();
//        params.put("once",String.valueOf(true));
        requestTest.setParams(params);
        mHttpManager.doHttpDeal(requestTest);
    }

}
