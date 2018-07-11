package com.hzq.myframe2.ui.presenter;

import android.content.Context;

import com.hzq.myframe2.base.BaseRxPresenter;
import com.hzq.myframe2.requestApi.RequestTest;
import com.hzq.myframe2.requestApi.ResultTest;
import com.hzq.myframe2.ui.contract.MainActivityContract;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivityPresenter extends BaseRxPresenter<MainActivityContract.MainView> implements MainActivityContract.Presenter {

    private Context context;

    public MainActivityPresenter(Context context) {
        this.context = context;
    }

}
