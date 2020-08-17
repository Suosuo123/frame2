package com.hzq.frame.ui.contract;

import com.example.baselib.ui.BasePresenter;
import com.example.baselib.ui.BaseView;
import com.hzq.frame.entity.ResponseTest;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

/**
 */

public interface MainFragmentContract {
     interface MainView extends BaseView {
        void getDataSuccess(List<ResponseTest> model);

        void getDataFail(String msg);

    }
    interface Presenter extends BasePresenter<MainView> {
        void  loadDataByRetrofitRxjava(RxAppCompatActivity rxAppCompatActivity,boolean showProgress);
    }
}
