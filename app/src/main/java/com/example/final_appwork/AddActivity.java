package com.example.final_appwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
新建
 */
public class AddActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        final DBManager dbManager=new DBManager(AddActivity.this);
        //获取传来的category，判断需要插入哪张表,获取传来的userID，作为插入时的参数
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        final String userID=intent.getStringExtra("userID");
        final int cate;
        if (category.equals("study")) {
            cate = 1;
        } else {
            cate = 2;
        }
        //获取当前时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date curDate = new Date(System.currentTimeMillis());
        final String time = formatter.format(curDate);

        Button btn_save = findViewById(R.id.btn_add_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击保存按钮，插入备忘录
                EditText et_title = findViewById(R.id.et_add_title);
                EditText et_body = findViewById(R.id.et_add_body);
                String title = et_title.getText().toString();
                String body = et_body.getText().toString();
                //点击保存，插入数据库
                if (title == null) {
                    //弹窗，请输入备忘录标题
                    Toast.makeText(AddActivity.this, "请输入标题", Toast.LENGTH_SHORT).show();
                } else if (body == null) {
                    //弹窗，请输入备忘录内容
                    Toast.makeText(AddActivity.this, "请输入内容", Toast.LENGTH_SHORT).show();
                } else {
                    //插入数据库，并返回
                    MemoItem memoItem = new MemoItem(userID,title,time, body);
                    dbManager.add_memo(cate,memoItem);
                    finish();
                }
            }
        });

        Button btn_return = findViewById(R.id.btn_add_return);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击返回，返回上一页面
                finish();
            }
        });

    }
}
