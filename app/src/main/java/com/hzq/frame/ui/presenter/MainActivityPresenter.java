package com.hzq.frame.ui.presenter;

import android.content.Context;

import com.example.baselib.ui.BaseRxPresenter;
import com.hzq.frame.ui.contract.MainActivityContract;


public class MainActivityPresenter extends BaseRxPresenter<MainActivityContract.MainView> implements MainActivityContract.Presenter {

    private Context context;

    public MainActivityPresenter(Context context) {
        this.context = context;
    }

}
