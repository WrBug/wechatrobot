package cn.mandroid.wechatrobot.model.repository.wechatinfo;

import android.text.TextUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.mandroid.wechatrobot.model.common.BaseCloudSource;
import cn.mandroid.wechatrobot.model.common.HttpBody;
import cn.mandroid.wechatrobot.model.entity.dao.WechatAuthenticationBean;
import cn.mandroid.wechatrobot.model.entity.wechat.WechatContactVo;
import cn.mandroid.wechatrobot.model.entity.wechat.WechatSyncKeyBean;
import cn.mandroid.wechatrobot.model.entity.wechat.wechatmessage.WechatMessageBean;
import cn.mandroid.wechatrobot.utils.MLog;
import cn.mandroid.wechatrobot.utils.RegexUtil;
import rx.Subscriber;

/**
 * Created by wrBug on 2017/1/8.
 */

public class WechatInfoCloudSource extends BaseCloudSource implements IWechatInfoCloudSource {

    @Override
    public void getWechatContactors(String baseUrl, String passTicket, String skey, final GetContactorsCallback callback) {
        String url = baseUrl + "/webwxgetcontact";
        getQequestBuilder().addQuery("pass_ticket", passTicket)
                .addQuery("skey", skey)
                .addQuery("r", System.currentTimeMillis() + "")
                .doOtherApiGet(url, new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        MLog.e(e);
                        callback.onError();
                    }

                    @Override
                    public void onNext(String s) {
                        if (!TextUtils.isEmpty(s)) {
                            WechatContactVo contactVo = mGson.fromJson(s, WechatContactVo.class);
                            if (contactVo.getBaseResponse().isSuccess()) {
                                callback.onSuccess(contactVo);
                            } else {
                                callback.onLogin();
                            }
                        } else {
                            callback.onLogin();
                        }
                    }
                });
    }

    /**
     * 新消息查询
     *
     * @param sid
     * @param skey
     * @param uin
     * @param syncKey
     * @param callback
     */
    @Override
    public void wechatMessageLoop(String baseUrl, String sid, String skey, long uin, String syncKey, String passTicket, Map<String, String> baseRequest, WechatSyncKeyBean syncKeyBean, WechatMessageLoopCallback callback) {
        String url = "https://webpush.wx.qq.com/cgi-bin/mmwebwx-bin/synccheck";
        HttpBody.Query query = getQequestBuilder().addQuery("sid", sid)
                .addQuery("skey", skey)
                .addQuery("uin", String.valueOf(uin))
                .addQuery("deviceid", WechatAuthenticationBean.createDeviceId())
                .addQuery("synckey", syncKey)
                .addQuery("r", System.currentTimeMillis() + "")
                .addQuery("_", System.currentTimeMillis() + "");
        String result = query.doGet(url);
        if (TextUtils.isEmpty(result)) {
            callback.onAuthFailed();
            return;
        } else {
            Pattern pattern = Pattern.compile(RegexUtil.WECHAT_NEW_MESSAGE_LOOP);
            Matcher match = pattern.matcher(result);
            while (match.find()) {
                if ("0".equals(match.group(1))) {
                    if ("2".equals(match.group(2))) {
                        messageSync(baseUrl, sid, skey, passTicket, baseRequest, syncKeyBean, callback);
                    } else if ("0".equals(match.group(2))) {
                        callback.onNoNewMessage();
                    } else {
                        callback.onEmpty();
                    }
                    return;
                }
                callback.onAuthFailed();
            }
        }
    }

    private void messageSync(String baseUrl, String sid, String skey, String passTicket, Map<String, String> baseRequest, WechatSyncKeyBean syncKeyBean, final WechatMessageLoopCallback callback) {
        String url = baseUrl + "/webwxsync?sid=" + sid + "&skey=" + skey + "&pass_ticket=" + passTicket;
        postRequestBuilder().addParameter("BaseRequest", baseRequest)
                .addParameter("SyncKey", syncKeyBean)
                .addParameter("rr", System.currentTimeMillis())
                .doOtherApiPost(url, new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        MLog.e(e);
                    }

                    @Override
                    public void onNext(String s) {
                        if (!TextUtils.isEmpty(s)) {
                            WechatMessageBean vo = mGson.fromJson(s, WechatMessageBean.class);
                            callback.onSuccess(vo);
                        }
                    }
                });
    }
}
