package cn.mandroid.wechatrobot.model.entity;

/**
 * Created by wrBug on 2017/1/2.
 */

public class BaseRequestBean {
    private String cookie;
    private String result;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
