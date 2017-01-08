package cn.mandroid.wechatrobot.ui.widget.chatview;

import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;

/**
 * Created by wrBug on 2017/1/8.
 */

public interface IChatBubble {
    void setMessage(String msg);

    void setUser(WechatUserBean user);
}
