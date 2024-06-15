package com.oyoung.diary.mine;

import static android.app.Activity.RESULT_OK;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.imageview.ShapeableImageView;
import com.oyoung.diary.R;
import com.oyoung.diary.utils.BitmapUtils;
import com.oyoung.diary.utils.FileUtils;
import com.oyoung.diary.utils.GlideUtils;
import com.oyoung.diary.utils.PermissionUtils;
import java.io.File;
import java.util.Objects;

public class MeFragment extends Fragment implements View.OnClickListener {
    private static final int CHOOSE_PICTURE = 0;
    private static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    private static final String sightsUrl = "http://pic2.zhimg.com/v2-696b347aa5b02a943706d5de13dc6ec1_r.jpg";

    private TextView tv_password;
    private ImageView imageView;
    private ShapeableImageView shapeableImageView;
    private MeController meController;
    private boolean isShow = true;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        meController = new MeController(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_me, container, false);
        initView(root);
        return root;
    }

    private void initView(View root) {
        imageView = root.findViewById(R.id.image_me);
        TextView tv_update = root.findViewById(R.id.tv_update);
        tv_update.setOnClickListener(this);
        TextView tv_setting = root.findViewById(R.id.tv_setting);
        tv_setting.setOnClickListener(this);
        tv_password = root.findViewById(R.id.tv_password);
        shapeableImageView = root.findViewById(R.id.iv_avatar);
        shapeableImageView.setOnClickListener(this);
        initAvatarView();
        setTouch();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setTouch() {
        tv_password.setOnTouchListener((v, event) -> {
            Drawable drawable = tv_password.getCompoundDrawables()[2];
            if (drawable == null) {
                return false;
            }
            if (tv_password.getWidth() - event.getX() < drawable.getBounds().width()) {
                if (isShow) {
                    Drawable on_drawable = ContextCompat.getDrawable(requireContext(), R.drawable.eye_on);
                    tv_password.setCompoundDrawablesWithIntrinsicBounds(null, null, on_drawable, null);
                    String password = FileUtils.readInfoByContext(requireActivity());
                    tv_password.setText(password);
                    isShow = false;
                } else {
                    Drawable off_drawable = ContextCompat.getDrawable(requireContext(), R.drawable.eye_off);
                    tv_password.setCompoundDrawablesWithIntrinsicBounds(null, null, off_drawable, null);
                    tv_password.setText(getString(R.string.password_hide_text));
                    isShow = true;
                }
            }
            return false;
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (R.id.tv_update == id) {
            GlideUtils.getBitmap(getActivity(), sightsUrl, bitmap -> imageView.setImageBitmap(bitmap));
        } else if (R.id.tv_setting == id) {
            meController.toSelfSetting(requireActivity());
        } else if (R.id.iv_avatar == id) {
            PermissionUtils.checkPermission(getActivity());
            if (PermissionUtils.isPermissionGranted(getActivity())) {
                meController.showSelectWindow();
            }
        }
    }

    public void initAvatarView() {
        File roundImageFile = new File(requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "roundImage.jpg");
        // 该路径为/storage/emulated/0/Android/data/yourPackageName/files/Picture
        if (roundImageFile.exists()) {
            shapeableImageView.setImageBitmap(BitmapUtils.getImg(
                            requireActivity().getExternalFilesDir(
                                    Environment.DIRECTORY_PICTURES).getAbsolutePath(), "roundImage"));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    meController.startPhotoZoom(MeController.getTempUri()); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    if (data != null) {
                        meController.startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    }
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionUtils.REQUEST_CODE) {
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    // 授权失败，退出应用
                    requireActivity().finish();
                    return;
                }
            }
        }
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param data 图片
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            BitmapUtils.storeImageToSDCARD(photo, "roundImage", Objects.requireNonNull(requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)).getAbsolutePath());
            shapeableImageView.setImageBitmap(photo);
        }
    }

}
