package cn.mandroid.wechatrobot.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import cn.mandroid.wechatrobot.model.entity.dao.WechatAuthenticationBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "WECHAT_AUTHENTICATION_BEAN".
*/
public class WechatAuthenticationBeanDao extends AbstractDao<WechatAuthenticationBean, Long> {

    public static final String TABLENAME = "WECHAT_AUTHENTICATION_BEAN";

    /**
     * Properties of entity WechatAuthenticationBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Uid = new Property(0, String.class, "uid", false, "UID");
        public final static Property RedirectUrl = new Property(1, String.class, "redirectUrl", false, "REDIRECT_URL");
        public final static Property BaseUrl = new Property(2, String.class, "baseUrl", false, "BASE_URL");
        public final static Property Skey = new Property(3, String.class, "skey", false, "SKEY");
        public final static Property Sid = new Property(4, String.class, "sid", false, "SID");
        public final static Property Uin = new Property(5, long.class, "uin", true, "_id");
        public final static Property PassTicket = new Property(6, String.class, "passTicket", false, "PASS_TICKET");
        public final static Property Cookie = new Property(7, String.class, "cookie", false, "COOKIE");
        public final static Property BaseRequestStr = new Property(8, String.class, "baseRequestStr", false, "BASE_REQUEST_STR");
        public final static Property SyncKey = new Property(9, String.class, "syncKey", false, "SYNC_KEY");
        public final static Property SyncKeyJson = new Property(10, String.class, "syncKeyJson", false, "SYNC_KEY_JSON");
    }


    public WechatAuthenticationBeanDao(DaoConfig config) {
        super(config);
    }
    
    public WechatAuthenticationBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"WECHAT_AUTHENTICATION_BEAN\" (" + //
                "\"UID\" TEXT," + // 0: uid
                "\"REDIRECT_URL\" TEXT," + // 1: redirectUrl
                "\"BASE_URL\" TEXT," + // 2: baseUrl
                "\"SKEY\" TEXT," + // 3: skey
                "\"SID\" TEXT," + // 4: sid
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 5: uin
                "\"PASS_TICKET\" TEXT," + // 6: passTicket
                "\"COOKIE\" TEXT," + // 7: cookie
                "\"BASE_REQUEST_STR\" TEXT," + // 8: baseRequestStr
                "\"SYNC_KEY\" TEXT," + // 9: syncKey
                "\"SYNC_KEY_JSON\" TEXT);"); // 10: syncKeyJson
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WECHAT_AUTHENTICATION_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, WechatAuthenticationBean entity) {
        stmt.clearBindings();
 
        String uid = entity.getUid();
        if (uid != null) {
            stmt.bindString(1, uid);
        }
 
        String redirectUrl = entity.getRedirectUrl();
        if (redirectUrl != null) {
            stmt.bindString(2, redirectUrl);
        }
 
        String baseUrl = entity.getBaseUrl();
        if (baseUrl != null) {
            stmt.bindString(3, baseUrl);
        }
 
        String skey = entity.getSkey();
        if (skey != null) {
            stmt.bindString(4, skey);
        }
 
        String sid = entity.getSid();
        if (sid != null) {
            stmt.bindString(5, sid);
        }
        stmt.bindLong(6, entity.getUin());
 
        String passTicket = entity.getPassTicket();
        if (passTicket != null) {
            stmt.bindString(7, passTicket);
        }
 
        String cookie = entity.getCookie();
        if (cookie != null) {
            stmt.bindString(8, cookie);
        }
 
        String baseRequestStr = entity.getBaseRequestStr();
        if (baseRequestStr != null) {
            stmt.bindString(9, baseRequestStr);
        }
 
        String syncKey = entity.getSyncKey();
        if (syncKey != null) {
            stmt.bindString(10, syncKey);
        }
 
        String syncKeyJson = entity.getSyncKeyJson();
        if (syncKeyJson != null) {
            stmt.bindString(11, syncKeyJson);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, WechatAuthenticationBean entity) {
        stmt.clearBindings();
 
        String uid = entity.getUid();
        if (uid != null) {
            stmt.bindString(1, uid);
        }
 
        String redirectUrl = entity.getRedirectUrl();
        if (redirectUrl != null) {
            stmt.bindString(2, redirectUrl);
        }
 
        String baseUrl = entity.getBaseUrl();
        if (baseUrl != null) {
            stmt.bindString(3, baseUrl);
        }
 
        String skey = entity.getSkey();
        if (skey != null) {
            stmt.bindString(4, skey);
        }
 
        String sid = entity.getSid();
        if (sid != null) {
            stmt.bindString(5, sid);
        }
        stmt.bindLong(6, entity.getUin());
 
        String passTicket = entity.getPassTicket();
        if (passTicket != null) {
            stmt.bindString(7, passTicket);
        }
 
        String cookie = entity.getCookie();
        if (cookie != null) {
            stmt.bindString(8, cookie);
        }
 
        String baseRequestStr = entity.getBaseRequestStr();
        if (baseRequestStr != null) {
            stmt.bindString(9, baseRequestStr);
        }
 
        String syncKey = entity.getSyncKey();
        if (syncKey != null) {
            stmt.bindString(10, syncKey);
        }
 
        String syncKeyJson = entity.getSyncKeyJson();
        if (syncKeyJson != null) {
            stmt.bindString(11, syncKeyJson);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 5);
    }    

    @Override
    public WechatAuthenticationBean readEntity(Cursor cursor, int offset) {
        WechatAuthenticationBean entity = new WechatAuthenticationBean( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // uid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // redirectUrl
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // baseUrl
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // skey
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // sid
            cursor.getLong(offset + 5), // uin
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // passTicket
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // cookie
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // baseRequestStr
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // syncKey
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10) // syncKeyJson
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, WechatAuthenticationBean entity, int offset) {
        entity.setUid(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setRedirectUrl(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setBaseUrl(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSkey(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSid(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUin(cursor.getLong(offset + 5));
        entity.setPassTicket(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCookie(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setBaseRequestStr(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setSyncKey(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setSyncKeyJson(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(WechatAuthenticationBean entity, long rowId) {
        entity.setUin(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(WechatAuthenticationBean entity) {
        if(entity != null) {
            return entity.getUin();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(WechatAuthenticationBean entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
