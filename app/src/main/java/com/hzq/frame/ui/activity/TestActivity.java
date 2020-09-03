package com.hzq.frame.ui.activity;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.baselib.ui.BaseMvpActivity;
import com.hzq.frame.R;
import com.hzq.frame.entity.ResponseTest;
import com.hzq.frame.jetpack.Java8Observer;
import com.hzq.frame.jetpack.NameViewModel;
import com.hzq.frame.jetpack.TimerViewModel;
import com.hzq.frame.ui.contract.TestActivityContract;
import com.hzq.frame.ui.presenter.TestActivityPresenter;

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

    @OnClick(R.id.btn_request)
    public void onViewClicked() {
//        mPresenter.loadDataByRetrofitRxjava((RxAppCompatActivity) mActivity);

        mNameViewModel.getCurrentName().setValue("李四");
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        getLifecycle().addObserver(new Java8Observer());

        iniComponent2();
    }

    @Override
    protected void initView() {
        super.initView();

        setActionTitle("测试页面");
    }

    NameViewModel mNameViewModel;


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
    public void getDataSuccess(List<ResponseTest> model) {
        tvResult.setText(model.toString());
    }

    @Override
    public void getDataFail(String msg) {

    }


    private void iniComponent() {
        //通过ViewModelProviders得到ViewModel，如果ViewModel不存在就创建一个新的，如果已经存在就直接返回已经存在的
        TimerViewModel timerViewModel = new ViewModelProvider(this).get(TimerViewModel.class);
        timerViewModel.setOnTimeChangeListener(new TimerViewModel.OnTimeChangeListener() {
            @Override
            public void onTimeChanged(final int second) {
                //更新UI界面
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvResult.setText("TIME:" + second);
                    }
                });
            }
        });

        timerViewModel.startTiming();
    }


    private void iniComponent2() {
        //通过ViewModelProviders得到ViewModel，如果ViewModel不存在就创建一个新的，如果已经存在就直接返回已经存在的
        mNameViewModel = new ViewModelProvider(this).get(NameViewModel.class);
        // 订阅LiveData中当前Name数据变化，以lambda形式定义Observer
        mNameViewModel.getCurrentName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String name) {
                tvResult.setText(name);
            }
        });
    }
}
