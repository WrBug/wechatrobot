package cn.mandroid.wechatrobot.model.repository;

import android.text.TextUtils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.mandroid.wechatrobot.model.common.BaseCloudSource;
import cn.mandroid.wechatrobot.model.entity.ShortUrlBean;
import cn.mandroid.wechatrobot.utils.DeviceUtil;
import cn.mandroid.wechatrobot.utils.FileUtil;
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
        mGetQequestBuilder.addQuery("appid", "wx782c26e4c19acffb")
                .addQuery("fun", "new")
                .addQuery("lang", "zh_CN")
                .addQuery("_", "" + System.currentTimeMillis())
                .doOtherApiGet(url, new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        MLog.e(e.getMessage());
                    }

                    @Override
                    public void onNext(String result) {
                        String regex = "(window.QRLogin.code = )([0-9]*); window.QRLogin.uuid = \"(\\S+?)\"";
                        Pattern pattern = Pattern.compile(regex);
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
        mGetQequestBuilder.addQuery("t", "webwx")
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
//                        String path = DeviceUtil.getSDPath() + "/DCIM/Camera";
//                        File rootDir = new File(path);
//                        if (!rootDir.exists() || !rootDir.isDirectory()) {
//                            rootDir.mkdirs();
//                        }
//                        File newFile = new File(rootDir, "qrcode.jpg");
//                        FileUtil.copyFile(file, newFile.getPath());
//                        newFile.setReadable(true);
//                        newFile.setWritable(true);
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
        String url = "http://a.mandroid.cn/api.php";
        StringBuilder builder = new StringBuilder("https://login.weixin.qq.com/qrcode/");
        builder.append(uuid).append("?").append("t=webwx").append("&").append("_=").append(System.currentTimeMillis());
        String imgUrl = builder.toString();
        mGetQequestBuilder.addQuery("url", imgUrl)
                .doOtherApiGet(url, new Subscriber<String>() {
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
        mGetQequestBuilder.addQuery("tip", "" + tip)
                .addQuery("uuid", uuid)
                .addQuery("_", "" + System.currentTimeMillis())
                .doOtherApiGet(url, new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

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
                                }
                            }
                        }
                    }
                });
    }
}
