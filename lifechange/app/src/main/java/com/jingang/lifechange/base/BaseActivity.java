package com.jingang.lifechange.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.jingang.lifechange.R;


/**
 * @author jingang
 */
public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar()!=null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getLabel());
        }
        super.onCreate(savedInstanceState);
        Log.v(getTag(),"onCreate");
        setContentView(R.layout.activity_base);
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
        Log.v(getTag(),"onStart");
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
        Log.v(getTag(),"onResume");
        getWindow().getDecorView().getViewTreeObserver().addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
            @Override
            public void onDraw() {
                Log.v(getTag(),"onDecorView draw ,first draw");
            }
        });
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
