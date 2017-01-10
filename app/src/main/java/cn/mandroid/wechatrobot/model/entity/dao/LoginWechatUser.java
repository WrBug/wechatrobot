package cn.mandroid.wechatrobot.model.entity.dao;

import android.text.TextUtils;

import com.google.gson.GsonBuilder;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wrBug on 2017/1/2.
 */
@Entity
public class LoginWechatUser {
    @Transient
    private WechatUserBean mWechatUserBean;
    @Id
    private long id = 1;
    private long uin;
    private String wechatUser;

    @Generated(hash = 1141252029)
    public LoginWechatUser(long id, long uin, String wechatUser) {
        this.id = id;
        this.uin = uin;
        this.wechatUser = wechatUser;
    }

    public LoginWechatUser(long uin) {
        this.uin = uin;
    }

    @Generated(hash = 988988007)
    public LoginWechatUser() {
    }

    public WechatUserBean getWechatUserBean() {
        if (!TextUtils.isEmpty(wechatUser) && mWechatUserBean == null) {
            mWechatUserBean = new GsonBuilder().create().fromJson(wechatUser, WechatUserBean.class);
        }
        return mWechatUserBean;
    }

    public void setWechatUserBean(WechatUserBean wechatUserBean) {
        mWechatUserBean = wechatUserBean;
        wechatUser = mWechatUserBean.toJson();
    }

    public long getUin() {
        return this.uin;
    }

    public void setUin(long uin) {
        this.uin = uin;
    }

    public String getWechatUser() {
        return this.wechatUser;
    }

    public void setWechatUser(String wechatUser) {
        this.wechatUser = wechatUser;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
