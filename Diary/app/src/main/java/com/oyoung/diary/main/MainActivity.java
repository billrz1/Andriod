package com.oyoung.diary.main;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.oyoung.diary.R;
import com.oyoung.diary.mine.MeController;
import com.oyoung.diary.utils.ActivityUtils;
import com.oyoung.diary.diaries.AddDiaryFragment;
import com.oyoung.diary.diaries.DiariesFragment;
import com.oyoung.diary.mine.MeFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private DiariesFragment diariesFragment;
    private MeFragment meFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initFragment();
        initNavigationBottom();
    }

    @SuppressLint("ResourceAsColor")
    private void initNavigationBottom() {
        bottomNavigationView = findViewById(R.id.navigation_bottom);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnItemSelectedListener(itemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private void initFragment() {
        getCurrentFragment();
        if (diariesFragment == null) {
            diariesFragment = new DiariesFragment();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), diariesFragment, R.id.content);
        }
        if (meFragment == null) {
            meFragment = new MeFragment();
        }
    }

    private void getCurrentFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content);
        if (fragment instanceof DiariesFragment) {
            diariesFragment = (DiariesFragment) fragment;
        } else if (fragment instanceof MeFragment) {
            meFragment = (MeFragment) fragment;
        }
    }

    private void initToolbar() {
        //设置顶部状态栏为透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private final NavigationBarView.OnItemSelectedListener itemSelectedListener = item -> {
        int id = item.getItemId();
        if (id == R.id.menu_diary) {
            MeController.setToolbarVisibility(this);
            ActivityUtils.replaceFragmentTOActivity(getSupportFragmentManager(), diariesFragment);
        } else if (id == R.id.menu_me) {
            findViewById(R.id.toolbar).setVisibility(View.GONE);
            ActivityUtils.replaceFragmentTOActivity(getSupportFragmentManager(), meFragment);
        } else if (id == R.id.menu_new) {
            bottomNavigationView.setVisibility(View.GONE);
            MeController.setToolbarVisibility(this);
            ActivityUtils.removeFragmentTOActivity(getSupportFragmentManager(), getSupportFragmentManager().findFragmentById(R.id.content));
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), new AddDiaryFragment(), R.id.content);
        }
        return true;
    };
}