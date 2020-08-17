package com.hzq.frame.ui.contract;

import com.example.baselib.ui.BasePresenter;
import com.example.baselib.ui.BaseView;

/**
 */
public interface MainActivityContract {
     interface MainView extends BaseView {
//        void getDataSuccess(List<ResultTest> model);
//
//        void getDataFail(String msg);

    }
    interface Presenter extends BasePresenter<MainView> {
//        void  loadDataByRetrofitRxjava(RxAppCompatActivity rxAppCompatActivity);
    }
}
