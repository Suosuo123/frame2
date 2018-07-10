package com.hzq.myframe2.constants;


import com.hzq.myframe2.BuildConfig;

public class ConstantData {

    public static final String SP_FILE_NAME = "myframe";

    public static final String DEFAULT_HOST;

    static {
        if (BuildConfig.DEBUG) {
            DEFAULT_HOST = "https://www.izaodao.com/Api/";// 测试环境
        } else {
            DEFAULT_HOST = "https://www.izaodao.com/Api/";// 正式环境
        }
    }
}
