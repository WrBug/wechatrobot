package cn.mandroid.wechatrobot.model.repository.wechatinfo;

import cn.mandroid.wechatrobot.model.common.BaseResponseCallback;
import cn.mandroid.wechatrobot.model.entity.wechat.WechatContactVo;

/**
 * Created by wrBug on 2017/1/8.
 */

public interface IWechatInfoCloudSource {
    interface GetContactorsCallback extends BaseResponseCallback {
        void onSuccess(WechatContactVo wechatContactVo);

        void onLogin();
    }

    void getWechatContactors(String baseUrl, String passTicket, String skey, GetContactorsCallback callback);
}
