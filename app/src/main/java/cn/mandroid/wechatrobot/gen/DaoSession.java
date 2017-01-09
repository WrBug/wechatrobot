package cn.mandroid.wechatrobot.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import cn.mandroid.wechatrobot.model.entity.dao.LoginWechatUser;
import cn.mandroid.wechatrobot.model.entity.dao.User;
import cn.mandroid.wechatrobot.model.entity.dao.WechatAuthenticationBean;
import cn.mandroid.wechatrobot.model.entity.dao.WechatMessage;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;

import cn.mandroid.wechatrobot.gen.LoginWechatUserDao;
import cn.mandroid.wechatrobot.gen.UserDao;
import cn.mandroid.wechatrobot.gen.WechatAuthenticationBeanDao;
import cn.mandroid.wechatrobot.gen.WechatMessageDao;
import cn.mandroid.wechatrobot.gen.WechatUserBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig loginWechatUserDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig wechatAuthenticationBeanDaoConfig;
    private final DaoConfig wechatMessageDaoConfig;
    private final DaoConfig wechatUserBeanDaoConfig;

    private final LoginWechatUserDao loginWechatUserDao;
    private final UserDao userDao;
    private final WechatAuthenticationBeanDao wechatAuthenticationBeanDao;
    private final WechatMessageDao wechatMessageDao;
    private final WechatUserBeanDao wechatUserBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        loginWechatUserDaoConfig = daoConfigMap.get(LoginWechatUserDao.class).clone();
        loginWechatUserDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        wechatAuthenticationBeanDaoConfig = daoConfigMap.get(WechatAuthenticationBeanDao.class).clone();
        wechatAuthenticationBeanDaoConfig.initIdentityScope(type);

        wechatMessageDaoConfig = daoConfigMap.get(WechatMessageDao.class).clone();
        wechatMessageDaoConfig.initIdentityScope(type);

        wechatUserBeanDaoConfig = daoConfigMap.get(WechatUserBeanDao.class).clone();
        wechatUserBeanDaoConfig.initIdentityScope(type);

        loginWechatUserDao = new LoginWechatUserDao(loginWechatUserDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        wechatAuthenticationBeanDao = new WechatAuthenticationBeanDao(wechatAuthenticationBeanDaoConfig, this);
        wechatMessageDao = new WechatMessageDao(wechatMessageDaoConfig, this);
        wechatUserBeanDao = new WechatUserBeanDao(wechatUserBeanDaoConfig, this);

        registerDao(LoginWechatUser.class, loginWechatUserDao);
        registerDao(User.class, userDao);
        registerDao(WechatAuthenticationBean.class, wechatAuthenticationBeanDao);
        registerDao(WechatMessage.class, wechatMessageDao);
        registerDao(WechatUserBean.class, wechatUserBeanDao);
    }
    
    public void clear() {
        loginWechatUserDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
        wechatAuthenticationBeanDaoConfig.clearIdentityScope();
        wechatMessageDaoConfig.clearIdentityScope();
        wechatUserBeanDaoConfig.clearIdentityScope();
    }

    public LoginWechatUserDao getLoginWechatUserDao() {
        return loginWechatUserDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public WechatAuthenticationBeanDao getWechatAuthenticationBeanDao() {
        return wechatAuthenticationBeanDao;
    }

    public WechatMessageDao getWechatMessageDao() {
        return wechatMessageDao;
    }

    public WechatUserBeanDao getWechatUserBeanDao() {
        return wechatUserBeanDao;
    }

}
