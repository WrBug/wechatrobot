package cn.mandroid.wechatrobot.ui.activity.welcome;

import android.text.TextUtils;

import cn.mandroid.wechatrobot.model.common.Injection;
import cn.mandroid.wechatrobot.model.entity.dao.LoginWechatUser;
import cn.mandroid.wechatrobot.model.entity.dao.WechatAuthenticationBean;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;
import cn.mandroid.wechatrobot.model.repository.wechatauth.WechatAuthenticationRepository;
import cn.mandroid.wechatrobot.ui.activity.common.BasePresenter;

/**
 * Created by wrBug on 2017/1/8.
 */

public class WelcomePresenter extends BasePresenter<WelcomeContract.View> implements WelcomeContract.Presenter {
    WechatAuthenticationRepository mWechatAuthenticationRepository;

    public WelcomePresenter(WelcomeContract.View view) {
        super(view);
        mWechatAuthenticationRepository = Injection.getWechatAuthenticationRepository();
    }

    @Override
    public void start() {

    }

    @Override
    public void checkLoginCache() {
        LoginWechatUser wechatUser = mWechatAuthenticationRepository.getLoginWechatUser();
        if (wechatUser != null) {
            WechatUserBean wechatUserBean = wechatUser.getWechatUserBean();
            if (wechatUserBean != null) {
                getWechatAuthenticationInfo(wechatUserBean.getUin());
            } else {
                mView.enterLoginActivity();
            }
        } else {
            mView.enterLoginActivity();
        }
    }

    private void getWechatAuthenticationInfo(long uin) {
        WechatAuthenticationBean authenticationBean = mWechatAuthenticationRepository.getWechatAuthInfo(uin);
        if (authenticationBean != null && !TextUtils.isEmpty(authenticationBean.getCookie())) {
            mView.enterHomeActivity();
        } else {
            mView.enterLoginActivity();
        }
    }
}
