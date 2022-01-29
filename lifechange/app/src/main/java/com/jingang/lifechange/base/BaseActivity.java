package com.jingang.lifechange.base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jingang.lifechange.R;


/**
 * @author jingang
 */
public class BaseActivity extends AppCompatActivity {
    private volatile int time=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar()!=null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getLabel());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Log.v(getTag(),"onCreate"+Log.getStackTraceString(new Throwable()));
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context,
                             @NonNull AttributeSet attrs) {
        Log.v(getTag(),"onCreateView");
        return super.onCreateView(parent, name, context, attrs);

    }


    /**
     * 获取每个应用的标题名称
     * @return
     */
    protected String getLabel(){
       return this.getClass().getSimpleName();
    }

    protected String getTag() {
        return "lifeCycle:"+this.getClass().getSimpleName();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(getTag(),"onStart"+Log.getStackTraceString(new Throwable()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(getTag(),"onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(getTag(),"onResume"+Log.getStackTraceString(new Throwable()));
        time=0;
        getWindow().getDecorView().getViewTreeObserver().addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
            @Override
            public void onDraw() {
                Log.v(getTag(),"onDecorView draw , draw time"+time);
                addTime();
            }
        });
    }

    private synchronized void addTime() {
        time++;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(getTag(),"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(getTag(),"onStop");
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v(getTag(),"onSaveInstanceState");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(getTag(),"onDestroy");
    }
}
