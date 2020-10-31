package com.example.final_appwork;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity extends AppCompatActivity {
    private final String TAG="EditActivity";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        final DBManager dbManager = new DBManager(EditActivity.this);

        Intent intent = getIntent();
        final int cate = intent.getIntExtra("category", 0);
        final int id=intent.getIntExtra("ID",0);
        Log.i(TAG, "itemget=" + id);

        final EditText et_title = findViewById(R.id.et_edit_title);
        final EditText et_body = findViewById(R.id.et_edit_body);

        //获取当前时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date curDate = new Date(System.currentTimeMillis());
        final String time = formatter.format(curDate);

        final MemoItem memoItem=dbManager.findById(cate,id);

        et_title.setText(memoItem.getTitle());
        et_body.setText(memoItem.getBody());

        //点击保存按钮，更新数据库信息
        Button bt_save = findViewById(R.id.btn_edit_update);
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"update");
                final String title=et_title.getText().toString();
                EditText et_body = findViewById(R.id.et_edit_body);
                final String body=et_body.getText().toString();
                memoItem.setTitle(title);
                memoItem.setBody(body);
                memoItem.setTime(time);
                dbManager.update_memo(cate, memoItem);
            }
        });


    }
}
