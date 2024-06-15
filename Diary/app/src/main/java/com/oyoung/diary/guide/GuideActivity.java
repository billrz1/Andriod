package com.oyoung.diary.guide;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.oyoung.diary.R;
import com.oyoung.diary.login.LoginDirectActivity;
import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<View> viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        initView();
        initAdapter();
        initStart();
    }

    private void initStart() {
        Button button = viewList.get(2).findViewById(R.id.btn_goto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this, LoginDirectActivity.class));
                GuideActivity.this.finish();
            }
        });
    }

    private void initAdapter() {
        GuideAdapter adapter = new GuideAdapter(viewList);
        viewPager.setAdapter(adapter);
    }

    private void initView() {
        //设置顶部状态栏为透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        viewPager = findViewById(R.id.introduce_viewPager);
        viewList = new ArrayList<>();
        viewList.add(getView(R.layout.app_guide_one));
        viewList.add(getView(R.layout.app_guide_two));
        viewList.add(getView(R.layout.app_guide_three));
    }

    private View getView(int resId) {
        return LayoutInflater.from(this).inflate(resId,null);
    }
}