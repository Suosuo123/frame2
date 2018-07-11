package com.hzq.myframe2.ui.contract;

import com.hzq.myframe2.base.BasePresenter;
import com.hzq.myframe2.base.BaseView;
import com.hzq.myframe2.requestApi.ResultTest;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

/**
 * Created by caojun on 2018/5/31.
 */

public interface TestActivityContract {
    interface TestView extends BaseView {
        void getDataSuccess(List<ResultTest> model);

        void getDataFail(String msg);

    }

    interface Presenter extends BasePresenter<TestView> {
        void loadDataByRetrofitRxjava(RxAppCompatActivity rxAppCompatActivity);
    }
}
