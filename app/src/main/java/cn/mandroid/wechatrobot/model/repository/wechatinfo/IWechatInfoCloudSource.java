package cn.mandroid.wechatrobot.model.repository.wechatinfo;

import java.util.Map;

import cn.mandroid.wechatrobot.model.common.BaseResponseCallback;
import cn.mandroid.wechatrobot.model.entity.wechat.WechatContactVo;
import cn.mandroid.wechatrobot.model.entity.wechat.WechatSyncKeyBean;
import cn.mandroid.wechatrobot.model.entity.wechat.wechatmessage.WechatMessageBean;

/**
 * Created by wrBug on 2017/1/8.
 */

public interface IWechatInfoCloudSource {
    interface GetContactorsCallback extends BaseResponseCallback {
        void onSuccess(WechatContactVo wechatContactVo);

        void onLogin();
    }

    interface WechatMessageLoopCallback {
        void onSuccess(WechatMessageBean vo);

        void onEmpty();
        void onNoNewMessage();
        void onAuthFailed();
    }

    /**
     * 获取微信联系人列表
     *
     * @param baseUrl
     * @param passTicket
     * @param skey
     * @param callback
     */
    void getWechatContactors(String baseUrl, String passTicket, String skey, GetContactorsCallback callback);

    /**
     * 新消息查询
     *
     * @param sid
     * @param skey
     * @param uin
     * @param syncKey
     * @param callback
     */
    void wechatMessageLoop(String baseUrl, String sid, String skey, long uin, String syncKey, String passTicket, Map<String, String> baseRequest, WechatSyncKeyBean syncKeyBean, WechatMessageLoopCallback callback);
}
