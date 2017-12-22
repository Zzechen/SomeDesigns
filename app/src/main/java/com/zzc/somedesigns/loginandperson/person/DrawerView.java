package com.zzc.somedesigns.loginandperson.person;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

/**
 * @auth zzc
 * @date 2017/12/19
 * @desc ${desc}
 */

public class DrawerView extends FrameLayout {
    private static final String TAG = DrawerView.class.toString();
    private final int DURATION = 2000;

    private View mContentView;
    private View mDrawerView;


    private ScrollerCompat mScroller;

    private int mTouchSlop;

    private float mInitX;
    private float mInitY;

    public DrawerView(@NonNull Context context) {
        this(context, null);
    }

    public DrawerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
        mScroller = ScrollerCompat.create(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 2)
            throw new IllegalArgumentException("can't hold more than 2 children");
        mContentView = getChildAt(1);
        mDrawerView = getChildAt(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mInitX = ev.getX();
                mInitY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float x = ev.getX();
                final float y = ev.getY();
                final float offsetX = x - mInitX;
                final float offsetY = y - mInitY;
                if (Math.abs(offsetX / offsetY) > 1 &&
                        (Math.abs(offsetX) > mTouchSlop)) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        boolean wantTouchEvents = true;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mScroller.abortAnimation();
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                final float offset = x - mInitX;
                ViewCompat.offsetLeftAndRight(mContentView, (int) offset);
                mInitX = x;
                mInitY = event.getY();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                int right = mContentView.getRight();
                int left = mContentView.getLeft();
                int activeX = mDrawerView.getLeft() - getWidth() + getPaddingLeft() + getPaddingRight();
                int idelX = 0;
                int dx = right > (mDrawerView.getLeft() + mDrawerView.getWidth() / 2) ? idelX - left : activeX - left;
                mScroller.startScroll(left, 0, dx, 0, DURATION);
                invalidate();
                break;
        }
        return wantTouchEvents;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            int offset = mScroller.getCurrX() - mContentView.getLeft();
            ViewCompat.offsetLeftAndRight(mContentView, offset);
            invalidate();
        }
    }

    public void close() {

    }

    public void open() {

    }
}
