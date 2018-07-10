package com.hzq.myframe2.base;

/**
 */
public interface BasePresenter<T extends BaseView> {

    /**
     * Presenter入口
     */
    void start();

    /**
     * 绑定View
     *
     * @param view
     */
    void attachView(T view);

    /**
     * 销毁View
     */
    void detachView() ;
}
