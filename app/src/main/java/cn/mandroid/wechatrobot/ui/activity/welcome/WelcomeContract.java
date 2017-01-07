package cn.mandroid.wechatrobot.ui.activity.welcome;

import cn.mandroid.wechatrobot.ui.activity.common.IBasePresenter;
import cn.mandroid.wechatrobot.ui.activity.common.IBaseView;

/**
 * Created by wrBug on 2017/1/8.
 */

public class WelcomeContract {
    interface View extends IBaseView {

        void enterHomeActivity();
    }

    interface Presenter extends IBasePresenter {
        void checkLoginCache();
    }
}
