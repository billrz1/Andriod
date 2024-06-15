 package com.oyoung.diary.guide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.WindowManager;

import com.oyoung.diary.R;
import com.oyoung.diary.login.LoginDirectActivity;

import java.lang.ref.WeakReference;

 public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        SharedPreferences sp = getSharedPreferences("Start", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        StartHandler mHandler = new StartHandler(getMainLooper(), this);
        boolean isFirst = sp.getBoolean("isFirst", true);
        if (isFirst) {
            mHandler.sendEmptyMessageDelayed(0, 1000);
            editor.putBoolean("isFirst", false);
            editor.apply();
        } else {
            mHandler.sendEmptyMessageDelayed(1, 1000);
        }
    }

     static class StartHandler extends Handler {
         private final WeakReference<StartActivity> activityWeakReference;
         private static final int GO_LOGIN = 1;
         private static final int GO_INTRODUCE = 0;

         public StartHandler(Looper looper, StartActivity activity) {
             super(looper);
             activityWeakReference = new WeakReference<>(activity);
         }
         @Override
         public void handleMessage(@NonNull Message msg) {
             switch (msg.what) {
                 case GO_LOGIN:
                     Intent login_intent = new Intent(activityWeakReference.get(), LoginDirectActivity.class);
                     activityWeakReference.get().startActivity(login_intent);
                     activityWeakReference.get().finish();
                     break;
                 case GO_INTRODUCE:
                     Intent login2_intent = new Intent(activityWeakReference.get(), GuideActivity.class);
                     activityWeakReference.get().startActivity(login2_intent);
                     activityWeakReference.get().finish();
                     break;
             }
         }
     }
}