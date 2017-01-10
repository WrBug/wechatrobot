package cn.mandroid.wechatrobot.model.common;

import cn.mandroid.wechatrobot.model.repository.turingrobot.TuRingRepostory3;
import cn.mandroid.wechatrobot.model.repository.wechatauth.WechatAuthenticationRepository;
import cn.mandroid.wechatrobot.model.repository.wechatinfo.WechatInfoRepository;

/**
 * Created by wrBug on 2017/1/1.
 */

public class Injection {
    public static WechatAuthenticationRepository getWechatAuthenticationRepository() {
        return WechatAuthenticationRepository.getInstant();
    }

    public static WechatInfoRepository getWechatInfoRepository() {
        return WechatInfoRepository.getInstance();
    }

    public static TuRingRepostory3 getTuringRepostory() {
        return TuRingRepostory3.getInstance();
    }
}
