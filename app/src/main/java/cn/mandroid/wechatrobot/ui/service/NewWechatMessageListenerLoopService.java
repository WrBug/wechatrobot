package cn.mandroid.wechatrobot.ui.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.mandroid.wechatrobot.model.common.Injection;
import cn.mandroid.wechatrobot.model.entity.dao.WechatAuthenticationBean;
import cn.mandroid.wechatrobot.model.entity.wechat.WechatSyncKeyBean;
import cn.mandroid.wechatrobot.model.entity.wechat.wechatmessage.WechatMessageBean;
import cn.mandroid.wechatrobot.model.repository.wechatauth.WechatAuthenticationRepository;
import cn.mandroid.wechatrobot.model.repository.wechatinfo.IWechatInfoCloudSource;
import cn.mandroid.wechatrobot.model.repository.wechatinfo.WechatInfoRepository;
import cn.mandroid.wechatrobot.ui.broadcastreceiver.NewMessageReceiver;
import cn.mandroid.wechatrobot.utils.MLog;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class NewWechatMessageListenerLoopService extends IntentService {
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_MESSAGE_LOOP = "ACTION_MESSAGE_LOOP";

    private static final String WECHAT_AUTHENTICATION_BEAN = "WECHAT_AUTHENTICATION_BEAN";
    private WechatInfoRepository mWechatInfoRepository;
    private WechatAuthenticationRepository mWechatAuthenticationRepository;
    private WechatAuthenticationBean mWechatAuthenticationBean;
    private ExecutorService mExecutorService;
    private int emptyMessageCount = 0;

    public NewWechatMessageListenerLoopService() {
        super("NewWechatMessageListenerLoopService");
        mWechatInfoRepository = Injection.getWechatInfoRepository();
        mWechatAuthenticationRepository = Injection.getWechatAuthenticationRepository();
        mExecutorService = Executors.newSingleThreadExecutor();
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void start(Context context, WechatAuthenticationBean bean) {
        Intent intent = new Intent(context, NewWechatMessageListenerLoopService.class);
        intent.setAction(ACTION_MESSAGE_LOOP);
        intent.putExtra(WECHAT_AUTHENTICATION_BEAN, bean);
        context.startService(intent);
    }

    public static void stop(Context context) {
        Intent intent = new Intent(context, NewWechatMessageListenerLoopService.class);
        context.stopService(intent);
    }

    @Override
    public void onDestroy() {
        mExecutorService.shutdown();
        MLog.i("服务已停止");
        sendStoped();
        super.onDestroy();
    }

    private void sendStoped() {
        Intent intent = new Intent(NewMessageReceiver.ACTION_SERVICE_STOPED);
        sendBroadcast(intent);
    }

    private void sendAuthFailed() {
        Intent intent = new Intent(NewMessageReceiver.ACTION_AUTH_FALID);
        sendBroadcast(intent);
        stopSelf();
    }

    private void sendStarted() {
        Intent intent = new Intent(NewMessageReceiver.ACTION_SERVICE_STARTED);
        sendBroadcast(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        MLog.i("服务已启动");
        sendStarted();
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_MESSAGE_LOOP.equals(action)) {
                mWechatAuthenticationBean = (WechatAuthenticationBean) intent.getSerializableExtra(WECHAT_AUTHENTICATION_BEAN);
                handleActionMessageLoop();
                while (true) ;
            }
        }
    }


    private void handleActionMessageLoop() {
        if (mExecutorService.isShutdown()) {
            return;
        }
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                wechatMessageLoop(mWechatAuthenticationBean.getBaseUrl(), mWechatAuthenticationBean.getSid(), mWechatAuthenticationBean.getSkey(), mWechatAuthenticationBean.getUin(), mWechatAuthenticationBean.getSyncKey(), mWechatAuthenticationBean.getPassTicket(), mWechatAuthenticationBean.getBaseRequest(), mWechatAuthenticationBean.getWechatSyncKeyBean());
            }
        });
    }

    private void wechatMessageLoop(String baseUrl, String sid, String skey, long uin, String syncKey, String passTicket, Map<String, String> baseRequest, WechatSyncKeyBean syncKeyBean) {
        mWechatInfoRepository.wechatMessageLoop(baseUrl, sid, skey, uin, syncKey, passTicket, baseRequest, syncKeyBean, new IWechatInfoCloudSource.WechatMessageLoopCallback() {
            @Override
            public void onSuccess(WechatMessageBean vo) {
                if (vo != null) {
                    MLog.i("新消息\n" + vo.toJson());
                    emptyMessageCount = 0;
                    int msgCount = vo.getAddMsgCount();
                    mWechatAuthenticationBean.setSyncKey(msgCount == 0 ? vo.getSyncCheckKey() : vo.getSyncKey());
                    mWechatAuthenticationRepository.saveWechatAuthInfo(mWechatAuthenticationBean);
                    if (msgCount > 0) {
                        Intent intent = new Intent(NewMessageReceiver.ACTION_NEW_MESSAGE);
                        intent.putExtra(NewMessageReceiver.MESSAGE_VO, vo);
                        sendBroadcast(intent);
                    }
                }
                handleActionMessageLoop();
            }

            @Override
            public void onEmpty() {
                emptyMessageCount++;
                if (emptyMessageCount >= 10) {
                    sendAuthFailed();
                } else {
                    handleActionMessageLoop();
                }
            }

            @Override
            public void onNoNewMessage() {
                emptyMessageCount = 0;
                handleActionMessageLoop();
            }

            @Override
            public void onAuthFailed() {
                sendAuthFailed();
            }
        });
    }

}
