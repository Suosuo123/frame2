package com.hzq.frame;

import android.app.Activity;
import android.app.Application;


import com.alibaba.android.arouter.launcher.ARouter;

import java.util.LinkedList;
import java.util.List;

public class MainApplication extends Application {
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

    /**
     * 运用list来保存们每一个activity
     */
    public List<Activity> mActivityList = new LinkedList<Activity>();

    /**
     * add Activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        mActivityList.add(activity);
    }

    /**
     * remove Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        mActivityList.remove(activity);
    }

    /**
     * 关闭每一个list内的activity
     */
    public void finishAllActivities() {
        try {
            for (Activity activity : mActivityList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
