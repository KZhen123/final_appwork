package com.example.final_appwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button btn_login = findViewById(R.id.btn_login1);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击登录按钮，跳转登录页面
                Intent config = new Intent(FirstActivity.this, LoginActivity.class);//当前session
                startActivityForResult(config, 1);//打开新窗口
            }
        });

        Button btn_register = findViewById(R.id.btn_register1);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击注册按钮，跳转注册页面
                Intent config = new Intent(FirstActivity.this, RegisterActivity.class);//当前session
                startActivityForResult(config, 1);//打开新窗口
            }
        });
    }

}
