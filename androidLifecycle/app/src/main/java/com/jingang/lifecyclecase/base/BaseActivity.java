package com.jingang.lifecyclecase.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.jingang.lifecyclecase.R;

/**
 * @author jingang
 */
public class BaseActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.v(getTag(),"onCreate");
        setContentView(R.layout.activity_base);
    }

    protected String getTag() {
        return this.getClass().getSimpleName();
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
    protected void onDestroy() {
        super.onDestroy();
        Log.v(getTag(),"onDestroy");
    }
}
