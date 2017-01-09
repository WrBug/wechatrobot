package cn.mandroid.wechatrobot.model.repository.turingrobot;

import cn.mandroid.wechatrobot.model.entity.turing.TuringRespBean;

/**
 * Created by wrBug on 2017/1/9.
 */

public interface ITuringCloudSource {
    interface GetTuringRespCallback {
        void onSuccess(TuringRespBean tuRingRespBean);
    }

    void getTuringResp(String username, String msg, GetTuringRespCallback callback);
}
