package cn.mandroid.wechatrobot.ui.widget.chatview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ListView;

import butterknife.ButterKnife;

/**
 * Created by wrBug on 2017/1/8.
 */

public class ChatView extends RecyclerView {
    public ChatView(Context context) {
        this(context, null);
    }

    public ChatView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChatView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public class ChatViewAdapter extends Adapter{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

       
        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
