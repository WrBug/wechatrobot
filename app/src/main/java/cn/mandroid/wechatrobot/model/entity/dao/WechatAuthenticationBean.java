package cn.mandroid.wechatrobot.model.entity.dao;

import android.text.TextUtils;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.HashMap;
import java.util.Map;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wrBug on 2017/1/1.
 */
@Entity
public class WechatAuthenticationBean {
    @Id
    private String uid;
    private String redirectUrl;
    private String baseUrl;
    private String skey;
    private String sid;
    private String uin;
    private String passTicket;
    private String cookie;
    private String baseRequestStr;
    @Transient
    private Map<String, String> baseRequest;

    @Generated(hash = 1254755090)
    public WechatAuthenticationBean(String uid, String redirectUrl, String baseUrl,
                                    String skey, String sid, String uin, String passTicket, String cookie,
                                    String baseRequestStr) {
        this.uid = uid;
        this.redirectUrl = redirectUrl;
        this.baseUrl = baseUrl;
        this.skey = skey;
        this.sid = sid;
        this.uin = uin;
        this.passTicket = passTicket;
        this.cookie = cookie;
        this.baseRequestStr = baseRequestStr;
    }

    @Generated(hash = 1672662466)
    public WechatAuthenticationBean() {
    }

    public Map<String, String> getBaseRequest() {
        if (baseRequest == null || baseRequest.isEmpty()) {
            if (!TextUtils.isEmpty(baseRequestStr)) {
                TypeToken<Map<String, String>> typeToken = new TypeToken<Map<String, String>>() {
                };
                baseRequest = new GsonBuilder().create().fromJson(baseRequestStr, typeToken.getType());
                if (baseRequest == null) {
                    baseRequest = new HashMap<>();
                }
            } else {
                baseRequest = new HashMap<>();
            }
        }
        return baseRequest;
    }

    public void setBaseRequest() {
        this.baseRequest = new HashMap<>();
        this.baseRequest.put("Skey", this.skey);
        this.baseRequest.put("Sid", this.sid);
        this.baseRequest.put("Uin", this.uin);
        this.baseRequest.put("DeviceID", createDeviceId());
        this.baseRequestStr = new GsonBuilder().create().toJson(this.baseRequest);
    }

    private String createDeviceId() {
        return "e" + (long) (System.currentTimeMillis() * 100l + Math.random() * 100l);
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    public String getPassTicket() {
        return passTicket;
    }

    public void setPassTicket(String passTicket) {
        this.passTicket = passTicket;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBaseRequestStr() {
        return this.baseRequestStr;
    }

    public void setBaseRequestStr(String baseRequestStr) {
        this.baseRequestStr = baseRequestStr;
    }
}
