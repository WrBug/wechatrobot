package cn.mandroid.wechatrobot.model.repository;

/**
 * Created by wrBug on 2016/12/21.
 */
public class WechatAuthenticationRepository {
    private static volatile WechatAuthenticationRepository INSTANCE;
    private IWechatAuthenticationCloudSource mWechatAuthenticationCloudSource;

    private WechatAuthenticationRepository() {
        mWechatAuthenticationCloudSource = new WechatAuthenticationCloudSource();
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
}
