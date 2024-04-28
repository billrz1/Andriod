package com.example.zhaokl.chapter02exe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhaokl.db.DBHelper;

public class LoginActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtPwd;
    private Button btnLogin;
    private String strName; //存入账号的变量
    private String strPwd;   //存入密码的变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtName = findViewById(R.id.edtName);     //绑定账号Edit Text的id
        edtPwd = findViewById(R.id.edtPwd);     //绑定密码Edit Text的id
        btnLogin = findViewById(R.id.btLogin);             //绑定按钮Button的id
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strName = edtName.getText().toString();
                strPwd = edtPwd.getText().toString();
                if (strName.equals("")||strPwd.equals("")){
                    Toast.makeText(LoginActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                }else if (strPwd.length()!=6){     //if判断输入账号的长度是不是6位数，如果不是则弹窗提示
                    Toast.makeText(LoginActivity.this,"请输入6位数的密码",Toast.LENGTH_SHORT).show();
                }
                //if(strName.equals("zhaokling")&&strPwd.equals("888888"))
                //数据库查询
                DBHelper dbh=new DBHelper(getApplicationContext());
                if(dbh.check(strName,strPwd))
                {
                    //登录成功
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this,"账号或密码输入错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}