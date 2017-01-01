package cn.mandroid.wechatrobot.model.repository;

import cn.mandroid.wechatrobot.model.entity.dao.WechatAuthenticationBean;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;

/**
 * Created by wrBug on 2017/1/2.
 */

public interface IWechatAuthenticationLocalSource {
    /**
     * 保存微信登录信息
     *
     * @param bean
     */
    void saveWechatAuthInfo(WechatAuthenticationBean bean);

    /**
     * 保存用户信息
     * @param wechatUserBean
     */
    void saveWechatUser(WechatUserBean wechatUserBean);
}
