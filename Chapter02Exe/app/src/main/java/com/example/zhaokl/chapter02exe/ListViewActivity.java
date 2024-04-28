package com.example.zhaokl.chapter02exe;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zhaokl.adapter.ButonListItemAdapter;


public class ListViewActivity extends AppCompatActivity {
    //数据源列表
    private String[] mListStr = {"姓名：张三", "性别：男", "年龄：25", "居住地：青岛",
            "邮箱：zhangsan@163.com"};
    ListView mListView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置Activity的布局
        setContentView(R.layout.activity_listview);
        //获取id为listview的ListView组件
        mListView = (ListView) findViewById(R.id.listview);
        ButonListItemAdapter adapter = new ButonListItemAdapter(this, mListStr);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(ListViewActivity.this,
                        "您选择了" + mListStr[position], Toast.LENGTH_LONG).show();
            }
        });
    }
}
