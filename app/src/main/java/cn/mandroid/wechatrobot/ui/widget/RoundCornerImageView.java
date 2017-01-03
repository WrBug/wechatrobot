package cn.mandroid.wechatrobot.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.ImageView;

import cn.mandroid.wechatrobot.R;

/**
 * RoundCornerImageView
 *
 * @author wrbug
 * @since 2016/12/12
 */
public class RoundCornerImageView extends ImageView {
    private TypedArray mTypedArray;
    private int defaultSrcId;
    private float roundCornerRadius;

    public RoundCornerImageView(Context context) {
        this(context, null);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mTypedArray = getContext().obtainStyledAttributes(attrs, R.styleable.RoundCornerImageView);
        roundCornerRadius = mTypedArray.getDimension(R.styleable.RoundCornerImageView_roundCornerRadius, 0);
        defaultSrcId = mTypedArray.getResourceId(R.styleable.RoundCornerImageView_defaultSrc, 0);
        mTypedArray.recycle();
        setImageResource(defaultSrcId);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        if (bm == null || bm.isRecycled()) {
            setImageResource(defaultSrcId);
            return;
        }
        super.setImageBitmap(bm);
    }

    @Override
    public void setImageResource(int resId) {
        if (resId <= 0) {
            resId = defaultSrcId;
        }
        super.setImageResource(resId);
    }

    public void setSetDefaultImage(@DrawableRes int res) {
        defaultSrcId = res;
        setImageResource(defaultSrcId);
    }

    public void setRoundCornerRadius(float roundCornerRadius) {
        this.roundCornerRadius = roundCornerRadius;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path clipPath = new Path();
        int w = this.getWidth();
        int h = this.getHeight();
        clipPath.addRoundRect(new RectF(0, 0, w, h), roundCornerRadius, roundCornerRadius, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }
}