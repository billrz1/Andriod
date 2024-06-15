package com.oyoung.diary.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.oyoung.diary.R;

public class InputDialog extends Dialog {
    private String title;
    private String content;
    private EditText editTitle;
    private EditText editContent;
    private View.OnClickListener onCommitClickListener;

    public InputDialog(@NonNull Context context, String title, String content) {
        super(context);
        this.title = title;
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_input);
        setCanceledOnTouchOutside(true);
        editTitle = findViewById(R.id.edit_title_input);
        editTitle.setText(title);
        editContent = findViewById(R.id.edit_content_input);
        editContent.setText(content);
        Button btnOk = findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(v -> {
            update();
            onCommitClickListener.onClick(v);
            dismiss();
        });
        Button btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> dismiss());
    }

    private void update() {
        String title = editTitle.getText().toString().trim();
        if (!this.title.equals(title)) {
            this.title = title;
        }
        String content = editContent.getText().toString().trim();
        if (!this.content.equals(content)) {
            this.content = content;
        }
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public Dialog setOnCommitClickListener(View.OnClickListener onCommitClickListener) {
        this.onCommitClickListener = onCommitClickListener;
        return this;
    }
}
