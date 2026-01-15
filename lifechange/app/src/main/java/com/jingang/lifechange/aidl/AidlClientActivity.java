package com.jingang.lifechange.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.TextView;

import com.example.networklib.ICommonCallback;
import com.example.networklib.ICommonInterface;
import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;
import com.jingang.lifechange.utils.PublicThreadPools;

public class AidlClientActivity extends BaseActivity {

    private static final String TAG = "AidlClientActivity";
    private ICommonInterface mRemoteInterface;
    private TextView mTextView;

    private Handler UIHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_client);
        mTextView = findViewById(R.id.textView);
        UIHandler = new Handler(getMainLooper());
        connectRemoteService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        PublicThreadPools.getService().submit(new Runnable() {
            @Override
            public void run() {
                if (mRemoteInterface == null){
                    return;
                }
                try {
                    mRemoteInterface.set("Client Activity onResume");
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });
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
            mRemoteInterface = ICommonInterface.Stub.asInterface(service);

            PublicThreadPools.getService().submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Log.v(TAG, "onServiceConnected run task");
                        mRemoteInterface.set("Client onServiceConnected");
                        mRemoteInterface.registerCallback(new ICommonCallback.Stub() {
                            @Override
                            public void onEvent(final String code, final String msg) throws RemoteException {
                                Log.v(TAG, "onEvent code=" + code + " msg=" + msg);
                                UIHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mTextView.setText(String.format("%s:%s", code, msg));
                                    }
                                });

                            }
                        });
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.v(TAG, "onServiceDisconnected");
            // 重连
            connectRemoteService();
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        PublicThreadPools.getService().submit(new Runnable() {
            @Override
            public void run() {
                try {
                    mRemoteInterface.set("Client Activity onStop");
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unbindService(mConnection);
    }


}