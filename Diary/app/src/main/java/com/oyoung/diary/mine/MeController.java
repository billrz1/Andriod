package com.oyoung.diary.mine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import com.oyoung.diary.R;
import com.oyoung.diary.view.SelectPicPopupWindow;
import java.io.File;

public class MeController {
    private static final int CHOOSE_PICTURE = 0;
    private static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;

    private final Fragment mView;

    public static Uri getTempUri() {
        return tempUri;
    }

    protected static Uri tempUri;
    private SelectPicPopupWindow menuWindow;

    public MeController(MeFragment meFragment) {
        this.mView = meFragment;
    }

    // 为弹出窗口实现监听类
    private final View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            menuWindow.dismiss();
            int id = v.getId();
            if (R.id.tv_take_pictures == id) {
                Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = new File(mView.requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "image.jpg");
                tempUri = FileProvider.getUriForFile(mView.requireActivity(), mView.requireActivity().getPackageName() + ".fileprovider", file);
                Log.d("11111111", tempUri.toString());
                // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                mView.startActivityForResult(openCameraIntent, TAKE_PICTURE);
            } else if (R.id.tv_open_album == id) {
                Intent openAlbumIntent = new Intent(Intent.ACTION_PICK);
                openAlbumIntent.setType("image/*");
                mView.startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
            }
        }
    };

    /**
     * 跳转应用系统设置界面
     */
    public void toSelfSetting(Context context) {
        Intent mIntent = new Intent();
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        mIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivity(mIntent);
    }

    /**
     * 弹出头像选择框
     */
    public void showSelectWindow() {
        menuWindow = new SelectPicPopupWindow(mView.getActivity(), itemsOnClick);
        // 获取当前窗口的像素
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) mView.requireActivity().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        // 弹窗为屏幕宽度的一半
        menuWindow.setWidth(dm.widthPixels);
        // 从底部显示
        menuWindow.showAtLocation(mView.requireActivity().findViewById(R.id.layout_update), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0 ,0);
    }

    /**
     * 裁剪图片方法实现
     */
    public void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        mView.startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    public static void setToolbarVisibility(Activity activity) {
        View toolbar = activity.findViewById(R.id.toolbar);
        if (toolbar.getVisibility() != View.VISIBLE) {
            toolbar.setVisibility(View.VISIBLE);
        }
    }
}
