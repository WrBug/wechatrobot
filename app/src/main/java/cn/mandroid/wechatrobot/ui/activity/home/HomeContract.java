package cn.mandroid.wechatrobot.ui.activity.home;

import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;
import cn.mandroid.wechatrobot.ui.activity.common.IBasePresenter;
import cn.mandroid.wechatrobot.ui.activity.common.IBaseView;

/**
 * Created by wrBug on 2016/12/31.
 */

public class HomeContract {
    public interface View extends IBaseView {
        void setNickname(String nickname);

        void test(WechatUserBean userBean);

        void setAvatarImage(String url);
    }

    public interface Presenter extends IBasePresenter {
        void loadUserInfo();
    }
}
