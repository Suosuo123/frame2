package com.hzq.myframe2.activity;

import android.Manifest;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.hzq.myframe2.R;
import com.hzq.myframe2.base.MvpActivity;
import com.hzq.myframe2.main.MainPresenter;
import com.hzq.myframe2.main.MainView;
import com.hzq.myframe2.requestApi.SubjectResulte;
import com.hzq.myframe2.utils.CommonUtils;
import com.hzq.myframe2.utils.log.LogUtils;
import com.master.permissionhelper.PermissionHelper;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @BindView(R.id.iv_gif)
    ImageView iv_gif;

    @BindView(R.id.tv_result)
    TextView tvResult;

    @OnClick(R.id.btn_request)
    public void onViewClicked() {
        //请求接口
        mvpPresenter.loadDataByRetrofitRxjava((RxAppCompatActivity) mActivity);
    }

    private PermissionHelper permissionHelper;

    @Override
    protected void onCreate() {
        super.onCreate();

        permissionHelper = new PermissionHelper(this, new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
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

//        GlideApp.with(mActivity).asGif().load(R.mipmap.loading).placeholder(R.mipmap.ic_launcher)
//                .fitCenter().into(iv_gif);

        CommonUtils.loadOneTimeGif(mActivity, R.mipmap.loading, iv_gif, new CommonUtils.GifListener() {
            @Override
            public void gifPlayComplete() {
                LogUtils.d("播放完毕");
            }
        });

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
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void getDataSuccess(List<SubjectResulte> model) {
        tvResult.setText(model.toString());
    }

    @Override
    public void getDataFail(String msg) {

    }


}
