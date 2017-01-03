package cn.mandroid.wechatrobot;

import android.app.Application;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.File;
import java.io.IOException;

import cn.mandroid.wechatrobot.gen.DaoMaster;
import cn.mandroid.wechatrobot.gen.DaoSession;
import cn.mandroid.wechatrobot.utils.MLog;

/**
 * Created by wrBug on 2017/1/1.
 */

public class WechatRobotApp extends Application {
    private static WechatRobotApp INSTANCE;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static PatchManager patchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        setDatabase();
        initHotFix();
    }

    public static WechatRobotApp getApplication() {
        return INSTANCE;
    }

    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "wechatdatabase.db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return INSTANCE.mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    private void initHotFix() {
        patchManager = new PatchManager(this);
        String version_name = BuildConfig.VERSION_NAME;
        patchManager.init(version_name);
        patchManager.loadPatch();
        loadPatch();
    }

    public void loadPatch() {
        String path = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/XiaoLuoBei/hot.apatch";
        File file = new File(path);
        addHotFixPatch(file);
    }

    public void addHotFixPatch(File file) {
        if (file.exists()) {
            MLog.i("加载补丁");
            try {
                patchManager.addPatch(file.getPath());
            } catch (IOException e) {
            }
            file.delete();
        }
    }
}
