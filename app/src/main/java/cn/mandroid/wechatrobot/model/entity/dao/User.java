package cn.mandroid.wechatrobot.model.entity.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wrBug on 2017/1/1.
 */
@Entity
public class User {
    @Id
    private long id;

    @Generated(hash = 936488051)
    public User(long id) {
        this.id = id;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
