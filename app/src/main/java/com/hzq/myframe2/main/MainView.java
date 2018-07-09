package com.hzq.myframe2.main;

import com.hzq.myframe2.base.BaseView;
import com.hzq.myframe2.modle.MainModel;
import com.hzq.myframe2.requestApi.SubjectResulte;

import java.util.List;

/**
 * Created by WuXiaolong on 2015/9/23.
 * 处理业务需要哪些方法
 * github:https://github.com/WuXiaolong/
 * 微信公众号：吴小龙同学
 * 个人博客：http://wuxiaolong.me/
 */
public interface MainView extends BaseView {

    void getDataSuccess(List<SubjectResulte> model);

    void getDataFail(String msg);

}
