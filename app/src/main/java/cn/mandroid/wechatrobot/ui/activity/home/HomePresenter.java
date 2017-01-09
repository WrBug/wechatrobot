package cn.mandroid.wechatrobot.ui.activity.home;

import java.util.ArrayList;
import java.util.List;

import cn.mandroid.wechatrobot.model.common.Api;
import cn.mandroid.wechatrobot.model.common.Injection;
import cn.mandroid.wechatrobot.model.entity.dao.LoginWechatUser;
import cn.mandroid.wechatrobot.model.entity.dao.WechatAuthenticationBean;
import cn.mandroid.wechatrobot.model.entity.dao.WechatMessage;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;
import cn.mandroid.wechatrobot.model.entity.wechat.WechatContactVo;
import cn.mandroid.wechatrobot.model.entity.wechat.wechatmessage.WechatMessageBean;
import cn.mandroid.wechatrobot.model.repository.wechatauth.WechatAuthenticationRepository;
import cn.mandroid.wechatrobot.model.repository.wechatinfo.IWechatInfoCloudSource;
import cn.mandroid.wechatrobot.model.repository.wechatinfo.WechatInfoRepository;
import cn.mandroid.wechatrobot.ui.activity.common.BasePresenter;

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

    @Override
    public void saveMessages(WechatMessageBean messageBean) {
        List<WechatMessage> wechatMessages = messageBean.getAddMsgList();
        List<WechatMessage> cache = new ArrayList<>(wechatMessages);
        for (int i = 0; i < messageBean.getAddMsgList().size(); i++) {
            wechatMessages.get(i).setUin(mWechatAuthenticationBean.getUin());
            wechatMessages.get(i).setIsFromMine(wechatMessages.get(i).getFromUserName().equals(mUser.getUserName()));
            if (wechatMessages.get(i).getMsgType() == 51||wechatMessages.get(i).isGroupMessage()) {
                cache.remove(wechatMessages.get(i));
            }
        }
        mWechatInfoRepository.saveWechatMessages(wechatMessages);
        mView.showWechatMessage(cache);
    }

    private void getWechatAuthInfo(long uin) {
        mWechatAuthenticationBean = mWechatAuthenticationRepository.getWechatAuthInfo(uin);
        if (mWechatAuthenticationBean == null) {
            mView.enterLoginActivity();
        } else {
            Api.setCookie(mWechatAuthenticationBean.getCookie());
            mView.setNickname(mUser.getNickName());
            mView.setAvatarImage(mUser.getHeadImgUrl());
            List<WechatMessage> cacheMsg = mWechatInfoRepository.getWechatMessages(mUser.getUin());
            mView.showWechatMessageCache(cacheMsg);
            getContactor();
        }
    }

    private void getContactor() {
        mWechatInfoRepository.getWechatContactors(mWechatAuthenticationBean.getBaseUrl(), mWechatAuthenticationBean.getPassTicket(), mWechatAuthenticationBean.getSkey(), new IWechatInfoCloudSource.GetContactorsCallback() {
            @Override
            public void onSuccess(WechatContactVo wechatContactVo) {
                if (wechatContactVo.getMemberCount() > 0) {
                    mWechatInfoRepository.saveWechatContactors(wechatContactVo.getMemberList());
                }
                mView.showToast("联系人列表获取成功");
                mView.contactorsLoadFinished(mWechatAuthenticationBean);
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
