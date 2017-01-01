package cn.mandroid.wechatrobot.model.common;

import cn.mandroid.wechatrobot.WechatRobotApp;
import cn.mandroid.wechatrobot.gen.DaoSession;

/**
 * Created by wrBug on 2017/1/2.
 */

public class BaseLocalSource {
    protected DaoSession mDaoSession;

    public BaseLocalSource() {
        mDaoSession = WechatRobotApp.getDaoSession();
    }
}
