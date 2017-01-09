package cn.mandroid.wechatrobot.ui.widget.chatview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.mandroid.wechatrobot.model.common.Injection;
import cn.mandroid.wechatrobot.model.entity.dao.WechatMessage;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;
import cn.mandroid.wechatrobot.model.entity.wechat.wechatmessage.WechatMessageBean;

/**
 * Created by wrBug on 2017/1/8.
 */

public class ChatView extends RecyclerView {
    ChatViewAdapter mAdapter;
    private static Map<String, WechatUserBean> sWechatUserMap = new HashMap<>();

    public ChatView(Context context) {
        this(context, null);
    }

    public ChatView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChatView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setLayoutManager(new LinearLayoutManager(context));
    }

    public void setHistoryMessage(List<WechatMessage> wechatMessages) {
        mAdapter = new ChatViewAdapter(getContext(), wechatMessages);
        setAdapter(mAdapter);
    }

    public void addMessage(WechatMessage... wechatMessages) {
        mAdapter.addMessage(wechatMessages);
    }

    public void addMessage(List<WechatMessage> wechatMessages) {
        mAdapter.addMessage(wechatMessages);
    }

    private static class ChatViewAdapter extends Adapter<ChatViewAdapter.ChatViewHolder> {
        private static final int TYPE_LEFT = 1;
        private static final int TYPE_RIGHT = 2;
        private List<WechatMessage> mWechatMessages;
        private Context mContext;

        public ChatViewAdapter(Context context, List<WechatMessage> wechatMessages) {
            mContext = context;
            mWechatMessages = wechatMessages;
            if (mWechatMessages == null) {
                mWechatMessages = new ArrayList<>();
            }
        }

        public void addMessage(WechatMessage... wechatMessages) {
            if (wechatMessages != null) {
                for (WechatMessage wechatMessage : wechatMessages) {
                    if (wechatMessage != null) {
                        mWechatMessages.add(wechatMessage);
                    }
                }
                notifyDataSetChanged();
            }
        }

        private void addMessage(List<WechatMessage> wechatMessages) {
            if (wechatMessages != null) {
                for (WechatMessage wechatMessage : wechatMessages) {
                    if (wechatMessage != null) {
                        mWechatMessages.add(wechatMessage);
                    }
                }
                notifyDataSetChanged();
            }
        }


        @Override
        public int getItemViewType(int position) {
            WechatMessage message = mWechatMessages.get(position);
            if (message.getIsFromMine()) {
                return TYPE_RIGHT;
            } else {
                return TYPE_LEFT;
            }
        }

        @Override
        public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case TYPE_LEFT: {
                    LeftChatBubble bubble = new LeftChatBubble(mContext);
                    AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    bubble.setLayoutParams(params);
                    return new ChatViewHolder(bubble);
                }
                case TYPE_RIGHT: {
                    RightChatBubble bubble = new RightChatBubble(mContext);
                    AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    bubble.setLayoutParams(params);
                    return new ChatViewHolder(bubble);
                }
            }
            return null;
        }

        @Override
        public void onBindViewHolder(ChatViewHolder holder, int position) {
            WechatMessage message = mWechatMessages.get(position);
            if (sWechatUserMap.containsKey(message.getFromUserName())) {
                WechatUserBean wechatUserBean = sWechatUserMap.get(message.getFromUserName());
                holder.setData(wechatUserBean.getHeadImgUrl(), wechatUserBean.getNickName(), message.getContent());
            } else {
                WechatUserBean wechatUserBean = Injection.getWechatInfoRepository().getLocalWechatContactor(message.getFromUserName());
                if (wechatUserBean != null) {
                    sWechatUserMap.put(message.getFromUserName(), wechatUserBean);
                    holder.setData(wechatUserBean.getHeadImgUrl(), wechatUserBean.getNickName(), message.getContent());
                }
            }
        }

        public static class ChatViewHolder extends ViewHolder {
            private IChatBubble mChatBubble;

            public ChatViewHolder(IChatBubble itemView) {
                super((View) itemView);
                mChatBubble = itemView;
            }

            public void setData(String url, String nickname, String msg) {
                mChatBubble.setAvatar(url);
                mChatBubble.setNickname(nickname);
                mChatBubble.setMessage(msg);
            }
        }

        @Override
        public int getItemCount() {
            return mWechatMessages.size();
        }
    }
}
