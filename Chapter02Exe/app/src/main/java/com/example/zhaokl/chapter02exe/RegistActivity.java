package com.example.zhaokl.chapter02exe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhaokl.db.DBHelper;
import com.example.zhaokl.db.User;

import java.util.regex.Pattern;

public class RegistActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtPwd;
    private EditText edtRePwd;
    private EditText edtEmail;
    private Button btnRegist;
    private String strName; //存入账号的变量
    private String strPwd;   //存入密码的变量
    private String strRePwd;   //存入确认密码的变量
    private String strEmail;   //存入确认邮箱的变量
    //邮箱正则表达式
    public static final String REGEX_EMAIL = "^[a-z0-9A-Z.%+-]+@[a-z0-9A-Z.-]+\\.[a-zA-Z]{2,6}$";
    //"^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,6}$";
    ContentResolver contentResolver;
    Uri uri = Uri.parse("content://com.example.zhaokl.userprovider/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        // 获取系统的ContentResolver对象
        contentResolver = getContentResolver();

        edtName = findViewById(R.id.edtName);     //绑定账号Edit Text的id
        edtPwd = findViewById(R.id.edtPwd);     //绑定密码Edit Text的id
        edtRePwd = findViewById(R.id.edtrePwd);
        edtEmail = findViewById(R.id.edtEmail);
        btnRegist = findViewById(R.id.btRegist);             //绑定按钮Button的id
        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strName = edtName.getText().toString();
                strPwd = edtPwd.getText().toString();
                strRePwd = edtRePwd.getText().toString();
                strEmail = edtEmail.getText().toString();
                if (strName.equals("") || strPwd.equals("") || strRePwd.equals("") || strEmail.equals("")) {
                    Toast.makeText(RegistActivity.this, "注册信息不能为空，请填写完整！", Toast.LENGTH_SHORT).show();
                } else if (strPwd.length() != 6) {     //if判断输入账号的长度是不是6位数，如果不是则弹窗提示
                    Toast.makeText(RegistActivity.this, "请输入6位数的密码", Toast.LENGTH_SHORT).show();
                } else if (!strRePwd.equals(strPwd)) {
                    Toast.makeText(RegistActivity.this, "确认密码与密码必须一致！", Toast.LENGTH_SHORT).show();
                }
//                else if (!Pattern.matches(REGEX_EMAIL, strEmail)) {
//                    Toast.makeText(RegistActivity.this, "请输入正确的邮箱！", Toast.LENGTH_SHORT).show();
//                }
                else //if (strName.equals("zhaokl")&&strPwd.equals("123456")){
                {
                   // insert(strName, strPwd);
                    // 将用户名和密码封装到User实体对象
                    User  user=new User(strName,strPwd);
                    //数据库操作，插入新用户
                    DBHelper dbh=new DBHelper(getApplicationContext());
                    dbh.insert(user);
                    Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
//                else{
//                    Toast.makeText(RegistActivity.this,"注册失败，请重新输入",Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    public void insert(String uName, String uPwd) {
        ContentValues values = new ContentValues();
        values.put("uName", uName);
        values.put("uPwd", uPwd);
        // 调用ContentResolver的insert()方法。
        Uri newUri = contentResolver.insert(uri, values);
    }
}