package cn.mandroid.wechatrobot.model.entity.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import cn.mandroid.wechatrobot.gen.DaoSession;
import cn.mandroid.wechatrobot.gen.WechatUserBeanDao;
import cn.mandroid.wechatrobot.gen.LoginWechatUserDao;

/**
 * Created by wrBug on 2017/1/2.
 */
@Entity
public class LoginWechatUser {
    @ToOne
    private WechatUserBean mWechatUserBean;
    @Id
    @Unique
    private String uin;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1992762451)
    private transient LoginWechatUserDao myDao;

    @Generated(hash = 452249135)
    public LoginWechatUser(String uin) {
        this.uin = uin;
    }

    @Generated(hash = 988988007)
    public LoginWechatUser() {
    }

    public String getUin() {
        return this.uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    @Generated(hash = 229276460)
    private transient boolean mWechatUserBean__refreshed;

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 769558121)
    public WechatUserBean getMWechatUserBean() {
        if (mWechatUserBean != null || !mWechatUserBean__refreshed) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            WechatUserBeanDao targetDao = daoSession.getWechatUserBeanDao();
            targetDao.refresh(mWechatUserBean);
            mWechatUserBean__refreshed = true;
        }
        return mWechatUserBean;
    }

    /**
     * To-one relationship, returned entity is not refreshed and may carry only the PK property.
     */
    @Generated(hash = 361666219)
    public WechatUserBean peakMWechatUserBean() {
        return mWechatUserBean;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 426325561)
    public void setMWechatUserBean(WechatUserBean mWechatUserBean) {
        synchronized (this) {
            this.mWechatUserBean = mWechatUserBean;
            mWechatUserBean__refreshed = true;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 685367103)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLoginWechatUserDao() : null;
    }
}
