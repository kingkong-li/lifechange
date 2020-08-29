package com.jingang.lifecyclecase.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author:
 * @CreateTime:2020/8/7-6:15 PM
 * @changeTime:
 */
public class ActivityCache {
    private static List<Activity> sActivityList=new ArrayList<>();
    public static void   addActivity(Activity activity){
        sActivityList.add(activity);
    }
}
