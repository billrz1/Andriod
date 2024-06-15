package com.oyoung.diary.application;

import android.app.Application;

import com.oyoung.diary.utils.GlideUtils;

/**
 * 应用
 */
public class YyApplication extends Application {
    private static YyApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        GlideUtils.init(getApplicationContext());
        INSTANCE = this;
    }

    public static YyApplication get() {
        return INSTANCE;
    }
}
