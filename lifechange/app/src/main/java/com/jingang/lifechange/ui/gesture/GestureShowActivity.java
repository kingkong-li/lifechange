package com.jingang.lifechange.ui.gesture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;

/**
 * @author lijingang02
 */
public class GestureShowActivity extends BaseActivity {
    private GestureCoverLayer mGestureCoverLayer;
    private boolean mNowIsShowCover = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_show);


        TextView textView = findViewById(R.id.tv_player_mock);
        Log.v(getTag(), "find view");
        mGestureCoverLayer = findViewById(R.id.gc_my_cover);
        Log.v(getTag(), "find view mGestureCoverLayer="+mGestureCoverLayer);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!mNowIsShowCover) {
//                    mNowIsShowCover = true;
//                    mGestureCoverLayer.showGestureCover();
//                } else {
//                    mGestureCoverLayer.hideGestureCover();
//                    mNowIsShowCover = false;
//                }

            }
        });

    }
}