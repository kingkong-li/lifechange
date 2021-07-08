package com.jingang.lifechange.algorithm.struct.vector;

import android.os.Bundle;
import android.util.Log;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;

public class VectorAndStackTestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector_and_stack);
        MinMaxStack minMaxStack=new MinMaxStack();
        minMaxStack.push(1);
        minMaxStack.push(2);
        minMaxStack.push(4);
        Log.v(getTag(),"MinMaxStack peek="+minMaxStack.peek()+" max="+
                minMaxStack.getMax()+" min="+minMaxStack.getMin());

        new HanNuoTa().getMoveStepTest();
    }
}