package com.hzq.frame.ui.contract;

import com.example.baselib.ui.BasePresenter;
import com.example.baselib.ui.BaseView;
import com.hzq.frame.entity.ResponseTest;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

/**
 */
public interface TestActivityContract {
    interface TestView extends BaseView {
        void getDataSuccess(List<ResponseTest> model);

        void getDataFail(String msg);

    }

    interface Presenter extends BasePresenter<TestView> {
        void loadDataByRetrofitRxjava(RxAppCompatActivity rxAppCompatActivity);
    }
}
