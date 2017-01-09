package cn.mandroid.wechatrobot.model.repository.wechatinfo;

import java.util.List;
import java.util.Map;

import cn.mandroid.wechatrobot.model.entity.dao.WechatMessage;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;
import cn.mandroid.wechatrobot.model.entity.wechat.WechatSyncKeyBean;

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

    public void saveWechatContactors(List<WechatUserBean> userBeens) {
        mWechatInfoLocalSource.saveWechatContactors(userBeens);
    }

    public WechatUserBean getLocalWechatContactor(String username) {
        return mWechatInfoLocalSource.getLocalWechatContactor(username);
    }

    public void wechatMessageLoop(String baseUrl, String sid, String skey, long uin, String syncKey, String passTicket, Map<String, String> baseRequest, WechatSyncKeyBean syncKeyBean, IWechatInfoCloudSource.WechatMessageLoopCallback callback) {
        mWechatInfoCloudSource.wechatMessageLoop(baseUrl, sid, skey, uin, syncKey, passTicket, baseRequest, syncKeyBean, callback);
    }

    public void saveWechatMessages(List<WechatMessage> messages) {
        mWechatInfoLocalSource.saveWechatMessages(messages);
    }

    public List<WechatMessage> getWechatMessages(long uin) {
        return mWechatInfoLocalSource.getWechatMessages(uin);
    }
}
