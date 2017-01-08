package cn.mandroid.wechatrobot.ui.widget.chatview;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.mandroid.wechatrobot.R;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;
import cn.mandroid.wechatrobot.ui.widget.RoundCornerImageView;
import cn.mandroid.wechatrobot.utils.DensityUtil;
import cn.mandroid.wechatrobot.utils.ImageLoader;

/**
 * Created by wrBug on 2017/1/8.
 */

public class LeftChatBubble extends LinearLayout implements IChatBubble {
    @BindView(R.id.avatarIv)
    RoundCornerImageView mAvatarIv;
    @BindView(R.id.nicknameTv)
    TextView mNicknameTv;
    @BindView(R.id.msgTv)
    TextView mMsgTv;
    @BindView(R.id.msgContainer)
    FrameLayout mMsgContainer;
    private Context mContext;

    public LeftChatBubble(Context context) {
        super(context);
        init(context);
    }

    public LeftChatBubble(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LeftChatBubble(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.layout_left_chat_bubble, this);
        ButterKnife.bind(this);
    }

    @Override
    public void setMessage(String msg) {
        mMsgTv.setText(Html.fromHtml(msg));
    }

    @Override
    public void setUser(WechatUserBean user) {
    }

    @Override
    public void setNickname(String nickname) {
        mNicknameTv.setText(nickname);
    }

    @Override
    public void setAvatar(String url) {
        ImageLoader.load(url).into(mAvatarIv);
    }
}
