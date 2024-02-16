package com.jit.funchemistry.utils;

import android.app.Activity;

import java.util.ArrayList;

import static java.lang.System.exit;

/**
 * @author XiaNingIf
 * @date 2021/3/18
 */
public class ActivityUtil {
    private ArrayList<Activity> mActivityList = new ArrayList<>();

    private static final ActivityUtil ourInstance = new ActivityUtil();

    public static ActivityUtil getInstance(){
        return ourInstance;
    }

    public void addActivity(Activity activity){
        if (mActivityList==null){
            mActivityList = new ArrayList();
        }
        mActivityList.add(activity);
    }

    public void removeActivity(Activity activity){
        mActivityList.remove(activity);
    }

    public void exitSystem(){
        for (Activity activity : mActivityList) {
            activity.finish();
        }
        exit(0);
    }
}
