package cn.mandroid.wechatrobot.model.common;

import cn.mandroid.wechatrobot.model.repository.WechatAuthenticationRepository;

/**
 * Created by wrBug on 2017/1/1.
 */

public class Injection {
    public static WechatAuthenticationRepository getWechatAuthenticationRepository() {
        return WechatAuthenticationRepository.getInstant();
    }
}
