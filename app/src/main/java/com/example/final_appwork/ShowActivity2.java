package com.example.final_appwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/*生活板块
* 2020.10.28-
* */
public class ShowActivity2 extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show2);

        Button btn_add=findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击新建按钮，跳转至新建页面
                Intent config=new Intent(ShowActivity2.this,AddActivity.class);//当前session
                config.putExtra("category","life");
                startActivityForResult(config,1);//打开新窗口
            }
        });
    }
}
