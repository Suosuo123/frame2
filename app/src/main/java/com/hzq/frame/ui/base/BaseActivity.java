package com.hzq.frame.ui.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;

import com.hzq.frame.MainApplication;
import com.hzq.frame.R;
import com.hzq.frame.utils.ToastUtil;
import com.hzq.frame.widget.swipeBackLayout.ui.SwipeBackActivity;
import com.hzq.frame.widget.uiView.UIImageView;
import com.leaf.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;


public abstract class BaseActivity extends SwipeBackActivity {

    protected Activity mActivity;

    /**
     * 导航条
     */
    @Nullable
    @BindView(R.id.rel_bar)
    public RelativeLayout rel_bar;
    /**
     * 返回
     */
    @Nullable
    @BindView(R.id.iv_back)
    public UIImageView iv_back;
    /**
     * 标题
     */
    @Nullable
    @BindView(R.id.tv_title)
    public TextView tv_title;

    @Nullable
    @BindView(R.id.iv_right)
    public UIImageView iv_right;

    @Optional
    @OnClick(R.id.iv_back)
    public void backClick(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StatusBarUtil.setTransparentForWindow(this);
        StatusBarUtil.setLightMode(this);
        super.onCreate(savedInstanceState);
        mActivity = this;
        //统一管理activity
        MainApplication.getApplication().addActivity(mActivity);

        setContentView(getLayoutId());

        ButterKnife.bind(mActivity);

        setSwipeBackEnable(true);//禁止滑动关闭界面

        onCreate();
        initView();
        initData();

        if (null != rel_bar) {
            StatusBarUtil.setPaddingTop(mActivity, rel_bar);
        }
    }

    @Override
    protected void onDestroy() {
        //统一管理activity
        MainApplication.getApplication().removeActivity(mActivity);
        super.onDestroy();
    }


    protected abstract int getLayoutId();


    protected void onCreate() {
    }

    protected void initView() {
    }

    protected void initData() {
    }

    /**
     * 设置actionbar title
     *
     * @param title 页面标题
     */
    public void setActionTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            if (tv_title != null) {
                tv_title.setText(title);
            }
        }

    }

    public void setRightImg(int drawableId) {
        if (iv_right != null) {
            iv_right.setVisibility(View.VISIBLE);
            iv_right.setImageResource(drawableId);
        }
    }

    public ProgressDialog progressDialog;

    public ProgressDialog showProgressDialog() {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage("加载中");
        progressDialog.show();
        return progressDialog;
    }

    public ProgressDialog showProgressDialog(CharSequence message) {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage(message);
        progressDialog.show();
        return progressDialog;
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            // progressDialog.hide();会导致android.view.WindowLeaked
            progressDialog.dismiss();
        }
    }

    public void showMessage(CharSequence message) {
        ToastUtil.toast(mActivity, message);
    }

}
