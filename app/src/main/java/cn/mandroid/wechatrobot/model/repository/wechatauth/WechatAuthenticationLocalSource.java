package cn.mandroid.wechatrobot.model.repository.wechatauth;

import cn.mandroid.wechatrobot.model.common.BaseLocalSource;
import cn.mandroid.wechatrobot.model.entity.dao.LoginWechatUser;
import cn.mandroid.wechatrobot.model.entity.dao.WechatAuthenticationBean;

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
    public void saveLoginWechatUser(LoginWechatUser wechatUserBean) {
        mDaoSession.getLoginWechatUserDao().insertOrReplace(wechatUserBean);
    }

    /**
     * 获取已登录用户信息
     *
     * @return
     */
    @Override
    public LoginWechatUser getLoginWechatUser() {
        return mDaoSession.getLoginWechatUserDao().load(1l);
    }

    /**
     * 获取登录信息
     *
     * @param uin
     * @return
     */
    @Override
    public WechatAuthenticationBean getWechatAuthInfo(long uin) {
        return mDaoSession.getWechatAuthenticationBeanDao().load(uin);
    }
}
