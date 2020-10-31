package com.example.final_appwork;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final DBManager dbManager=new DBManager(LoginActivity.this);
        Button btn_login=findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            //点击登录按钮，判断id密码是否与数据库匹配，匹配则登录成功进入ShowActivity1，不成功则弹窗并清空内容
            @Override
            public void onClick(View v) {
                Log.i("Login","login");
                EditText et_userID=findViewById(R.id.et_login_id);
                EditText et_pwd=findViewById(R.id.et_login_pwd);
                String userID=et_userID.getText().toString();
                String pwd=et_pwd.getText().toString();
                if(userID.equals("")||pwd.equals("")){
                    Toast.makeText(LoginActivity.this, "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
                }
                else if(dbManager.isMatch(userID,pwd)){
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    //传递用户id
                    Intent config = new Intent(LoginActivity.this, ShowActivity1.class);//当前session
                    config.putExtra("userID",userID);
                    startActivityForResult(config, 1);//打开新窗口
                }
                else{
                    Toast.makeText(LoginActivity.this, "账号或密码错误！", Toast.LENGTH_SHORT).show();
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
