package com.example.zhaokl.chapter02exe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0; // 数字按钮
    Button jia, jian, cheng, chu, dian, sum, clear;// +号
    EditText edit; // 显示文本


    private String ss = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 获取页面上的控件
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn0 = (Button) findViewById(R.id.btn0);
        jia = (Button) findViewById(R.id.jia);
        jian = (Button) findViewById(R.id.jian);
        cheng = (Button) findViewById(R.id.cheng);
        chu = (Button) findViewById(R.id.chu);
        sum = (Button) findViewById(R.id.sum);
        dian = (Button) findViewById(R.id.dian);
        clear = (Button) findViewById(R.id.clear);
        edit = (EditText) findViewById(R.id.edit);

        // 按钮的单击事件
        btn1.setOnClickListener(new Click());
        btn2.setOnClickListener((View.OnClickListener) new Click());
        btn3.setOnClickListener(new Click());
        btn4.setOnClickListener(new Click());
        btn5.setOnClickListener(new Click());
        btn6.setOnClickListener(new Click());
        btn7.setOnClickListener(new Click());
        btn8.setOnClickListener(new Click());
        btn9.setOnClickListener(new Click());
        btn0.setOnClickListener(new Click());
        jia.setOnClickListener(new Click());
        jian.setOnClickListener(new Click());
        cheng.setOnClickListener(new Click());
        chu.setOnClickListener(new Click());
        sum.setOnClickListener(new Click());
        dian.setOnClickListener(new Click());
        clear.setOnClickListener(new Click());
        edit.setOnClickListener(new Click());
    }

    // 设置按钮点击后的监听
    class Click implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {                //switch循环获取点击按钮后的值
                case R.id.clear: {
                    ss = "";
                    edit.setText(ss);
                }
                break;

                case R.id.btn0: {
                    ss += "0";
                    edit.setText(ss);
                }
                break;
                case R.id.btn1: {
                    ss += "1";
                    edit.setText(ss);
                }
                break;
                case R.id.btn2: {
                    ss += "2";
                    edit.setText(ss);
                }
                break;

                case R.id.btn3: {
                    ss += "3";
                    edit.setText(ss);
                }
                break;
                case R.id.btn4: {
                    ss += "4";
                    edit.setText(ss);
                }
                break;
                case R.id.btn5: {
                    ss += "5";
                    edit.setText(ss);
                }
                break;

                case R.id.btn6: {
                    ss += "6";
                    edit.setText(ss);
                }
                break;
                case R.id.btn7: {
                    ss += "7";
                    edit.setText(ss);
                }
                break;
                case R.id.btn8: {
                    ss += "8";
                    edit.setText(ss);
                }
                break;
                case R.id.btn9: {
                    ss += "9";
                    edit.setText(ss);
                }
                break;
                case R.id.dian: {
                    if (ss.length() == 0 || ss.indexOf(" ") == ss.length() - 3 || ss.lastIndexOf(".") > ss.indexOf(" ")) {
                        break;
                    } else {
                        ss += ".";
                        edit.setText(ss);
                    }
                }
                break;
                case R.id.jia: {
                    if (ss.length() == 0) {
                        break;
                    }
                    if (ss.contains(" ")) {
                        if (ss.indexOf(" ") == ss.length() - 3 || ss.indexOf(" ") == ss.length() - 2 || ss.indexOf(" ") == ss.length() - 1)
                            break;
                        getResult();
                    }

                    ss += " ＋ ";
                    edit.setText(ss);
                }
                break;
                case R.id.jian: {
                    if (ss.length() == 0) {
                        break;
                    }
                    if (ss.contains(" ")) {
                        if (ss.indexOf(" ") == ss.length() - 3 || ss.indexOf(" ") == ss.length() - 2 || ss.indexOf(" ") == ss.length() - 1)
                            break;
                        getResult();
                    }

                    ss += " － ";
                    edit.setText(ss);
                }
                break;
                case R.id.cheng: {
                    if (ss.length() == 0) {
                        break;
                    }
                    if (ss.contains(" ")) {
                        if (ss.indexOf(" ") == ss.length() - 3 || ss.indexOf(" ") == ss.length() - 2 || ss.indexOf(" ") == ss.length() - 1)
                            break;
                        getResult();
                    }

                    ss += " × ";
                    edit.setText(ss);
                }
                break;
                case R.id.chu: {
                    if (ss.length() == 0) {
                        break;
                    }
                    if (ss.contains(" ")) {
                        if (ss.indexOf(" ") == ss.length() - 3 || ss.indexOf(" ") == ss.length() - 2 || ss.indexOf(" ") == ss.length() - 1)
                            break;
                        getResult();
                    }

                    ss += " / ";
                    edit.setText(ss);
                }
                break;
                case R.id.sum:
                    getResult();
                    break;
            }
        }

        private void getResult() {
            double result = 0;
            if (ss == null || ss.equals("")) return;
            if (!ss.contains(" ")) return;
            String s1 = ss.substring(0, ss.indexOf(" "));
            String op = ss.substring(ss.indexOf(" ") + 1, ss.indexOf(" ") + 2);
            String s2 = ss.substring(ss.indexOf(" ") + 3);
            if (!s1.equals("") && !s2.equals("")) {
                double d1 = Double.parseDouble(s1);
                double d2 = Double.parseDouble(s2);
                switch (op) {
                    case "＋":
                        result = d1 + d2;
                        break;
                    case "－":
                        result = d1 - d2;
                        break;
                    case "×":
                        result = d1 * d2;
                        break;
                    case "÷": {
                        if (d2 == 0) {
                            edit.setText("不能除以零");
                            break;
                        }
                        result = d1 / d2 * 1.0;
                    }
                    break;
                }

                int r = (int) result;
                if (r == result) {
                    edit.setText("" + r);
                    ss = "" + r;
                } else {
                    edit.setText(result + "");
                    ss = "" + result;
                }

            }

        }
    }

}
