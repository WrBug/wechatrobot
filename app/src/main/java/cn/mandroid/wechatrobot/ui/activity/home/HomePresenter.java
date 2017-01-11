package cn.mandroid.wechatrobot.ui.activity.home;

import java.util.ArrayList;
import java.util.List;

import cn.mandroid.wechatrobot.model.common.Api;
import cn.mandroid.wechatrobot.model.common.Injection;
import cn.mandroid.wechatrobot.model.entity.dao.LoginWechatUser;
import cn.mandroid.wechatrobot.model.entity.dao.WechatAuthenticationBean;
import cn.mandroid.wechatrobot.model.entity.dao.WechatMessage;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;
import cn.mandroid.wechatrobot.model.entity.turing.TuringRespVo;
import cn.mandroid.wechatrobot.model.entity.wechat.WechatContactVo;
import cn.mandroid.wechatrobot.model.entity.wechat.wechatmessage.WechatMessageBean;
import cn.mandroid.wechatrobot.model.repository.turingrobot.ITuringCloudSource;
import cn.mandroid.wechatrobot.model.repository.turingrobot.TuringRepostory;
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
    private TuringRepostory mTuringRepostory;

    public HomePresenter(HomeContract.View view) {
        super(view);
        mWechatAuthenticationRepository = Injection.getWechatAuthenticationRepository();
        mWechatInfoRepository = Injection.getWechatInfoRepository();
        mTuringRepostory = Injection.getTuringRepostory();
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
        List<WechatMessage> cache = new ArrayList<>();
        for (int i = 0; i < messageBean.getAddMsgList().size(); i++) {
            wechatMessages.get(i).setUin(mWechatAuthenticationBean.getUin());
            wechatMessages.get(i).setIsFromMine(wechatMessages.get(i).getFromUserName().equals(mUser.getUserName()));
            wechatMessages.get(i).setUserHash(mWechatInfoRepository.getLocalWechatContactor(wechatMessages.get(i).getFromUserName()).getHashCode());
            if (wechatMessages.get(i).getMsgType() != 51 && !wechatMessages.get(i).isGroupMessage()) {
                cache.add(wechatMessages.get(i));
            }
        }
        mWechatInfoRepository.saveWechatMessages(wechatMessages);
        mView.showWechatMessage(cache);
    }

    @Override
    public void getTuringResp(WechatMessageBean wechatMessage) {
        if (wechatMessage.getAddMsgCount() != 1) {
            return;
        }
        final WechatMessage message = wechatMessage.getAddMsgList().get(0);
        if (message.getMsgType() == WechatMessage.TEXT) {
            mTuringRepostory.getTuringResp(message.getFromUserName(), message.getContent(), new ITuringCloudSource.GetTuringRespCallback() {
                @Override
                public void onSuccess(TuringRespVo turingRespBean) {
                    String text = turingRespBean.getShowtext();
                    sendTextMessage(text, message.getFromUserName());
                }
            });
        }

    }

    private void sendTextMessage(String msg, String toUser) {
        msg += "\n[滔哥机器人自动回复]";
        mWechatInfoRepository.sendWechatTextMessage(mUser.getUserName(), toUser, msg, mWechatAuthenticationBean.getPassTicket(), mWechatAuthenticationBean.getBaseRequest(), new IWechatInfoCloudSource.SendMessageCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
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
