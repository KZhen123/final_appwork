package com.example.final_appwork;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*学习板块
2020.10.28-
点击备忘录条目可编辑/查看详细信息
长按可删除
 */
public class ShowActivity1 extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    ListView listView;
    private final String TAG = "ShowActivity";
    private String userID = "";
    MyAdapter myAdapter;
    private Intent config;
    DBManager dbManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show1);
        dbManager = new DBManager(ShowActivity1.this);
        listView = findViewById(R.id.list_show1);
        //设置监听
        listView.setOnItemClickListener(ShowActivity1.this);
        listView.setOnItemLongClickListener(ShowActivity1.this);
        //获取userID，根据userID选择展示的信息
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        //获取当前study表的内容
        List<MemoItem> listItems = dbManager.list_study(userID);
        //listview展示
        myAdapter = new MyAdapter(ShowActivity1.this,
                R.layout.listconfig,//listitem的行布局实现
                listItems);//数据源
        listView.setAdapter(myAdapter);

        //点击新建按钮，跳转至新建页面
        Button btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent config = new Intent(ShowActivity1.this, AddActivity.class);//当前session
                config.putExtra("category", "study");
                config.putExtra("userID", userID);
                startActivityForResult(config, 1);//打开新窗口
            }
        });
    }

    //点击备忘录条目，传递id至EditActivity
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object itemAtPosition = listView.getItemAtPosition(position);
        // HashMap<String,String> map = (HashMap<String, String>) itemAtPosition;
        MemoItem map = (MemoItem) itemAtPosition;
        final int id1 = map.getId();
        Log.i(TAG, "onItemClick: item=" + id1);
        //跳转页面，传递点击的备忘录项的id*/
        config = new Intent(this, EditActivity.class);//当前session
        config.putExtra("category", 1);
        config.putExtra("ID", id1);
        //请求可返回窗口
        startActivityForResult(config, 1);//打开新窗口
    }

    //长按删除
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {
        Object itemAtPosition = listView.getItemAtPosition(position);
        // HashMap<String,String> map = (HashMap<String, String>) itemAtPosition;
        MemoItem map = (MemoItem) itemAtPosition;
        final int id1 = map.getId();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("请确认是否删除当前数据")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "onClick: 对话框事件处理");
                        //删除数据库中该数据项
                        dbManager.delete(id1);
                        /*//更新适配器
                        listItemAdapter.notifyDataSetChanged();*/
                    }
                }).setNegativeButton("否", null);
        builder.create().show();
        //不触发短按
        return true;
    }
}
