package com.oyoung.diary.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.oyoung.diary.R;

import java.util.Objects;


/**
 * @author OyoungZh
 * @brief description
 * @date 2022-11-07
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.loading_dialog);
        ImageView ivLoading = findViewById(R.id.iv_loading);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.loading_animation);
        ivLoading.startAnimation(animation);
    }
}
