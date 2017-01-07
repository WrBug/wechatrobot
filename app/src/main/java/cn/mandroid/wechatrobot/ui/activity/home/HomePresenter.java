package cn.mandroid.wechatrobot.ui.activity.home;

import cn.mandroid.wechatrobot.model.common.Api;
import cn.mandroid.wechatrobot.model.common.Injection;
import cn.mandroid.wechatrobot.model.entity.dao.LoginWechatUser;
import cn.mandroid.wechatrobot.model.entity.dao.WechatAuthenticationBean;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;
import cn.mandroid.wechatrobot.model.entity.wechat.WechatContactVo;
import cn.mandroid.wechatrobot.model.repository.wechatauth.WechatAuthenticationRepository;
import cn.mandroid.wechatrobot.model.repository.wechatinfo.IWechatInfoCloudSource;
import cn.mandroid.wechatrobot.model.repository.wechatinfo.WechatInfoRepository;
import cn.mandroid.wechatrobot.ui.activity.common.BasePresenter;
import cn.mandroid.wechatrobot.utils.MLog;

/**
 * Created by wrBug on 2017/1/1.
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {
    private WechatAuthenticationRepository mWechatAuthenticationRepository;
    private WechatInfoRepository mWechatInfoRepository;
    private WechatUserBean mUser;
    private WechatAuthenticationBean mWechatAuthenticationBean;

    public HomePresenter(HomeContract.View view) {
        super(view);
        mWechatAuthenticationRepository = Injection.getWechatAuthenticationRepository();
        mWechatInfoRepository = Injection.getWechatInfoRepository();
    }

    @Override
    public void start() {

    }

    @Override
    public void loadUserInfo() {
        LoginWechatUser wechatUser = mWechatAuthenticationRepository.getLoginWechatUser();
        if (wechatUser == null) {
            mView.enterLoginActivity();
        } else {
            mUser = wechatUser.getWechatUserBean();
            if (mUser == null) {
                mView.enterLoginActivity();
            } else {
                getWechatAuthInfo(mUser.getUin());
            }
        }
    }

    private void getWechatAuthInfo(long uin) {
        mWechatAuthenticationBean = mWechatAuthenticationRepository.getWechatAuthInfo(uin);
        if (mWechatAuthenticationBean == null) {
            mView.enterLoginActivity();
        } else {
            mView.setNickname(mUser.getNickName());
            Api.setCookie(mWechatAuthenticationBean.getCookie());
            getContactor();
        }
    }

    private void getContactor() {
        mWechatInfoRepository.getWechatContactors(mWechatAuthenticationBean.getBaseUrl(), mWechatAuthenticationBean.getPassTicket(), mWechatAuthenticationBean.getSkey(), new IWechatInfoCloudSource.GetContactorsCallback() {
            @Override
            public void onSuccess(WechatContactVo wechatContactVo) {
                MLog.i(wechatContactVo.toJson());
            }

            @Override
            public void onLogin() {
                mView.enterLoginActivity();
            }

            @Override
            public void onError() {
                mView.showToast("用户获取失败");
            }
        });
    }
}
