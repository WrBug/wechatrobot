package cn.mandroid.wechatrobot.model.repository.wechatinfo;

import java.util.List;

import cn.mandroid.wechatrobot.model.common.BaseLocalSource;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;

/**
 * Created by wrBug on 2017/1/8.
 */

public class WechatInfoLocalSource extends BaseLocalSource implements IWechatInfoLocalSource {
    @Override
    public void saveWechatContactors(List<WechatUserBean> userBeens) {
        mDaoSession.getWechatUserBeanDao().insertOrReplaceInTx(userBeens);
    }

    @Override
    public WechatUserBean getLocalWechatContactor(String username) {
        WechatUserBean userBean = mDaoSession.getWechatUserBeanDao().load(username);
        return userBean;
    }
}
