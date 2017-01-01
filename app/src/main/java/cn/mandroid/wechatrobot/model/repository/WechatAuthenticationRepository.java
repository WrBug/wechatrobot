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

    public void getUUID(IWechatAuthenticationCloudSource.GetUUIDCallback callback) {
        mWechatAuthenticationCloudSource.getUUID(callback);
    }

    public void getQrcode(String uuid, IWechatAuthenticationCloudSource.GetQrcodeCallback callback) {
        mWechatAuthenticationCloudSource.getQrcode(uuid, callback);
    }

    public void getShortUrl(String uuid, IWechatAuthenticationCloudSource.GetShortUrlCallback callback) {
        mWechatAuthenticationCloudSource.getShortUrl(uuid, callback);
    }

    public void checkIsScanQrcode(String uuid, IWechatAuthenticationCloudSource.WaitForLoginCallback callback) {
        mWechatAuthenticationCloudSource.waitForLogin(uuid, 1, callback);
    }

    public void checkIsLogin(String uuid, IWechatAuthenticationCloudSource.WaitForLoginCallback callback) {
        mWechatAuthenticationCloudSource.waitForLogin(uuid, 0, callback);
    }

    private String getXmlData(String xml, String key) {
        String header = "<" + key + ">";
        String footer = "</" + key + ">";
        return xml.substring(xml.indexOf(header) + header.length(), xml.indexOf(footer));
    }
}
