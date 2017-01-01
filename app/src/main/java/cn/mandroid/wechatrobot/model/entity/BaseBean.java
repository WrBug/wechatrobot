package cn.mandroid.wechatrobot.model.entity;

import com.google.gson.GsonBuilder;

import java.io.Serializable;

/**
 * Created by wrBug on 2017/1/1.
 */

public class BaseBean implements Serializable {
    public String toJson() {
        return new GsonBuilder().create().toJson(this);
    }
}
