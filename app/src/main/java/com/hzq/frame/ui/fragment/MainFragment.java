package com.hzq.frame.ui.fragment;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.baselib.ui.BaseMvpFragment;
import com.hzq.frame.R;
import com.hzq.frame.adapter.TestAdapter;
import com.hzq.frame.entity.ResponseTest;
import com.hzq.frame.ui.activity.TestActivity;
import com.hzq.frame.ui.activity.TestDataBindingActivity;
import com.hzq.frame.ui.contract.MainFragmentContract;
import com.hzq.frame.ui.presenter.MainFragmentPresenter;
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
    RecyclerView rv_test;

    private TestAdapter mAdapter;

    @Override
    protected void onCreate() {
        super.onCreate();
    }

    @Override
    protected void initView() {
        super.initView();

        mAdapter = new TestAdapter(mActivity);
        rv_test.setLayoutManager(new LinearLayoutManager(mActivity));
        rv_test.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 0) {
                    Intent intent = new Intent(mActivity, TestActivity.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(mActivity, TestDataBindingActivity.class);
                    startActivity(intent);
                }
            }
        });


        srl_test.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mPresenter.loadDataByRetrofitRxjava((RxAppCompatActivity) mActivity, false);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPresenter.loadDataByRetrofitRxjava((RxAppCompatActivity) mActivity, false);
            }
        });

    }

    @Override
    protected void initData() {
        super.initData();

        mPresenter.loadDataByRetrofitRxjava((RxAppCompatActivity) mActivity, true);
    }

    @Override
    public void getDataSuccess(List<ResponseTest> model) {
        srl_test.finishRefresh();
        srl_test.finishLoadMore();

        mAdapter.setNewData(model);
    }

    @Override
    public void getDataFail(String msg) {

    }
}
