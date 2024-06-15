package com.oyoung.diary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author OyoungZh
 * @brief description
 * @date 2022-11-09
 */
public class BitmapUtils {
    /**
     * 将图片保存到sdcard
     * @param colorImage
     * @param ImageName
     * @param path
     */
    public static void storeImageToSDCARD(Bitmap colorImage, String ImageName,
                                          String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        File imagefile = new File(file, ImageName + ".jpg");
        try {
            imagefile.createNewFile();
            FileOutputStream fos = new FileOutputStream(imagefile);
            colorImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从sdcard中提取图片
     * @param path
     * @param picName
     * @return
     */
    public static Bitmap getImg(String path,String picName) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        try {
            File file = new File(path, picName + ".jpg");
            FileInputStream inputStream = new FileInputStream(file);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }
}
