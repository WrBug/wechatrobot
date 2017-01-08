package cn.mandroid.wechatrobot.ui.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.ArrayList;
import java.util.List;

import cn.mandroid.wechatrobot.model.entity.wechat.wechatmessage.WechatMessageBean;

public class NewMessageReceiver extends BroadcastReceiver {
    private static volatile NewMessageReceiver INSTANCE;
    public final static String ACTION_NEW_MESSAGE = "ACTION_NEW_MESSAGE";
    public final static String ACTION_AUTH_FALID = "ACTION_AUTH_FALID";
    public final static String MESSAGE_VO = "MESSAGE_VO";
    private List<OnNewMessageListener> listeners;

    private NewMessageReceiver() {
        listeners = new ArrayList<>();
    }

    public static NewMessageReceiver getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (NewMessageReceiver.class) {
                INSTANCE = new NewMessageReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(NewMessageReceiver.ACTION_NEW_MESSAGE);
                intentFilter.addAction(NewMessageReceiver.ACTION_AUTH_FALID);
                context.registerReceiver(INSTANCE, intentFilter);
            }
        }
        return INSTANCE;
    }

    public void addNewMessageListener(OnNewMessageListener listener) {
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeNewMessageListener(OnNewMessageListener listener) {
        if (listener != null && listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case ACTION_AUTH_FALID: {
                sendAuthFalid();
                break;
            }
            case ACTION_NEW_MESSAGE: {
                WechatMessageBean vo = (WechatMessageBean) intent.getSerializableExtra(MESSAGE_VO);
                sendNewMessage(vo);
            }
        }
    }

    private void sendNewMessage(WechatMessageBean vo) {
        for (OnNewMessageListener listener : listeners) {
            listener.onNewMessage(vo);
        }
    }

    private void sendAuthFalid() {
        for (OnNewMessageListener listener : listeners) {
            listener.onAuthFalid();
        }
    }

    public interface OnNewMessageListener {
        void onNewMessage(WechatMessageBean vo);

        void onAuthFalid();
    }
}
