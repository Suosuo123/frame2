package com.hzq.myframe2.requestApi;

import java.util.List;

/**
 * 直接请求返回数据格式
 */
public class RetrofitEntity {
    private int ret;
    private String msg;
    private List<ResultTest> data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultTest> getData() {
        return data;
    }

    public void setData(List<ResultTest> data) {
        this.data = data;
    }
}
