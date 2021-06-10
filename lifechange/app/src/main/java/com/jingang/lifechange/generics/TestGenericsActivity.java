package com.jingang.lifechange.generics;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TestGenericsActivity extends BaseActivity {
    private final static String TAG=TestGenericsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_generics);


        ParameterizedType superType = (ParameterizedType) DemoClass.class.getGenericSuperclass();
        for(Type ActualTypeArgument:superType.getActualTypeArguments()){
            Log.v(TAG,"parameterTypes ="+ActualTypeArgument.toString());
        }








    }

    @Override
    protected void onStart() {
        super.onStart();
        String[] PermissionString={Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA};
        ActivityCompat.requestPermissions(this,PermissionString,100);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            Log.v(TAG,"hava permission");
        }
    }

}