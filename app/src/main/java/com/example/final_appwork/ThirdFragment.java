package com.example.final_appwork;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ThirdFragment extends Fragment {

    ListView listView;
    private final String TAG = "ThirdFragment";
    MyAdapter2 myAdapter2;
    DBManager dbManager;
    View root;
    private int clickPosition=-1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.activity_show3, container, false);
        return root;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dbManager = new DBManager(getActivity());
        listView = root.findViewById(R.id.list_show3);
        Log.i(TAG,"xianshi");
        //获取当前memo3表(待办表)的内容
        List<TodoItem> listItems = dbManager.list_todo();
        //listview展示
        myAdapter2 = new MyAdapter2(getActivity(),
                R.layout.listconfig2,//listitem的行布局实现
                listItems);//数据源
        listView.setAdapter(myAdapter2);

        //listview的点击监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object itemAtPosition = listView.getItemAtPosition(position);
                TodoItem map = (TodoItem) itemAtPosition;
                /*String state=map.getState();
                TextView body=root.findViewById(R.id.list_todo_body);
                androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("怎么回事");
                builder.create().show();
                myAdapter2.notifyDataSetChanged();*/
                final int id1 = map.getId();
                Log.i(TAG, "onItemClick: item=" + id1);
                dbManager.update_todo(map);


            }
        });
    }

}