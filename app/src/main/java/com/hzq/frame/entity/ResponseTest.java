package com.hzq.frame.entity;

/**
 * 测试显示数据
 */
public class ResponseTest {

    /**
     * id : 0
     * name : qianyan.mp4
     * url : https://bj.bcebos.com/course-mct/media/qianyan.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2020-08-14T06%3A42%3A36Z%2F6000%2F%2Fe66c5d1138f7e2098273ae6887c594d92f4589c61bb037356b92157e29aff6f7
     * title : 前言
     */

    private int id;
    private String name;
    private String url;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
