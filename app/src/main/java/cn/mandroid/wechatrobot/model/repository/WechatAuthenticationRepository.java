package cn.mandroid.wechatrobot.model.repository;

import java.util.Map;

import cn.mandroid.wechatrobot.model.entity.dao.LoginWechatUser;
import cn.mandroid.wechatrobot.model.entity.dao.WechatAuthenticationBean;

/**
 * Created by wrBug on 2016/12/21.
 */
public class WechatAuthenticationRepository {
    private static volatile WechatAuthenticationRepository INSTANCE;
    private IWechatAuthenticationCloudSource mWechatAuthenticationCloudSource;
    private IWechatAuthenticationLocalSource mWechatAuthenticationLocalSource;

    private WechatAuthenticationRepository() {
        mWechatAuthenticationCloudSource = new WechatAuthenticationCloudSource();
        mWechatAuthenticationLocalSource = new WechatAuthenticationLocalSource();
    }

    public static WechatAuthenticationRepository getInstant() {
        if (INSTANCE == null) {
            INSTANCE = new WechatAuthenticationRepository();
        }
        return INSTANCE;
    }

    /**
     * 获取uuid
     *
     * @param callback
     */
    public void getUUID(IWechatAuthenticationCloudSource.GetUUIDCallback callback) {
        mWechatAuthenticationCloudSource.getUUID(callback);
    }

    /**
     * 获取二维码
     *
     * @param uuid
     * @param callback
     */
    public void getQrcode(String uuid, IWechatAuthenticationCloudSource.GetQrcodeCallback callback) {
        mWechatAuthenticationCloudSource.getQrcode(uuid, callback);
    }

    /**
     * 获取二维码短网址
     *
     * @param uuid
     * @param callback
     */
    public void getShortUrl(String uuid, IWechatAuthenticationCloudSource.GetShortUrlCallback callback) {
        mWechatAuthenticationCloudSource.getShortUrl(uuid, callback);
    }

    /**
     * 检查是否扫码
     *
     * @param uuid
     * @param callback
     */
    public void checkIsScanQrcode(String uuid, IWechatAuthenticationCloudSource.WaitForLoginCallback callback) {
        mWechatAuthenticationCloudSource.waitForLogin(uuid, 1, callback);
    }

    /**
     * 检查是否点击登录按钮
     *
     * @param uuid
     * @param callback
     */
    public void checkIsClickLoginButton(String uuid, IWechatAuthenticationCloudSource.WaitForLoginCallback callback) {
        mWechatAuthenticationCloudSource.waitForLogin(uuid, 0, callback);
    }

    /**
     * 微信登录
     *
     * @param redirectUrl
     * @param callback
     */
    public void wechatLogin(String redirectUrl, IWechatAuthenticationCloudSource.WechatLoginCallback callback) {
        mWechatAuthenticationCloudSource.wechatLogin(redirectUrl, callback);
    }

    /**
     * 获取微信用户信息
     *
     * @param baseUrl
     * @param passTicket
     * @param skey
     * @param params
     * @param callback
     */
    public void getWechatUserInfo(String baseUrl, String passTicket, String skey, Map<String, String> params, IWechatAuthenticationCloudSource.GetWechatUiDataCallback callback) {
        mWechatAuthenticationCloudSource.getWechatUserInfo(baseUrl, passTicket, skey, params, callback);
    }

    /**
     * 保存微信登录信息
     *
     * @param bean
     */
    public void saveWechatAuthInfo(WechatAuthenticationBean bean) {
        mWechatAuthenticationLocalSource.saveWechatAuthInfo(bean);
    }

    /**
     * 获取登录信息
     *
     * @param uin
     * @return
     */
    public WechatAuthenticationBean getWechatAuthInfo(long uin) {
        return mWechatAuthenticationLocalSource.getWechatAuthInfo(uin);
    }

    /**
     * 保存登录用户信息
     *
     * @param wechatUserBean
     */
    public void saveLoginWechatUser(LoginWechatUser wechatUserBean) {
        mWechatAuthenticationLocalSource.saveLoginWechatUser(wechatUserBean);
    }

    /**
     * 获取已登录用户信息
     *
     * @return
     */
    public LoginWechatUser getLoginWechatUser() {
        return mWechatAuthenticationLocalSource.getLoginWechatUser();
    }
}
