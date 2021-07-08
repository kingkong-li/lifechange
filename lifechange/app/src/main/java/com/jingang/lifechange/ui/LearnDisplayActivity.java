package com.jingang.lifechange.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.Choreographer;
import android.view.ViewTreeObserver;
import android.widget.Button;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;

import java.sql.Time;
import java.util.Date;

public class LearnDisplayActivity extends BaseActivity {
    private static final String TAG=LearnDisplayActivity.class.getSimpleName();
    private Button mButton;
    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView只是创建好需要的资源，比如phoneWindow，建立他们之间的通信关系
        setContentView(R.layout.activity_learn_display);
        mButton=findViewById(R.id.button);
        getWindow().getDecorView().getViewTreeObserver().addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
            @Override
            public void onDraw() {
                Log.v(TAG,"onDecorView draw button width="+mButton.getWidth()+", height="+mButton.getHeight());
            }
        });
        Log.v(TAG,"onCreate button width="+mButton.getWidth()+", height="+mButton.getHeight());


        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long frameTimeNanos) {

                if(startTime==0){
                    startTime=frameTimeNanos;
                    Log.v(TAG,"Choreographer begin="+new Date(startTime).toString());
                }

            }
        });
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onResume() {
        Log.v(TAG,"before Super.onResume button width="+mButton.getWidth()+", height="+mButton.getHeight());
        super.onResume();
        Log.v(TAG,"after Super.onResume button width="+mButton.getWidth()+", height="+mButton.getHeight());


        mButton.getViewTreeObserver().addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
            @Override
            public void onDraw() {
                // 这里得到Button的正常宽和高，这里证明了这里这个点 可以当做一个View显示出来的时间点，做性能优化
                Log.v(TAG,"addOnDrawListener button width="+mButton.getWidth()+", height="+mButton.getHeight());
            }
        });
        mButton.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                Log.v(TAG,"addOnPreDrawListener button width="+mButton.getWidth()+", height="+mButton.getHeight());
                return true;
            }
        });
        // 这里证明了 onResume之后才开始显示--经过查找 是ActivityThread去执行handleResumeActivity，这个方法
//        首先 performActivityResume 然后才执行view的生命周期
    }



    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG,"onPause button width="+mButton.getWidth()+", height="+mButton.getHeight());

    }
}