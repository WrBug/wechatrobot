package cn.mandroid.wechatrobot.model.repository;

import cn.mandroid.wechatrobot.model.common.BaseLocalSource;
import cn.mandroid.wechatrobot.model.entity.dao.LoginWechatUser;
import cn.mandroid.wechatrobot.model.entity.dao.WechatAuthenticationBean;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;

/**
 * Created by wrBug on 2017/1/2.
 */

public class WechatAuthenticationLocalSource extends BaseLocalSource implements IWechatAuthenticationLocalSource {
    /**
     * 保存微信登录信息
     *
     * @param bean
     */
    @Override
    public void saveWechatAuthInfo(WechatAuthenticationBean bean) {
        mDaoSession.getWechatAuthenticationBeanDao().insertOrReplace(bean);
    }

    /**
     * 保存用户信息
     *
     * @param wechatUserBean
     */
    @Override
    public void saveWechatUser(WechatUserBean wechatUserBean) {
        mDaoSession.getWechatUserBeanDao().insertOrReplace(wechatUserBean);
    }

    @Override
    public void saveLoginWechatUser(LoginWechatUser loginWechatUser) {

    }
}
