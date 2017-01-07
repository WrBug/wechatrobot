package cn.mandroid.wechatrobot.ui.activity.home;

import java.io.File;

import cn.mandroid.wechatrobot.model.common.Injection;
import cn.mandroid.wechatrobot.model.repository.IWechatAuthenticationCloudSource;
import cn.mandroid.wechatrobot.model.repository.WechatAuthenticationRepository;
import cn.mandroid.wechatrobot.ui.activity.common.BasePresenter;
import cn.mandroid.wechatrobot.utils.MLog;

/**
 * Created by wrBug on 2017/1/1.
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {
    WechatAuthenticationRepository mWechatAuthenticationRepository;

    public HomePresenter(HomeContract.View view) {
        super(view);
        mWechatAuthenticationRepository = Injection.getWechatAuthenticationRepository();
    }

    @Override
    public void getUUID() {
        mWechatAuthenticationRepository.getUUID(new IWechatAuthenticationCloudSource.GetUUIDCallback() {
            @Override
            public void onSuccess(String uuid) {
                getQrCode(uuid);
            }

            @Override
            public void onError() {
                mView.showLog("错误");
            }
        });
    }

    private void getQrCode(String uuid) {
        mWechatAuthenticationRepository.getQrcode(uuid, new IWechatAuthenticationCloudSource.GetQrcodeCallback() {
            @Override
            public void onSuccess(File file) {
                MLog.i(file.getAbsoluteFile());
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void start() {

    }
}
