package com.dhgate.dhpartner.requestApi;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * 测试数据
 */
public class RequestTest extends BaseRequest {
    private boolean showProgress;

    @Override
    public boolean isShowProgress() {
        return showProgress;
    }

    @Override
    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    /**
     * 默认初始化需要给定回调和rx周期类
     * 可以额外设置请求设置加载框显示，回调等（可扩展）
     *
     * @param listener
     * @param rxAppCompatActivity
     */
    public RequestTest(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setShowProgress(isShowProgress());
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService service = retrofit.create(HttpPostService.class);
        return service.getTestData(getParams());
    }
}
