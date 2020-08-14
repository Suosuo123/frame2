package com.hzq.myframe2.ui.activity;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hzq.myframe2.R;
import com.hzq.myframe2.base.BaseMvpActivity;
import com.hzq.myframe2.requestApi.ResultTest;
import com.hzq.myframe2.ui.contract.TestActivityContract;
import com.hzq.myframe2.ui.fragment.MainFragment;
import com.hzq.myframe2.ui.presenter.TestActivityPresenter;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class TestActivity extends BaseMvpActivity<TestActivityPresenter> implements TestActivityContract.TestView {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @BindView(R.id.iv_gif)
    ImageView iv_gif;

    @BindView(R.id.tv_result)
    TextView tvResult;

    @BindView(R.id.rel_fragment)
    RelativeLayout rel_fragment;

    @OnClick(R.id.btn_request)
    public void onViewClicked() {
        mPresenter.loadDataByRetrofitRxjava((RxAppCompatActivity) mActivity);
    }

    @Override
    protected void onCreate() {
        super.onCreate();
    }

    @Override
    protected void initView() {
        super.initView();

        setActionTitle("测试页面");

        getSupportFragmentManager().beginTransaction().add(R.id.rel_fragment, MainFragment.newInstance()).commit();

//        GlideApp.with(mActivity).asGif().load(R.mipmap.loading).placeholder(R.mipmap.ic_launcher)
//                .fitCenter().into(iv_gif);

//        CommonUtils.loadOneTimeGif(mActivity, R.mipmap.loading, iv_gif, new CommonUtils.GifListener() {
//            @Override
//            public void gifPlayComplete() {
//                LogUtils.d("播放完毕");
//            }
//        });

    }

    @Override
    protected void initData() {
        super.initData();

        String[] names = {"111", "222", "333"};
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String name) {

                    }
                });

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        }).subscribe();

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

        });

        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
            }
        };

    }


    @Override
    protected TestActivityPresenter createPresenter() {
        return new TestActivityPresenter(mActivity);
    }

    @Override
    public void getDataSuccess(List<ResultTest> model) {
        tvResult.setText(model.toString());
    }

    @Override
    public void getDataFail(String msg) {

    }


}
