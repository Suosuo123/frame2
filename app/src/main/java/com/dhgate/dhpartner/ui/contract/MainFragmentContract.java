package com.dhgate.dhpartner.ui.contract;

import com.dhgate.dhpartner.base.BasePresenter;
import com.dhgate.dhpartner.base.BaseView;
import com.dhgate.dhpartner.requestApi.ResultTest;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

/**
 */

public interface MainFragmentContract {
     interface MainView extends BaseView {
        void getDataSuccess(List<ResultTest> model);

        void getDataFail(String msg);

    }
    interface Presenter extends BasePresenter<MainView> {
        void  loadDataByRetrofitRxjava(RxAppCompatActivity rxAppCompatActivity,boolean showProgress);
    }
}
