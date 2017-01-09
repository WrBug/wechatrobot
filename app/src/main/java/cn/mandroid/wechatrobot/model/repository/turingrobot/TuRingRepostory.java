package cn.mandroid.wechatrobot.model.repository.turingrobot;

/**
 * Created by wrBug on 2017/1/9.
 */

public class TuringRepostory {
    private volatile static TuringRepostory INSTANCE;
    private ITuringCloudSource mTuLingCloudSource;

    private TuringRepostory() {
        mTuLingCloudSource = new TuringCloudSource();
    }

    public static TuringRepostory getInstance() {
        if (INSTANCE == null) {
            synchronized (TuringRepostory.class) {
                INSTANCE = new TuringRepostory();
            }
        }
        return INSTANCE;
    }

    public void getTuringResp(String username, String msg, ITuringCloudSource.GetTuringRespCallback callback) {
        mTuLingCloudSource.getTuringResp(username, msg, callback);
    }
}
