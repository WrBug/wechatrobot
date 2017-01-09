package cn.mandroid.wechatrobot.ui.widget.chatview;

import cn.mandroid.wechatrobot.model.entity.dao.WechatMessage;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;

/**
 * Created by wrBug on 2017/1/8.
 */

public interface IChatBubble {
    void setMessage(WechatMessage msg);

    void setUser(WechatUserBean user);

    void setNickname(String nickname);

    void setAvatar(String url);
}
