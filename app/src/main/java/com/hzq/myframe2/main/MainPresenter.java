package com.hzq.myframe2.main;

import com.hzq.myframe2.base.BasePresenter;
import com.hzq.myframe2.modle.MainModel;
import com.hzq.myframe2.requestApi.SubjectPostApi;
import com.hzq.myframe2.requestApi.SubjectResulte;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import java.util.List;

/**
 * Created by WuXiaolong on 2015/9/23.
 * github:https://github.com/WuXiaolong/
 * 微信公众号：吴小龙同学
 * 个人博客：http://wuxiaolong.me/
 */
public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView view) {
        attachView(view);
    }

    public void loadDataByRetrofitRxjava(RxAppCompatActivity rxAppCompatActivity) {
//        mvpView.showLoading();

        //   回调一一对应
        HttpOnNextListener simpleOnNextListener = new HttpOnNextListener<List<SubjectResulte>>() {
            @Override
            public void onNext(List<SubjectResulte> model) {
//                mvpView.hideLoading();
                mvpView.getDataSuccess(model);
            }

            /*缓存回调*/
            @Override
            public void onCacheNext(String cache) {
            }

            /*用户主动调用，默认是不需要覆写该方法*/
            @Override
            public void onError(Throwable e) {
                super.onError(e);
//                mvpView.hideLoading();
                mvpView.getDataFail(e.toString());
            }

            /*用户主动调用，默认是不需要覆写该方法*/
            @Override
            public void onCancel() {
                super.onCancel();
//                mvpView.hideLoading();
            }
        };

        SubjectPostApi postEntity = new SubjectPostApi(simpleOnNextListener, rxAppCompatActivity);
        postEntity.setAll(true);
        mHttpManager.doHttpDeal(postEntity);
    }

}
