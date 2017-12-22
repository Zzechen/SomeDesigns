package com.zzc.somedesigns.zhihuad;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewParent;

import com.zzc.somedesigns.utils.SizeUtils;

/**
 * @auth zzc
 * @date 2017/12/20
 * @desc ${desc}
 */

public class AdImageView extends AppCompatImageView {
    private static final String TAG = AdImageView.class.toString();
    private RecyclerView.OnScrollListener mOnScrollListener;
    private Matrix mMatrix;

    public AdImageView(Context context) {
        this(context, null);
    }

    public AdImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdImageView(final Context context, AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setScaleType(ScaleType.MATRIX);
        mMatrix = new Matrix();
        final int screenWith = SizeUtils.screenWith(context);
        final int screenHeight = SizeUtils.screenHeight(context);
        mOnScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mMatrix.reset();
                Rect bounds = getDrawable().getBounds();
                RectF src = new RectF(getLeft(), getTop(), getRight(), getBottom());
                RectF dist = new RectF(0, 0, getWidth(), getHeight());
                mMatrix.setRectToRect(src, dist, Matrix.ScaleToFit.CENTER);
                //缩放
                mMatrix.preScale(screenWith * 1.f / bounds.width(), screenHeight * 1.0f / bounds.height());
                //视差
//                mMatrix.postScale(screenWith * 1.f / bounds.width(), screenHeight * 1.0f / bounds.height());
                setImageMatrix(mMatrix);
            }
        };
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent != null && parent instanceof RecyclerView) {
            ((RecyclerView) parent).addOnScrollListener(mOnScrollListener);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewParent parent = getParent();
        if (parent != null && parent instanceof RecyclerView) {
            ((RecyclerView) parent).removeOnScrollListener(mOnScrollListener);
        }
    }
}
