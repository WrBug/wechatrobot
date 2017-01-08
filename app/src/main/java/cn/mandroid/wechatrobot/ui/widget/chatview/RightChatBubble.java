package cn.mandroid.wechatrobot.ui.widget.chatview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.mandroid.wechatrobot.R;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;
import cn.mandroid.wechatrobot.utils.DensityUtil;

/**
 * Created by wrBug on 2017/1/8.
 */

public class RightChatBubble extends LinearLayout implements IChatBubble {
    private Context mContext;
    private TextView mMessageTv;
    private ChatAvatarView mChatAvatarView;

    public RightChatBubble(Context context) {
        super(context);
        init(context);
    }

    public RightChatBubble(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RightChatBubble(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        setOrientation(LinearLayout.HORIZONTAL);
        initView();
    }

    private void initView() {
        mChatAvatarView = new ChatAvatarView(mContext);
        addView(mChatAvatarView);
        mMessageTv = new TextView(mContext);
        int padding = DensityUtil.dip2px(mContext, 7);
        mMessageTv.setPadding(padding * 2, padding, padding, padding);
        mMessageTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        mMessageTv.setBackgroundResource(R.drawable.ic_chat_bubble_right);
        mMessageTv.setTextColor(getResources().getColor(R.color.textCommon));
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, padding, 0);
        addView(mMessageTv);
    }

    @Override
    public void setMessage(String msg) {
        mMessageTv.setText(msg);
    }

    @Override
    public void setUser(WechatUserBean user) {
        mChatAvatarView.setData(user);
    }
}
