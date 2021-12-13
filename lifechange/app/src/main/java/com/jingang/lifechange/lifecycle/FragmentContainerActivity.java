package com.jingang.lifechange.lifecycle;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;
import com.jingang.lifechange.ui.SimpleFragment;
import com.jingang.lifechange.utils.LogUtil;

/**
 * 此Activity为了测试Fragment声明周期
 * @author lijingang02
 */
public class FragmentContainerActivity extends BaseActivity {

    private FragmentManager mFragmentManager;

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        Log.v(getTag(),"onAttachFragment fragment="+fragment.getTag()+", "+fragment.getId());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager=getSupportFragmentManager();

        setContentView(R.layout.activity_fragment_container);
        if(savedInstanceState!=null){
            Log.v(getTag(),"onCreates savedInstanceState="+savedInstanceState.toString());
            Fragment fragment = mFragmentManager.findFragmentByTag(SimpleFragment.class.getSimpleName());
            if(fragment!=null){
                Log.v(getTag(), "onCreates fragment!=null");
            }

        }else{
            Fragment fragment = new SimpleFragment();
            FragmentTransaction fragmentTransaction= mFragmentManager.beginTransaction();
            fragmentTransaction.add(fragment,SimpleFragment.class.getSimpleName());
            fragmentTransaction.replace(R.id.fragment_activity_root,fragment);
            fragmentTransaction.commit();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
//        FragmentTransaction fragmentTransaction= mFragmentManager.beginTransaction();
//        Fragment fragment=mFragmentManager.findFragmentByTag(SimpleFragment.class.getSimpleName());
//        // 这样就会产生一个崩溃
//        fragmentTransaction.remove(fragment);
//        fragmentTransaction.commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}