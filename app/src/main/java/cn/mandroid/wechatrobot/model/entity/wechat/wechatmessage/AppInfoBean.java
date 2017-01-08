package cn.mandroid.wechatrobot.model.entity.wechat.wechatmessage;

import java.io.Serializable;

/**
 * Created by wrBug on 2017/1/9.
 */

public class AppInfoBean implements Serializable{
    /**
     * AppID :
     * Type : 0
     */

    private String AppID;
    private int Type;

    public String getAppID() {
        return AppID;
    }

    public void setAppID(String AppID) {
        this.AppID = AppID;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

}
