package com.fulei.fuleitest.application;

import android.app.Application;
import android.content.Context;

import com.fulei.fuleitest.greendao.DaoManager;

/**
 * Created by gcl on 2017/8/1.
 */

public class MyApplication extends Application{

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        DaoManager.getInstance();

    }
    public static Context getContext(){

        return context;

    }



}
