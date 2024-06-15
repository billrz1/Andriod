package com.oyoung.diary.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.oyoung.diary.R;
import com.oyoung.diary.view.LoadingDialog;

import java.lang.ref.WeakReference;

/**
 * @author OyoungZh
 * @brief 加载图片的工具类
 * @date 2022-11-07
 */
public class GlideUtils {
    private static WeakReference<Context> contextWeakReference;
    private static final String TAG = "GlideUtil";
    private static LoadingDialog loadingDialog;

    public static void init(Context context) {
        contextWeakReference = new WeakReference<>(context);
        Log.i(TAG, "Glide初始化成功");
    }

    public static void loadImage(String url, ImageView imageView) {
        Glide.with(contextWeakReference.get()).load(url).into(imageView);
    }

    public interface GlideLoadBitmapCallback{
        void getBitmapCallback(Bitmap bitmap);
    }

    public static void getBitmap(Context context, String uri, final GlideLoadBitmapCallback callback) {
        loadingDialog = new LoadingDialog(context, R.style.loading_dialog);
        Glide.with(context).asBitmap().load(uri).centerCrop()
                .override(200, 200)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        if (loadingDialog != null) {
                            loadingDialog.show();
                        }
                    }

                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                        }
                        callback.getBitmapCallback(resource);
                    }
                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {}
                });
    }
}
