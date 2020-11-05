package com.example.final_appwork;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class SecondFragment extends Fragment {
    ListView listView;
    private final String TAG = "SecondFragment";
    MyAdapter myAdapter;
    private Intent config;
    DBManager dbManager;
    View root;///????????????这是啥子

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.activity_show2, container, false);
        return root;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dbManager = new DBManager(getActivity());
        listView = root.findViewById(R.id.list_show2);

        //获取当前life表的内容
        List<MemoItem> listItems = dbManager.list_memo(2);
        //listview展示
        myAdapter = new MyAdapter(getActivity(),
                R.layout.listconfig,//listitem的行布局实现
                listItems);//数据源
        listView.setAdapter(myAdapter);

        //listview的点击监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object itemAtPosition = listView.getItemAtPosition(position);
                // HashMap<String,String> map = (HashMap<String, String>) itemAtPosition;
                MemoItem map = (MemoItem) itemAtPosition;
                final int id1 = map.getId();
                Log.i(TAG, "onItemClick: item=" + id1);
                //跳转页面，传递点击的备忘录项的id
                config = new Intent(getActivity(), EditActivity.class);//
                config.putExtra("cate", 2);
                config.putExtra("ID", id1);
                //请求可返回窗口
                startActivityForResult(config, 1);//打开新窗口
            }
        });
        //listview的长按监听
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                Object itemAtPosition = listView.getItemAtPosition(position);
                // HashMap<String,String> map = (HashMap<String, String>) itemAtPosition;
                MemoItem map = (MemoItem) itemAtPosition;
                final int id1 = map.getId();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示")
                        .setMessage("请确认是否删除当前数据")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i(TAG, "onClick: 对话框事件处理");
                                //删除数据库中该数据项
                                myAdapter.remove(listView.getItemAtPosition(position));
                                dbManager.delete(2, id1);
                            }
                        }).setNegativeButton("否", null);
                builder.create().show();
                //不触发短按
                return true;
            }
        });
/*
        //点击新建按钮，跳转至新建页面
        Button btn_add = root.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent config = new Intent(getActivity(), AddActivity.class);//getActivity是什么意思呢
                config.putExtra("category", "study");
                config.putExtra("userID", userID);
                startActivityForResult(config, 1);//打开新窗口
            }
        });*/

    }
}