package com.oyoung.diary.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.oyoung.diary.R;

public class ActivityUtils {
    public static void addFragmentToActivity(FragmentManager manager, Fragment fragment, int fragmentId) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(fragmentId, fragment);
        transaction.commit();
    }

    public static void removeFragmentTOActivity(FragmentManager manager, Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();
    }

    public static void replaceFragmentTOActivity(FragmentManager manager, Fragment fragment) {
        Fragment currentFragment = manager.findFragmentById(R.id.content);
        if (fragment == currentFragment) {
            return;
        }
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }
}
