package com.jingang.lifechange;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.jingang.lifechange.base.BaseActivity;

/**
 * @author lijingang02
 */
public class SplashActivity extends BaseActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();
    private final static int OPEN_PERMISSION_CODE = 103;
    private final String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkCriticalPermissions();



    }
    private void checkCriticalPermissions() {
        boolean haveAllPermission = true;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                haveAllPermission = false;
                break;
            }
        }
        if (!haveAllPermission) {
            ActivityCompat.requestPermissions(this, permissions, OPEN_PERMISSION_CODE);
        } else {
            Log.v(getTag(), "checkCriticalPermissions haveAllPermission");
            startMainActivity();
        }

    }

    private void startMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        SplashActivity.this.startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == OPEN_PERMISSION_CODE) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }
            if (allGranted) {
                startMainActivity();
            } else {
                Log.e(TAG, "onRequestPermissionsResult: Permission denied");
                finish();
            }
        }

    }
}