package com.oyoung.diary.utils;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.oyoung.diary.application.YyApplication;

public class PermissionUtils {
    public static final int REQUEST_CODE = 5;
    //定义三个权限
    private static final String[] bellowRPermission = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    //每个权限是否已授
    public static boolean isPermissionGranted(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager() && isHasCameraPermission(activity);
        } else {
            for (String s : bellowRPermission) {
                int checkPermission = ContextCompat.checkSelfPermission(activity, s);
                if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
            return true;
        }
    }

    private static boolean isHasCameraPermission(Activity activity) {
        int checkPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        return checkPermission == PackageManager.PERMISSION_GRANTED;
    }

    public static void checkPermission(Activity activity) {
        if (!isPermissionGranted(activity)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + YyApplication.get().getPackageName()));
                startActivityForResult(activity, intent, REQUEST_CODE, null);
                if (!isHasCameraPermission(activity)) {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
                }
            } else {
                //如果没有设置过权限许可，则弹出系统的授权窗口
                ActivityCompat.requestPermissions(activity,bellowRPermission,REQUEST_CODE);
            }
        }
    }
}
