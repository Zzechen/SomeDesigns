package com.zzc.somedesigns.elema.goodsb;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zzc.somedesigns.R;

/**
 * @auth zzc
 * @date 2017/12/26
 * @desc ${desc}
 */

public class ElemaGoodsLayout extends LinearLayout implements NestedScrollingParent {
    private static final String TAG = ElemaGoodsLayout.class.toString();

    private static final float DP_TO_SCALE = 0.001f;

    private NestedScrollingParentHelper mParentHelper;
    private ScrollerCompat mScroller;
    private VelocityTracker mVelocityTracker;


    private int mMinVelocity;
    private int mMaxVelocity;
    private View vSummary;
    private View vBar;
    private View vShare;

    private Rect mSummaryLoc;

    private float px2Scale;
    private View vList;
    private ImageView ivImg;
    private View vTitle;

    public ElemaGoodsLayout(Context context) {
        this(context, null);
    }

    public ElemaGoodsLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ElemaGoodsLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mParentHelper = new NestedScrollingParentHelper(this);
        mScroller = ScrollerCompat.create(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mMinVelocity = configuration.getScaledMinimumFlingVelocity();
        mMaxVelocity = configuration.getScaledMaximumFlingVelocity();
        px2Scale = DP_TO_SCALE * context.getResources().getDisplayMetrics().density;
        mSummaryLoc = new Rect();
        setOrientation(VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        vSummary = findViewById(R.id.ll_summary);
        vSummary.setScaleX(0.8f);
        vSummary.setScaleY(0.8f);
        vBar = findViewById(R.id.rl_bar);
        vShare = findViewById(R.id.iv_elema_share);
        vList = findViewById(R.id.rv_elema);
        ivImg = (ImageView) findViewById(R.id.iv_elema_img);
        vTitle = findViewById(R.id.tv_bar_title);
    }
    //---------------------------------------------about NestedScrollingParent------------------------------------------

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        mParentHelper.onNestedScrollAccepted(child, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(View target) {
        mParentHelper.onStopNestedScroll(target);

    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed,
                               int dyUnconsumed) {
        float scale = vSummary.getScaleX();
        vSummary.getLocalVisibleRect(mSummaryLoc);
        if (scale >= 0.8f && scale <= 1.0f) {
            float newScale = 1;
            if (dyConsumed < 0 && mSummaryLoc.top > 0) {
                newScale = scale;
            } else if (mSummaryLoc.bottom > 0) {
                newScale = dyConsumed > 0 ? Math.min(scale + (px2Scale * dyConsumed), 1) :
                        Math.max(scale + (px2Scale * dyConsumed), 0.8f);
            }
            vSummary.setScaleX(newScale);
            vSummary.setScaleY(newScale);
            if (newScale == 1.0f) {
                ivImg.scrollBy(0, -dyConsumed / 2);
                vSummary.setBackgroundColor(Color.WHITE);
                vList.setAlpha(newScale);
                vBar.setAlpha(newScale);
            } else if (newScale >= 0.8f) {
                vSummary.setBackgroundResource(R.drawable.bg_elema_summary_scaled);
                vList.setAlpha(5 * newScale - 4);
                vBar.setAlpha(5 * newScale - 4);
                vShare.setAlpha(5 - 5 * newScale);
            }
        }
        vBar.setTranslationY(vBar.getTranslationY() + dyConsumed);
        if (mSummaryLoc.bottom < vBar.getMeasuredHeight()) {
            vBar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            //todo title transitionY
        } else {
            vBar.setBackgroundDrawable(null);
        }
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {

    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.e(TAG, "onNestedFling: ");
        return true;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return true;
    }

    @Override
    public int getNestedScrollAxes() {
        return mParentHelper.getNestedScrollAxes();
    }

//---------------------------------------------set VelocityTracker------------------------------------------

    private void initOrResetVelocity() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        } else {
            mVelocityTracker.clear();
        }
    }

    private void initVelocityTrackerIfNotExists() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private void recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

//---------------------------------------------about scroll------------------------------------------

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            //do something
        }
    }
}
