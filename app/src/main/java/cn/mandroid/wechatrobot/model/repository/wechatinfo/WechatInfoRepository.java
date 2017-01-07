package cn.mandroid.wechatrobot.model.repository.wechatinfo;

/**
 * Created by wrBug on 2017/1/8.
 */

public class WechatInfoRepository {
    private static volatile WechatInfoRepository INSTANCE;
    private IWechatInfoLocalSource mWechatInfoLocalSource;
    private IWechatInfoCloudSource mWechatInfoCloudSource;

    private WechatInfoRepository() {
        mWechatInfoCloudSource = new WechatInfoCloudSource();
        mWechatInfoLocalSource = new WechatInfoLocalSource();
    }

    public static WechatInfoRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (WechatInfoRepository.class) {
                INSTANCE = new WechatInfoRepository();
            }
        }
        return INSTANCE;
    }

    public void getWechatContactors(String baseUrl, String passTicket, String skey, IWechatInfoCloudSource.GetContactorsCallback callback) {
        mWechatInfoCloudSource.getWechatContactors(baseUrl, passTicket, skey, callback);
    }
}
