package com.dhgate.dhpartner.ui.presenter;

import android.content.Context;

import com.dhgate.dhpartner.base.BaseRxPresenter;
import com.dhgate.dhpartner.requestApi.RequestTest;
import com.dhgate.dhpartner.requestApi.ResultTest;
import com.dhgate.dhpartner.ui.contract.MainActivityContract;
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
