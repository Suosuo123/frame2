package com.hzq.myframe2.ui.activity;

import android.Manifest;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hzq.myframe2.R;
import com.hzq.myframe2.base.BaseMvpActivity;
import com.hzq.myframe2.requestApi.ResultTest;
import com.hzq.myframe2.ui.contract.TestActivityContract;
import com.hzq.myframe2.ui.fragment.MainFragment;
import com.hzq.myframe2.ui.presenter.TestActivityPresenter;
import com.hzq.myframe2.utils.log.LogUtils;
import com.master.permissionhelper.PermissionHelper;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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

    private PermissionHelper permissionHelper;

    @Override
    protected void onCreate() {
        super.onCreate();

        permissionHelper = new PermissionHelper(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        permissionHelper.request(new PermissionHelper.PermissionCallback() {
            @Override
            public void onPermissionGranted() {
                LogUtils.d("onPermissionGranted() called");
            }

            @Override
            public void onIndividualPermissionGranted(String[] grantedPermission) {
                LogUtils.d("onIndividualPermissionGranted() called with: grantedPermission = [" + TextUtils.join(",", grantedPermission) + "]");
            }

            @Override
            public void onPermissionDenied() {
                LogUtils.d("onPermissionDenied() called");

            }

            @Override
            public void onPermissionDeniedBySystem() {
                LogUtils.d("onPermissionDeniedBySystem() called");

            }
        });
    }

    @Override
    protected void initView() {
        super.initView();

        setActionBarWhite();
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
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionHelper != null) {
            permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
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
