package cn.mandroid.wechatrobot.model.repository.turingrobot;

/**
 * Created by wrBug on 2017/1/9.
 */

public class TuRingRepostory3 {
    private volatile static TuRingRepostory3 INSTANCE;
    private ITuRingCloudSource1 mTuLingCloudSource;

    private TuRingRepostory3() {
        mTuLingCloudSource = new TuRingCloudSource2();
    }

    public static TuRingRepostory3 getInstance() {
        if (INSTANCE == null) {
            synchronized (TuRingRepostory3.class) {
                INSTANCE = new TuRingRepostory3();
            }
        }
        return INSTANCE;
    }

    public void getTuringResp(String username, String msg, ITuRingCloudSource1.GetTuringRespCallback callback) {
        mTuLingCloudSource.getTuringResp(username, msg, callback);
    }
}
