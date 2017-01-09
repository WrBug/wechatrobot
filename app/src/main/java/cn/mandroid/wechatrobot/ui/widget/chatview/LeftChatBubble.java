package cn.mandroid.wechatrobot.ui.widget.chatview;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.mandroid.wechatrobot.R;
import cn.mandroid.wechatrobot.model.entity.dao.WechatMessage;
import cn.mandroid.wechatrobot.model.entity.dao.WechatUserBean;
import cn.mandroid.wechatrobot.model.entity.wechat.wechatmessage.message.BiaoQingBao;
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
    @BindView(R.id.msgContainer)
    FrameLayout mMsgContainer;
    private Context mContext;
    FrameLayout.LayoutParams mTextContentParams;
    FrameLayout.LayoutParams mImageContentParams;
    TextView mTextMsgTv;
    RoundCornerImageView mImageMsgIv;

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
        initMsgView();
    }

    private void initMsgView() {
        mTextMsgTv = new TextView(mContext);
        mTextMsgTv.setTextColor(getResources().getColor(R.color.trans80black));
        mTextMsgTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        mTextContentParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTextContentParams.gravity = Gravity.CENTER;
        mTextMsgTv.setLayoutParams(mTextContentParams);
        mImageMsgIv = new RoundCornerImageView(mContext);
//        mImageMsgIv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        int dp = DensityUtil.dip2px(mContext, 100);
        mImageContentParams = new FrameLayout.LayoutParams(dp, dp);
        mImageMsgIv.setLayoutParams(mImageContentParams);
    }

    @Override
    public void setMessage(WechatMessage msg) {
        mMsgContainer.removeAllViews();
        switch ((int) msg.getMsgType()) {
            case WechatMessage.TEXT: {
                mTextMsgTv.setText(Html.fromHtml(msg.getContent()));
                mMsgContainer.addView(mTextMsgTv);
                break;
            }
            case WechatMessage.PHOTO: {
                String url = "https://wx2.qq.com/cgi-bin/mmwebwx-bin/webwxgetmsgimg?MsgID=" + msg.getMsgId();
                ImageLoader.load(url, R.color.trans10black).into(mImageMsgIv);
                mMsgContainer.addView(mImageMsgIv);
                break;
            }
            case WechatMessage.VOICE: {
                break;
            }
            case WechatMessage.BIAO_QING_BAO: {
                BiaoQingBao biaoQingBao = BiaoQingBao.getBiaoQingBao(msg);
                if (biaoQingBao != null) {
                    ImageLoader.load(biaoQingBao.getCdnurl(), R.color.trans10black).into(mImageMsgIv);
                    mMsgContainer.addView(mImageMsgIv);
                } else {
                    mTextMsgTv.setText("[发送了一个表情，请在手机上查看]");
                    mMsgContainer.addView(mTextMsgTv);
                }
                break;
            }
            default: {
                mTextMsgTv.setText("不支持的消息类型");
                mMsgContainer.addView(mTextMsgTv);
                break;
            }
        }
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
