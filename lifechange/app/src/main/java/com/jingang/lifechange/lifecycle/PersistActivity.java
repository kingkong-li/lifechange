package com.jingang.lifechange.lifecycle;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;

/**
 * @author kingkong
 */
public class PersistActivity extends BaseActivity {
    private int mStartTime=0;
    private static final String START_TIME="start_time";

    @Override
    public void onCreate(Bundle savedInstanceState,PersistableBundle persistableBundle) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persist);
        int originTime = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
             originTime=persistableBundle.getInt(START_TIME);
        }

        mStartTime=mStartTime+originTime;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStartTime++;
        Log.v(getTag(),"originTime="+getStartTime(this));
    }

    /**
     * 仔细看这个方法  由于它处于PersistActivity的内部 所以它的作用可以访问到私有变量
     * 这就是内部类可以访问外部类私有字段的原理。也就是说，当我们写一个内部类的时候，就会把外部类
     * 实例给到内部类，如果我们需要获取/设置 某个外部类字段，编译时候 就会生成这种静态的方法，使我们能够进行操作。
     * @param persistActivity
     * @return
     */
    public static int getStartTime(PersistActivity persistActivity){
        return persistActivity.mStartTime;
    }
    /**
     * 何时调用呢？
     * @param outState
     * @param outPersistentState
     */
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Log.v(getTag(),"onSaveInstance start");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            outPersistentState.putInt(START_TIME,mStartTime);
        }
        super.onSaveInstanceState(outState, outPersistentState);
    }
}