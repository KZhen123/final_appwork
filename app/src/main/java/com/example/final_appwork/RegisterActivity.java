package com.example.final_appwork;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private final static String TAG = "RegisterActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final DBManager dbManager = new DBManager(RegisterActivity.this);
        Button btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击注册按钮，检测两次密码是否相同，检测账号是否已存在，插入信息，跳转至ShowActivity1
                EditText et_userID = findViewById(R.id.et_register_id);
                EditText et_name = findViewById(R.id.et_register_name);
                EditText et_pwd1 = findViewById(R.id.et_register_pwd1);
                EditText et_pwd2 = findViewById(R.id.et_register_pwd2);
                String userID = et_userID.getText().toString();
                String name = et_name.getText().toString();
                String pwd1 = et_pwd1.getText().toString();
                String pwd2 = et_pwd2.getText().toString();
                if (dbManager.findById(userID)) {
                    //弹窗，该账号已存在，请登录
                    Toast.makeText(RegisterActivity.this, "该用户已注册!", Toast.LENGTH_SHORT).show();
                    /*AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
                    dialog.setTitle("温馨提示").setMessage("该账户已存在，请登录！");
                    //点击确定就什么都不做，关闭对话框
                    dialog.setNegativeButton("确定", null);
                    dialog.show();*/
                } else if (!pwd1.equals(pwd2)) {
                    //弹窗，两次密码不相等
                    Toast.makeText(RegisterActivity.this, "两次密码输入不相等！", Toast.LENGTH_SHORT).show();
                } else {
                    //插入用户信息，跳转至主页
                    Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                    UserItem userItem = new UserItem(userID, name, pwd1);
                    dbManager.add_users(userItem);
                    //传递用户信息
                    Intent config = new Intent(RegisterActivity.this, ShowActivity1.class);//当前session
                    config.putExtra("userID",userID);
                    startActivityForResult(config, 1);//打开新窗口

                }
            }
        });
        Button btn_return=findViewById(R.id.btn_return);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击返回按钮，返回主页面
                finish();
            }
        });
    }

}