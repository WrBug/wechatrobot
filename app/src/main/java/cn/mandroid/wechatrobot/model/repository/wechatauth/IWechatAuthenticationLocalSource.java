package cn.mandroid.wechatrobot.model.repository.wechatauth;

import cn.mandroid.wechatrobot.model.entity.dao.LoginWechatUser;
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
     *
     * @param wechatUserBean
     */
    void saveLoginWechatUser(LoginWechatUser wechatUserBean);

    /**
     * 获取已登录用户信息
     *
     * @return
     */
    LoginWechatUser getLoginWechatUser();

    /**
     * 获取登录信息
     *
     * @param uin
     * @return
     */
    WechatAuthenticationBean getWechatAuthInfo(long uin);
}
