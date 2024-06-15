package com.oyoung.diary.utils;

import android.content.Context;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author RZ
 * @brief description
 * @date 2024-6-15
 */
public class FileUtils {

    /**
     * 1.
     * @param context 上下文
     * @param password 密码
     * @return 是否成功保存
     */
    public static boolean saveInfoByContext(Context context, String password) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("info.txt", Context.MODE_PRIVATE);
            byte[] psw_bytes = password.getBytes();
            byte[] encode = Base64.encode(psw_bytes, Base64.DEFAULT);
            fileOutputStream.write(encode);
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 2.
     * 通过上下文的方式读取保存在.txt文件中的密码
     * @return 是否成功读取
     */
    public static String readInfoByContext(Context context) {
        try {
            FileInputStream fis = context.openFileInput("info.txt");
            ByteArrayOutputStream psw_temp = new ByteArrayOutputStream();
            byte[] psw_bytes = new byte[1024];
            int len;
            while ((len = fis.read(psw_bytes)) != -1) {
                psw_temp.write(psw_bytes, 0 ,len);
            }
            byte[] decode = Base64.decode(psw_bytes, Base64.DEFAULT);
            fis.close();
            psw_temp.close();
            return new String(decode);
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
