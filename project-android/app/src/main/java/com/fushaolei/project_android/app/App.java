package com.fushaolei.project_android.app;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fushaolei.project_android.helper.MMKVHelper;
import com.tencent.mmkv.MMKV;

public class App extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(App.this);

        MMKV.initialize(this);
        if(MMKVHelper.isNightMod()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        context = getApplicationContext();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }

    public static Context getContext() {
        return context;
    }
}
