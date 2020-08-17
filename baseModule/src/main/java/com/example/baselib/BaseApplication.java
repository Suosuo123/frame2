package com.example.baselib;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import java.util.LinkedList;
import java.util.List;

public class BaseApplication extends MultiDexApplication {
    private static BaseApplication mContext;

    public static BaseApplication getApplication() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        registerActivityLifecycleCallbacks();
    }

    @Override
    protected void attachBaseContext(Context base) {
        MultiDex.install(this);
        super.attachBaseContext(base);
    }


    private int mCount;
    private boolean mFront;//是否前台


    /**
     * 判断应用处于前后台
     */
    private void registerActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                mCount++;
                if (!mFront) {
                    mFront = true;
//                    L.e("AppContext------->处于前台");
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                mCount--;
                if (mCount == 0) {
                    mFront = false;
//                    L.e("AppContext------->处于后台");
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
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
