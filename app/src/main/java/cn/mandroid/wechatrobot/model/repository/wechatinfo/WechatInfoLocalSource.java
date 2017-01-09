package cn.mandroid.wechatrobot.model.repository.wechatinfo;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import cn.mandroid.wechatrobot.gen.WechatMessageDao;
import cn.mandroid.wechatrobot.model.common.BaseLocalSource;
import cn.mandroid.wechatrobot.model.entity.dao.WechatMessage;
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

    @Override
    public void saveWechatMessages(List<WechatMessage> messages) {
        mDaoSession.getWechatMessageDao().insertOrReplaceInTx(messages);
    }

    @Override
    public List<WechatMessage> getWechatMessages(long uin) {
        WhereCondition.PropertyCondition condition = new WhereCondition.PropertyCondition(WechatMessageDao.Properties.FromUserName, "NOT LIKE ?", "%@@%");
        WhereCondition.PropertyCondition condition1 = new WhereCondition.PropertyCondition(WechatMessageDao.Properties.ToUserName, "NOT LIKE ?", "%@@%");
        List<WechatMessage> messages = mDaoSession.getWechatMessageDao().queryBuilder().where(WechatMessageDao.Properties.Uin.eq(uin), WechatMessageDao.Properties.MsgType.notEq(51), condition, condition1).list();
        return messages;
    }
}
