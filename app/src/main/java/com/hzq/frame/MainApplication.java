package com.hzq.frame;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.baselib.BaseApplication;

public class MainApplication extends BaseApplication {
    private static MainApplication mContext;

    public static MainApplication getApplication() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        if (BuildConfig.DEBUG) {
            ARouter.openLog();//打开日志
            ARouter.openDebug();//打开调式模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(mContext);
    }
}
