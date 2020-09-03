package com.hzq.frame.jetpack;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.example.baselib.utils.LogUtils;

/**
 * Created by Nate on 28/8/20.
 */
public class Java8Observer implements DefaultLifecycleObserver {
    private static final String TAG = Java8Observer.class.getSimpleName();

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        LogUtils.d("onCreate");
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        LogUtils.d("onStart");
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        LogUtils.d("onResume");
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        LogUtils.d("onPause");
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        LogUtils.d("onStop");
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        LogUtils.d("onDestroy");
    }
}
