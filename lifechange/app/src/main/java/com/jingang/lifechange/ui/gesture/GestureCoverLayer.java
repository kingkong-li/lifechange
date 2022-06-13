package com.jingang.lifechange.ui.gesture;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.jingang.lifechange.R;

/**
 * @author lijingang
 * @description:  覆盖层
 * @date
 */
public class GestureCoverLayer extends FrameLayout {
    private static final  String TAG="GestureCoverLayer";
    private boolean mGestureEnable=true;
    private View mRoot;
    private ProgressBar mProgressBar;
    private GestureDetector mGestureDetector;
    private boolean horizontalSlide;
    private boolean rightVerticalSlide;
    private boolean firstTouch=false;
    private int mWidth;
    private int mHeight;
    public GestureCoverLayer(@NonNull Context context) {
        super(context);
        initView(context);
        initGestureDetector(context);
    }



    public GestureCoverLayer(Context context, AttributeSet attrs){
        super(context,attrs);
        initView(context);
        initGestureDetector(context);
    }

    private void initView(Context context) {
        mRoot=View.inflate(context, R.layout.gesture_indicator , this);
        mProgressBar=mRoot.findViewById(R.id.voice_progress_bar);
    }

    private void initGestureDetector(Context context) {
        mGestureDetector=new GestureDetector(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                Log.v(TAG,"GestureDetector"+"onDown MotionEvent ="+e);
                firstTouch=true;
                return mGestureEnable;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//                Log.v(TAG,"GestureDetector  "+"e1="+e1.toString()+"e2="+e2+", distanceX="+distanceX+", distanceY="+distanceY );
                if (!mGestureEnable) {
                    return false;
                }
                float mOldX = e1.getX();
                float mOldY = e1.getY();
                float deltaY = mOldY - e2.getY();
                float deltaX = mOldX - e2.getX();
                if (firstTouch) {
                    horizontalSlide = Math.abs(distanceX) >= Math.abs(distanceY);
                    rightVerticalSlide = mOldX > mWidth * 0.5f;
                    firstTouch = false;
                }

                if (horizontalSlide) {

                    int currentProgress=mProgressBar.getProgress();
                    int newProgress=currentProgress+((int)deltaX/mWidth)*100;
                    Log.v(TAG,"deltaX="+deltaX+"mWidth="+mWidth+", new Pro="+newProgress);
                    mProgressBar.setProgress(newProgress);
                } else {
                    if (Math.abs(deltaY) > mHeight) {
                        return false;
                    }
                    if (rightVerticalSlide) {
                       // 右侧竖直滑动
                    } else {
                        // 左侧竖直滑动
                    }
                }
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }

        });
        // 将所有的触摸事件分发给mGestureDetector处理
        mRoot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mGestureDetector.onTouchEvent(event);
            }
        });
    }
    public void showGestureCover(){
        mRoot.setVisibility(View.VISIBLE);
    }
    public void hideGestureCover(){
        mRoot.setVisibility(View.GONE);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mWidth = mRoot.getWidth();
                mHeight = mRoot.getHeight();
                mRoot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }
}
