package com.example.zhaokl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhaokl.chapter02exe.R;

public class ButonListItemAdapter extends BaseAdapter {
    private Context mContext;
    // 展示的文字
    private String[] texts;

    public ButonListItemAdapter(Context context, String[] texts) {
        this.mContext = context;
        this.texts = texts;
    }

    /**
     * 元素的个数
     */
    public int getCount() {
        return texts.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // 用以生成在ListView中展示的一个个元素View
    public View getView(int position, View convertView, ViewGroup parent) {
        // 优化ListView
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listitem,
                    null);
            ItemViewCache viewCache = new ItemViewCache();
            viewCache.mTextView = (TextView) convertView.findViewById(R.id.addTxt);
            viewCache.mBtnChange = (Button) convertView.findViewById(R.id.changeBtn);
            viewCache.mBtnDel = (Button) convertView.findViewById(R.id.delBtn);
            convertView.setTag(viewCache);
        }
        ItemViewCache cache = (ItemViewCache) convertView.getTag();
        // 设置文本和图片，然后返回这个View，用于ListView的Item的展示
        cache.mTextView.setText(texts[position]);
        cache.mBtnChange.setText("修改");
        cache.mBtnDel.setText("删除");
        return convertView;
    }

    // 元素的缓冲类,用于优化ListView
    private class ItemViewCache {
        public TextView mTextView;
        public Button mBtnChange;
        public Button mBtnDel;
    }
}