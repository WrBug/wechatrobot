package cn.mandroid.wechatrobot.ui.activity.wechatlogin;

import cn.mandroid.wechatrobot.R;
import cn.mandroid.wechatrobot.model.common.Injection;
import cn.mandroid.wechatrobot.model.entity.dao.LoginWechatUser;
import cn.mandroid.wechatrobot.model.entity.dao.WechatAuthenticationBean;
import cn.mandroid.wechatrobot.model.entity.wechat.WechatSyncKeyBean;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;
import cn.mandroid.wechatrobot.model.repository.IWechatAuthenticationCloudSource;
import cn.mandroid.wechatrobot.model.repository.WechatAuthenticationRepository;
import cn.mandroid.wechatrobot.ui.activity.common.BasePresenter;
import cn.mandroid.wechatrobot.utils.MLog;

/**
 * Created by wrBug on 2017/1/1.
 */

public class WeChatLoginPresenter extends BasePresenter<WechatLoginContract.View> implements WechatLoginContract.Presenter {
    WechatAuthenticationRepository mWechatAuthenticationRepository;
    WechatAuthenticationBean mWechatAuthenticationBean;

    public WeChatLoginPresenter(WechatLoginContract.View view) {
        super(view);
        mWechatAuthenticationRepository = Injection.getWechatAuthenticationRepository();
        mWechatAuthenticationBean = new WechatAuthenticationBean();
    }

    @Override
    public void getUUID() {
        mView.setActionBarSubTitle("初始化中");
        mWechatAuthenticationRepository.getUUID(new IWechatAuthenticationCloudSource.GetUUIDCallback() {
            @Override
            public void onSuccess(String uuid) {
                getQrCode(uuid);
                getShortUrl(uuid);
                checkIsScanQrcode(uuid);
            }

            @Override
            public void onError() {
                mView.showLog("错误");
            }
        });
    }

    private void checkIsScanQrcode(final String uuid) {
        mWechatAuthenticationRepository.checkIsScanQrcode(uuid, new IWechatAuthenticationCloudSource.WaitForLoginCallback() {
            @Override
            public void onSuccess(String redirectUrl, String baseUrl) {
                mView.setActionBarSubTitle("扫码成功，请点击登录");
                checkIsClickLoginButton(uuid);
            }

            @Override
            public void onError() {
                getUUID();
            }
        });
    }


    private void checkIsClickLoginButton(String uuid) {
        mWechatAuthenticationRepository.checkIsClickLoginButton(uuid, new IWechatAuthenticationCloudSource.WaitForLoginCallback() {
            @Override
            public void onSuccess(String redirectUrl, String baseUrl) {
                mWechatAuthenticationBean.setRedirectUrl(redirectUrl);
                mWechatAuthenticationBean.setBaseUrl(baseUrl);
                mView.setActionBarSubTitle("正在登录");
                wechatLogin(redirectUrl);
            }

            @Override
            public void onError() {
                mView.setActionBarSubTitle("登录失败");
            }
        });
    }

    private void wechatLogin(String redirectUrl) {
        mWechatAuthenticationRepository.wechatLogin(redirectUrl, new IWechatAuthenticationCloudSource.WechatLoginCallback() {
            @Override
            public void onSuccess(String skey, String sid, long uin, String passTicket, String cookie) {
                mWechatAuthenticationBean.setSkey(skey);
                mWechatAuthenticationBean.setSid(sid);
                mWechatAuthenticationBean.setUin(uin);
                mWechatAuthenticationBean.setPassTicket(passTicket);
                mWechatAuthenticationBean.setCookie(cookie);
                mWechatAuthenticationBean.setBaseRequest();
                mView.setActionBarSubTitle("登录成功，正在获取用户信息");
                getWechatUiInfo();
            }

            @Override
            public void onError() {
                mView.setActionBarSubTitle("登录失败");
            }
        });
    }

    private void getWechatUiInfo() {
        mWechatAuthenticationRepository.getWechatUserInfo(mWechatAuthenticationBean.getBaseUrl(), mWechatAuthenticationBean.getPassTicket(),
                mWechatAuthenticationBean.getSkey(), mWechatAuthenticationBean.getBaseRequest(), new IWechatAuthenticationCloudSource.GetWechatUiDataCallback() {
                    @Override
                    public void onSuccess(WechatUserBean userBean, WechatSyncKeyBean syncKeyBean) {
                        mWechatAuthenticationBean.setUid(userBean.getUserName());
                        mWechatAuthenticationRepository.saveWechatAuthInfo(mWechatAuthenticationBean);
                        LoginWechatUser user = new LoginWechatUser(userBean.getUin());
                        user.setWechatUserBean(userBean);
                        mWechatAuthenticationRepository.saveLoginWechatUser(user);
                        mView.showToast(userBean.getNickName() + ",欢迎使用" + mView.getResources().getString(R.string.app_name));
                        mView.loginSuccess();
                    }

                    @Override
                    public void onError() {
                        mView.setActionBarSubTitle("获取用户信息失败,请重新登录");
                    }
                });
    }

    private void getShortUrl(String uuid) {
        mWechatAuthenticationRepository.getShortUrl(uuid, new IWechatAuthenticationCloudSource.GetShortUrlCallback() {
            @Override
            public void onSuccess(String url) {
                mView.setPcOpenNotice(url);
            }
        });
    }

    private void getQrCode(String uuid) {
        mView.setActionBarSubTitle("等待扫描");
        StringBuilder builder = new StringBuilder("https://login.weixin.qq.com/qrcode/");
        builder.append(uuid).append("?")
                .append("t=webwx").append("&")
                .append("_=").append(System.currentTimeMillis());
        mView.setQrcodeImage(builder.toString());
    }

    @Override
    public void start() {

    }
}
