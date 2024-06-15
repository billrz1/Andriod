package com.oyoung.diary.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.oyoung.diary.main.MainActivity;
import com.oyoung.diary.R;
import com.oyoung.diary.utils.FileUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edit_input_text;
    private EditText edit_input_text_again;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindView();
    }

    private void bindView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        edit_input_text = findViewById(R.id.edit_input_text);
        edit_input_text_again = findViewById(R.id.edit_input_text_again);
        Button btn_comeIn = findViewById(R.id.btn_comeIn);
        btn_comeIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_comeIn) {
            String psw = edit_input_text.getText().toString().trim();
            String psw_again = edit_input_text_again.getText().toString().trim();
            if (psw.isEmpty()) {
                Toast.makeText(this, getString(R.string.password_is_not_empty_toast), Toast.LENGTH_SHORT).show();
                return;
            }
            if (psw_again.isEmpty()) {
                Toast.makeText(this, getString(R.string.input_password_again_toast), Toast.LENGTH_SHORT).show();
                return;
            }
            if (!psw.equals(psw_again)) {
                Toast.makeText(this, getString(R.string.password_twice_not_same_toast), Toast.LENGTH_SHORT).show();
                return;
            }
            if (FileUtils.saveInfoByContext(this, psw)) {
                Toast.makeText(this, getString(R.string.login_success_toast), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

}
