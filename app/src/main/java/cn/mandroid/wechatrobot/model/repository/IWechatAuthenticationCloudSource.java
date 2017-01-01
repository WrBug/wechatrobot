package cn.mandroid.wechatrobot.model.repository;

import java.io.File;

import cn.mandroid.wechatrobot.model.common.BaseResponseCallback;

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
}
