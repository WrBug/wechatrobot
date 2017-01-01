package cn.mandroid.wechatrobot.ui.activity.wechatlogin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

import cn.mandroid.wechatrobot.model.common.Injection;
import cn.mandroid.wechatrobot.model.repository.IWechatAuthenticationCloudSource;
import cn.mandroid.wechatrobot.model.repository.WechatAuthenticationRepository;
import cn.mandroid.wechatrobot.ui.activity.common.BasePresenter;
import cn.mandroid.wechatrobot.ui.activity.home.HomeConstract;
import cn.mandroid.wechatrobot.utils.MLog;

/**
 * Created by wrBug on 2017/1/1.
 */

public class WeChatLoginPresenter extends BasePresenter<WechatLoginContract.View> implements WechatLoginContract.Presenter {
    WechatAuthenticationRepository mWechatAuthenticationRepository;

    public WeChatLoginPresenter(WechatLoginContract.View view) {
        super(view);
        mWechatAuthenticationRepository = Injection.getWechatAuthenticationRepository();
    }

    @Override
    public void getUUID() {
        mView.setActionBarSubTitle("初始化中");
        mWechatAuthenticationRepository.getUUID(new IWechatAuthenticationCloudSource.GetUUIDCallback() {
            @Override
            public void onSuccess(String uuid) {
                getQrCode(uuid);
                getShortUrl(uuid);
            }

            @Override
            public void onError() {
                mView.showLog("错误");
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
        mView.setActionBarSubTitle("获取二维码");
        mWechatAuthenticationRepository.getQrcode(uuid, new IWechatAuthenticationCloudSource.GetQrcodeCallback() {
            @Override
            public void onSuccess(File file) {
                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                mView.setQrcodeImage(bitmap);
                mView.setActionBarSubTitle("等待扫描");
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
