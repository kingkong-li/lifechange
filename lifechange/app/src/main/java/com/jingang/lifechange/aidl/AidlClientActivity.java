package com.jingang.lifechange.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.TextView;

import com.example.networklib.ICommonCallback;
import com.example.networklib.ICommonInterface;
import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;

public class AidlClientActivity extends BaseActivity {

    private static final String TAG = "AidlClientActivity";
    private ICommonInterface mRemoteHandler;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_client);
        mTextView = findViewById(R.id.textView);
        connectRemoteService();
    }

    private void connectRemoteService() {
        Intent intent = new Intent("com.example.networklib.MyService");
        intent.setPackage("com.example.networklib");
        intent.putExtra("package", "com.jingang.lifechange");

        boolean success = this.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        Log.v(TAG, "bindService success=" + success);

    }
    private final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.v(TAG, "onServiceConnected");
            mRemoteHandler = ICommonInterface.Stub.asInterface(service);
            try {
                mRemoteHandler.registerCallback(new ICommonCallback.Stub() {
                    @Override
                    public void onEvent(String code, String msg) throws RemoteException {
                        Log.v(TAG, "onEvent code=" + code + " msg=" + msg);
                        mTextView.setText(String.format("%s:%s", code, msg));
                    }
                });
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.v(TAG, "onServiceDisconnected");
            // 重连
            connectRemoteService();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unbindService(mConnection);
    }
}