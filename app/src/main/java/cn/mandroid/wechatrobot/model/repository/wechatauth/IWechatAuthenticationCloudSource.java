package cn.mandroid.wechatrobot.model.repository.wechatauth;

import java.io.File;
import java.util.Map;

import cn.mandroid.wechatrobot.model.common.BaseResponseCallback;
import cn.mandroid.wechatrobot.model.entity.wechat.WechatSyncKeyBean;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;

/**
 * Created by wrBug on 2017/1/1.
 */

public interface IWechatAuthenticationCloudSource {
    interface GetUUIDCallback extends BaseResponseCallback {
        void onSuccess(String uuid);
    }

    interface GetQrcodeCallback extends BaseResponseCallback {
        void onSuccess(File file);
    }

    interface GetShortUrlCallback {
        void onSuccess(String url);
    }

    interface WaitForLoginCallback extends BaseResponseCallback {
        void onSuccess(String redirectUrl, String baseUrl);
    }

    interface WechatLoginCallback extends BaseResponseCallback {
        void onSuccess(String skey, String sid, long uin, String passTicket, String cookie);
    }

    interface GetWechatUiDataCallback extends BaseResponseCallback {
        void onSuccess(WechatUserBean userBean, WechatSyncKeyBean syncKeyBean);
    }

    /**
     * 获取uuid
     *
     * @param callback
     */
    void getUUID(GetUUIDCallback callback);

    /**
     * 获取二维码
     *
     * @param uuid
     * @param callback
     */
    void getQrcode(String uuid, GetQrcodeCallback callback);

    /**
     * 获取二维码短网址
     *
     * @param uuid
     * @param callback
     */
    void getShortUrl(String uuid, GetShortUrlCallback callback);

    /**
     * 等待扫码登录
     *
     * @param uuid
     * @param tip
     * @param callback
     */
    void waitForLogin(String uuid, int tip, WaitForLoginCallback callback);

    /**
     * 微信登录
     *
     * @param redirectUrl
     * @param callback
     */
    void wechatLogin(String redirectUrl, WechatLoginCallback callback);


    /**
     * 获取界面信息
     *
     * @param baseUrl
     * @param passTicket
     * @param skey
     * @param params
     * @param callback
     */
    void getWechatUserInfo(String baseUrl, String passTicket, String skey, Map<String, String> params, GetWechatUiDataCallback callback);
}
