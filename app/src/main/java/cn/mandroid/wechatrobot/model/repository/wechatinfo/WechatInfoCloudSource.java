package cn.mandroid.wechatrobot.model.repository.wechatinfo;

import android.text.TextUtils;

import cn.mandroid.wechatrobot.model.common.BaseCloudSource;
import cn.mandroid.wechatrobot.model.entity.wechat.WechatContactVo;
import cn.mandroid.wechatrobot.utils.MLog;
import rx.Subscriber;

/**
 * Created by wrBug on 2017/1/8.
 */

public class WechatInfoCloudSource extends BaseCloudSource implements IWechatInfoCloudSource {

    @Override
    public void getWechatContactors(String baseUrl, String passTicket, String skey, final GetContactorsCallback callback) {
        String url = baseUrl + "/webwxgetcontact";
        getQequestBuilder().addQuery("pass_ticket", passTicket)
                .addQuery("skey", skey)
                .addQuery("r", System.currentTimeMillis() + "")
                .doOtherApiGet(url, new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        MLog.e(e);
                        callback.onError();
                    }

                    @Override
                    public void onNext(String s) {
                        if (!TextUtils.isEmpty(s)) {
                            WechatContactVo contactVo = mGson.fromJson(s, WechatContactVo.class);
                            if (contactVo.getBaseResponse().isSuccess()) {
                                callback.onSuccess(contactVo);
                            } else {
                                callback.onLogin();
                            }
                        } else {
                            callback.onLogin();
                        }
                    }
                });
    }
}
