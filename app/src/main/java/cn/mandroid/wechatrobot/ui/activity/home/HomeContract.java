package cn.mandroid.wechatrobot.ui.activity.home;

import java.util.List;

import cn.mandroid.wechatrobot.model.entity.dao.WechatAuthenticationBean;
import cn.mandroid.wechatrobot.model.entity.dao.WechatMessage;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;
import cn.mandroid.wechatrobot.model.entity.wechat.wechatmessage.WechatMessageBean;
import cn.mandroid.wechatrobot.ui.activity.common.IBasePresenter;
import cn.mandroid.wechatrobot.ui.activity.common.IBaseView;

/**
 * Created by wrBug on 2016/12/31.
 */

public class HomeContract {
    public interface View extends IBaseView {
        void setNickname(String nickname);

        void setAvatarImage(String url);

        void contactorsLoadFinished(WechatAuthenticationBean bean);

        void showWechatMessageCache(List<WechatMessage> messages);

        void showWechatMessage(List<WechatMessage> messages);
    }

    public interface Presenter extends IBasePresenter {
        void loadUserInfo();

        void saveMessages(WechatMessageBean messageBean);

        void getTuringResp(WechatMessageBean wechatMessage);
    }
}
