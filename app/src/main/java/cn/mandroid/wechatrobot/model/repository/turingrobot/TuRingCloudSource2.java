package cn.mandroid.wechatrobot.model.repository.turingrobot;

import android.text.TextUtils;

import cn.mandroid.wechatrobot.model.common.BaseCloudSource;
import cn.mandroid.wechatrobot.model.entity.turing.TuringRespVo;
import cn.mandroid.wechatrobot.utils.MLog;
import rx.Subscriber;

/**
 * Created by wrBug on 2017/1/9.
 */

public class TuRingCloudSource2 extends BaseCloudSource implements ITuRingCloudSource1 {
    @Override
    public void getTuringResp(String username, String msg, final GetTuringRespCallback callback) {
        String url = "http://apis.baidu.com/turing/turing/turing";
        getQequestBuilder().addQuery("key", "879a6cb3afb84dbf4fc84a1df2ab7319")
                .addQuery("info", msg)
                .addQuery("userid", username)
                .doOtherApiGet(url, new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        MLog.e(e);
                    }

                    @Override
                    public void onNext(String s) {
                        if (!TextUtils.isEmpty(s)) {
                            TuringRespVo bean = mGson.fromJson(s, TuringRespVo.class);
                            if (bean!=null) {
                                callback.onSuccess(bean);
                            }
                        }
                    }
                });
    }
}
