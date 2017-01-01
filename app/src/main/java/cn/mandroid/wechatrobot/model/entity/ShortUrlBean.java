package cn.mandroid.wechatrobot.model.entity;

/**
 * Created by wrBug on 2017/1/1.
 */

public class ShortUrlBean extends BaseBean {

    /**
     * url : http://mandroid.cn/
     * code : 1
     */

    private String url;
    private int code;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isSuccess() {
        return code == 1;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
