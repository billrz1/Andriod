package com.oyoung.diary.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.oyoung.diary.main.MainActivity;
import com.oyoung.diary.R;
import com.oyoung.diary.utils.FileUtils;

public class LoginDirectActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_input_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_login);
        bindView();
    }

    private void bindView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        edit_input_text = findViewById(R.id.edit_login2_input_text);
        Button btn_comeIn = findViewById(R.id.btn_login2_comeIn);
        btn_comeIn.setOnClickListener(this);
        TextView tv_setPsw = findViewById(R.id.tv_setPsw);
        tv_setPsw.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (R.id.tv_setPsw == id) {
            Intent setPsw_intent = new Intent(LoginDirectActivity.this, LoginActivity.class);
            startActivity(setPsw_intent);
            LoginDirectActivity.this.finish();
        } else if (R.id.btn_login2_comeIn == id) {
            String psw = edit_input_text.getText().toString().trim();
            if (psw.isEmpty()) {
                Toast.makeText(this, getString(R.string.password_is_not_empty_toast), Toast.LENGTH_SHORT).show();
                return;
            }
            String readInfoByContext = FileUtils.readInfoByContext(this);
            if (psw.equals(readInfoByContext)) {
                Toast.makeText(this, getString(R.string.login_success_toast), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, getString(R.string.password_is_not_correct_toast), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
