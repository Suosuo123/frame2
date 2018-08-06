package com.dhgate.dhpartner.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dhgate.dhpartner.MainApplication;
import com.dhgate.dhpartner.R;
import com.dhgate.dhpartner.widget.WinToast;
import com.dhgate.dhpartner.widget.swipeBackLayout.ui.SwipeBackActivity;
import com.dhgate.dhpartner.widget.uiView.UIImageView;

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
        super.onCreate(savedInstanceState);
        mActivity = this;

        setContentView(getLayoutId());

        ButterKnife.bind(mActivity);

        initWindow(R.color.main_blue);

        setSwipeBackEnable(true);//禁止滑动关闭界面

        onCreate();
        initView();
        initData();

        //统一管理activity
        MainApplication.getApplication().addActivity(mActivity);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //统一管理activity
        MainApplication.getApplication().removeActivity(mActivity);
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


    public void initWindow(int colorResId) {

        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 19) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(ContextCompat.getColor(mActivity, colorResId));
//            window.setNavigationBarColor(getColor(colorResId));
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }

        //设置为浅色主题
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        }

//     window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    public void fullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
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
        WinToast.toast(mActivity, message);
    }

}
