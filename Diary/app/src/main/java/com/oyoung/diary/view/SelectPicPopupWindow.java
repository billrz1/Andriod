package com.oyoung.diary.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.oyoung.diary.R;


/**
 * @author OyoungZh
 * @brief 底部弹窗选照片
 * @date 2022-11-08
 */
public class SelectPicPopupWindow extends PopupWindow {
    private final View mMenuView;

    @SuppressLint("InflateParams")
    public SelectPicPopupWindow(final Activity context, OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.dialog_button, null);
        TextView btn_take_photo = mMenuView.findViewById(R.id.tv_take_pictures);
        TextView btn_pick_photo = mMenuView.findViewById(R.id.tv_open_album);
        TextView btn_cancel = mMenuView.findViewById(R.id.tv_cancel);
        //取消按钮
        btn_cancel.setOnClickListener(v -> {
            //销毁弹出框
            dismiss();
        });
        //设置按钮监听
        btn_pick_photo.setOnClickListener(v -> {
            Toast.makeText(context, context.getString(R.string.pick_photo_toast), Toast.LENGTH_SHORT).show();
            itemsOnClick.onClick(v);
        });
        btn_take_photo.setOnClickListener(v -> {
            Toast.makeText(context, context.getString(R.string.take_photo_toast), Toast.LENGTH_SHORT).show();
            itemsOnClick.onClick(v);
        });
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.getTop();
                int y=(int) event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });
    }
}
