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

import cn.mandroid.wechatrobot.model.entity.BaseBean;
import cn.mandroid.wechatrobot.model.entity.wechat.WechatSyncKeyBean;

/**
 * Created by wrBug on 2017/1/1.
 */
@Entity
public class WechatAuthenticationBean extends BaseBean {
    private String uid;
    private String redirectUrl;
    private String baseUrl;
    private String skey;
    private String sid;
    @Id
    private long uin;
    private String passTicket;
    private String cookie;
    private String baseRequestStr;
    @Transient
    private Map<String, String> baseRequest;
    private String syncKey;
    private String syncKeyJson;

    @Generated(hash = 941076479)
    public WechatAuthenticationBean(String uid, String redirectUrl, String baseUrl, String skey, String sid, long uin,
                                    String passTicket, String cookie, String baseRequestStr, String syncKey, String syncKeyJson) {
        this.uid = uid;
        this.redirectUrl = redirectUrl;
        this.baseUrl = baseUrl;
        this.skey = skey;
        this.sid = sid;
        this.uin = uin;
        this.passTicket = passTicket;
        this.cookie = cookie;
        this.baseRequestStr = baseRequestStr;
        this.syncKey = syncKey;
        this.syncKeyJson = syncKeyJson;
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
        this.baseRequest.put("Uin", this.uin + "");
        this.baseRequest.put("DeviceID", createDeviceId());
        this.baseRequestStr = new GsonBuilder().create().toJson(this.baseRequest);
    }

    public String getSyncKey() {
        return syncKey;
    }

    public WechatSyncKeyBean getWechatSyncKeyBean() {
        if (!TextUtils.isEmpty(syncKeyJson)) {
            return new GsonBuilder().create().fromJson(syncKeyJson, WechatSyncKeyBean.class);
        }
        return null;
    }

    public void setSyncKey(WechatSyncKeyBean syncKey) {
        StringBuilder builder = new StringBuilder();
        for (WechatSyncKeyBean.ListBean listBean : syncKey.getList()) {
            builder.append(listBean.getKey()).append("_").append(listBean.getVal()).append("|");
        }
        builder.deleteCharAt(builder.length() - 1);
        this.syncKey = builder.toString();
        this.syncKeyJson = syncKey.toJson();
    }

    public static String createDeviceId() {
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

    public long getUin() {
        return uin;
    }

    public void setUin(long uin) {
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

    public void setSyncKey(String syncKey) {
        this.syncKey = syncKey;
    }

    public void setSyncKeyJson(String syncKeyJson) {
        this.syncKeyJson = syncKeyJson;
    }

    public String getSyncKeyJson() {
        return this.syncKeyJson;
    }
}
