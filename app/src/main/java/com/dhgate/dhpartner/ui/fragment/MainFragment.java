package com.dhgate.dhpartner.ui.fragment;

import android.support.annotation.NonNull;
import android.widget.ListView;

import com.dhgate.dhpartner.R;
import com.dhgate.dhpartner.adapter.TestListAdapter;
import com.dhgate.dhpartner.base.BaseMvpFragment;
import com.dhgate.dhpartner.requestApi.ResultTest;
import com.dhgate.dhpartner.ui.contract.MainFragmentContract;
import com.dhgate.dhpartner.ui.presenter.MainFragmentPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

import butterknife.BindView;

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

    @BindView(R.id.srl_test)
    SmartRefreshLayout srl_test;

    @BindView(R.id.lv_test)
    ListView lv_test;

    private TestListAdapter mAdapter;

    @Override
    protected void onCreate() {
        super.onCreate();
    }

    @Override
    protected void initView() {
        super.initView();

        mAdapter = new TestListAdapter(mActivity);
        lv_test.setAdapter(mAdapter);

        srl_test.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mPresenter.loadDataByRetrofitRxjava((RxAppCompatActivity) mActivity,false);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPresenter.loadDataByRetrofitRxjava((RxAppCompatActivity) mActivity,false);
            }
        });

        mPresenter.loadDataByRetrofitRxjava((RxAppCompatActivity) mActivity,true);

    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void getDataSuccess(List<ResultTest> model) {
        srl_test.finishRefresh();
        srl_test.finishLoadMore();
        mAdapter.bindData(model);
    }

    @Override
    public void getDataFail(String msg) {

    }
}
