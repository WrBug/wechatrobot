package cn.mandroid.wechatrobot.model.repository;

import android.text.TextUtils;

import java.io.File;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.mandroid.wechatrobot.model.common.BaseCloudSource;
import cn.mandroid.wechatrobot.model.entity.BaseRequestBean;
import cn.mandroid.wechatrobot.model.entity.ShortUrlBean;
import cn.mandroid.wechatrobot.model.entity.wechat.WechatSyncKeyBean;
import cn.mandroid.wechatrobot.model.entity.wechat.WechatUiDataBean;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;
import cn.mandroid.wechatrobot.utils.Constant;
import cn.mandroid.wechatrobot.utils.MLog;
import cn.mandroid.wechatrobot.utils.RegexUtil;
import rx.Subscriber;

/**
 * Created by wrBug on 2017/1/1.
 */

public class WechatAuthenticationCloudSource extends BaseCloudSource implements IWechatAuthenticationCloudSource {
    @Override
    public void getUUID(final GetUUIDCallback callback) {
        String url = "https://login.weixin.qq.com/jslogin";
        getQequestBuilder().addQuery("appid", "wx782c26e4c19acffb")
                .addQuery("fun", "new")
                .addQuery("lang", "zh_CN")
                .addQuery("_", "" + System.currentTimeMillis())
                .doOtherApiGet(url, new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError();
                        MLog.e(e.getMessage());
                    }

                    @Override
                    public void onNext(String result) {
                        Pattern pattern = Pattern.compile(RegexUtil.WECHAT_AUTHENTICATION_GET_UUID);
                        Matcher match = pattern.matcher(result);
                        while (match.find()) {
                            if ("200".equals(match.group(2))) {
                                callback.onSuccess(match.group(3));
                                return;
                            }
                        }
                        callback.onError();
                    }
                });
    }

    @Override
    public void getQrcode(String uuid, final GetQrcodeCallback callback) {
        String url = "https://login.weixin.qq.com/qrcode/" + uuid;
        getQequestBuilder().addQuery("t", "webwx")
                .addQuery("_", "" + System.currentTimeMillis())
                .getFile(url, new Subscriber<File>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        MLog.e(e.getMessage());
                        callback.onError();
                    }

                    @Override
                    public void onNext(File file) {
                        callback.onSuccess(file);
                    }
                });
    }

    /**
     * 获取二维码短网址
     *
     * @param uuid
     * @param callback
     */
    @Override
    public void getShortUrl(String uuid, final GetShortUrlCallback callback) {
        StringBuilder builder = new StringBuilder("https://login.weixin.qq.com/qrcode/");
        builder.append(uuid).append("?").append("t=webwx").append("&").append("_=").append(System.currentTimeMillis());
        String imgUrl = builder.toString();
        getQequestBuilder().addQuery("url", imgUrl)
                .doOtherApiGet(Constant.getApiShortUrl(), new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(String s) {
                        if (!TextUtils.isEmpty(s)) {
                            ShortUrlBean shortUrlBean = mGson.fromJson(s, ShortUrlBean.class);
                            if (shortUrlBean.isSuccess()) {
                                callback.onSuccess(shortUrlBean.getUrl());
                            }
                        }
                    }
                });

    }

    @Override
    public void waitForLogin(String uuid, int tip, final WaitForLoginCallback callback) {
        String url = "https://login.weixin.qq.com/cgi-bin/mmwebwx-bin/login";
        getQequestBuilder().addQuery("tip", "" + tip)
                .addQuery("uuid", uuid)
                .addQuery("_", "" + System.currentTimeMillis())
                .doOtherApiGet(url, new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError();
                    }

                    @Override
                    public void onNext(String s) {
                        if (TextUtils.isEmpty(s)) {
                            callback.onError();
                            return;
                        }
                        Pattern pattern = Pattern.compile(RegexUtil.WECHAT_AUTHENTICATION_WAIT_FOR_LOGIN);
                        Matcher match = pattern.matcher(s);
                        while (match.find()) {
                            if ("201".equals(match.group(1))) {
                                callback.onSuccess(null, null);
                                return;
                            } else if ("200".equals(match.group(1))) {
                                pattern = Pattern.compile(RegexUtil.WECHAT_AUTHENTICATION_WAIT_FOR_LOGIN_REDIRECT_UTL);
                                match = pattern.matcher(s);
                                while (match.find()) {
                                    String redirectUrl = match.group(1) + "&fun=new";
                                    String baseUrl = redirectUrl.substring(0, redirectUrl.lastIndexOf("/"));
                                    callback.onSuccess(redirectUrl, baseUrl);
                                    return;
                                }
                            }
                            callback.onError();
                            return;
                        }
                        callback.onError();
                    }
                });
    }

    /**
     * 微信登录
     *
     * @param redirectUrl
     * @param callback
     */
    @Override
    public void wechatLogin(String redirectUrl, final WechatLoginCallback callback) {
        getQequestBuilder().noQuery().doOtherApiGet(redirectUrl, true, new Subscriber<BaseRequestBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onError();
            }

            @Override
            public void onNext(BaseRequestBean baseRequestBean) {
                String s = baseRequestBean.getResult();
                if (TextUtils.isEmpty(s)) {
                    callback.onError();
                    return;
                }
                String skey = getLoginData(s, "skey");
                String sid = getLoginData(s, "wxsid");
                String uin = getLoginData(s, "wxuin");
                String passTicket = getLoginData(s, "pass_ticket");
                callback.onSuccess(skey, sid, uin, passTicket, baseRequestBean.getCookie());
            }
        });
    }

    /**
     * 获取界面信息
     *
     * @param baseUrl
     * @param passTicket
     * @param skey
     * @param params
     * @param callback
     */
    @Override
    public void getWechatUserInfo(String baseUrl, String passTicket, String skey, Map<String, String> params, final GetWechatUiDataCallback callback) {
        String url = baseUrl + "/webwxinit?pass_ticket=" + passTicket + "&skey=" + skey + "&r=" + System.currentTimeMillis();
        postRequestBuilder().addParameter("BaseRequest", params)
                .doOtherApiPost(url, new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        MLog.e(e.getMessage());
                        callback.onError();
                    }

                    @Override
                    public void onNext(String s) {
                        if (TextUtils.isEmpty(s)) {
                            callback.onError();
                            return;
                        }
                        WechatUiDataBean wechatUiDataBean = mGson.fromJson(s, WechatUiDataBean.class);
                        if (wechatUiDataBean.getBaseResponse().isSuccess()) {
                            WechatUserBean userBean = wechatUiDataBean.getUser();
                            WechatSyncKeyBean wechatSyncKeyBean = wechatUiDataBean.getSyncKey();
                            callback.onSuccess(userBean, wechatSyncKeyBean);
                        } else {
                            callback.onError();
                        }
                    }
                });
    }

    private String getLoginData(String xml, String key) {
        String header = "<" + key + ">";
        String footer = "</" + key + ">";
        return xml.substring(xml.indexOf(header) + header.length(), xml.indexOf(footer));
    }
}
