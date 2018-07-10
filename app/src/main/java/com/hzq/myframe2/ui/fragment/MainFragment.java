package com.hzq.myframe2.ui.fragment;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.hzq.myframe2.R;
import com.hzq.myframe2.base.BaseMvpActivity;
import com.hzq.myframe2.base.BaseMvpFragment;
import com.hzq.myframe2.requestApi.ResultTest;
import com.hzq.myframe2.ui.contract.MainFragmentContract;
import com.hzq.myframe2.ui.presenter.MainFragmentPresenter;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment extends BaseMvpFragment<MainFragmentPresenter> implements MainFragmentContract.MainView {

    @NonNull
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected MainFragmentPresenter createPresenter() {
        return new MainFragmentPresenter(mActivity);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_main;
    }

    @BindView(R.id.tv_result)
    TextView tvResult;

    @OnClick(R.id.btn_request)
    public void requestClick() {
        mPresenter.loadDataByRetrofitRxjava((RxAppCompatActivity) mActivity);
    }


    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void getDataSuccess(List<ResultTest> model) {
        tvResult.setText(model.toString());
    }

    @Override
    public void getDataFail(String msg) {

    }
}
