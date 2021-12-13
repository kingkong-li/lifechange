package com.jingang.lifechange.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingang.lifechange.R;

/**
 * @author lijingang02
 */
public abstract class BaseFragment extends Fragment {


    protected String getClassName() {
        return this.getClass().getSimpleName();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(getTag(),"onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v(getTag(),"onCreateView");
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        Log.v(getTag(),"onAttachFragment");
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.v(getTag(),"onAttachFragment");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.v(getTag(),"onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.v(getTag(),"onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.v(getTag(),"onPause");
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        Log.v(getTag(),"onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.v(getTag(),"onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.v(getTag(),"onDetach");
        super.onDetach();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.v(getTag(),"onHiddenChanged hidden="+hidden);
        super.onHiddenChanged(hidden);
    }
}