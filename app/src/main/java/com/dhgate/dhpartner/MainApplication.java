package com.dhgate.dhpartner;

import android.app.Activity;
import android.app.Application;

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
