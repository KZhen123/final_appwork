package com.example.final_appwork;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;

import javax.security.auth.login.LoginException;

public class ThirdFragment extends Fragment {

    ListView listView;
    private final String TAG = "ThirdFragment";
    MyPageAdapter myPageAdapter;
    MyAdapter2 myAdapter2;
    DBManager dbManager;
    View root;
    private int clickPosition = -1;

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
        Log.i(TAG, "xianshi");
        //获取当前memo3表(待办表)的内容
        List<TodoItem> listItems = dbManager.list_todo();
        //listview展示
        myAdapter2 = new MyAdapter2(getActivity(),
                R.layout.listconfig2,//listitem的行布局实现
                listItems);//数据源
        listView.setAdapter(myAdapter2);

        //final RadioButton rb=itemview.findViewById(R.id.radio_btn);
        //final boolean[] checked = {true};
/*
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for(int i=0;i<rg.getChildCount();i++){
                    if(i==checkedId){
                        rb=rg.getChildAt(i)
                    }
                }
            }
        });*/
        /*rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("怎么回事");
                builder.create().show();

            }
        });*/

        //listview的点击监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object itemAtPosition = listView.getItemAtPosition(position);

//                Log.i(TAG, "onItemClick: "+itemAtPosition);
//                Log.i(TAG, "onItemClick: "+position);
                TodoItem map = (TodoItem) itemAtPosition;

                RadioButton rb = view.findViewById(R.id.radio_btn);
                TextView body = view.findViewById(R.id.list_todo_body);
                //显示
                String state = map.getState();
                Log.i(TAG, "state=" + state + "body=" + map.getBody());
                if (state.equals("0")) {//原来未被选中，被选中，设置成灰色
                    rb.setChecked(true);
                    Log.i(TAG,"原来未被选中，被选中，设置成灰色");
                    rb.setBackgroundColor(Color.rgb(220, 220, 220));
                    body.setBackgroundColor(Color.rgb(220, 220, 220));
                } else {//原来被选中，未选中，白色
                    rb.setChecked(false);
                    Log.i(TAG,"原来被选中，设置成白色");
                    rb.setBackgroundColor(Color.rgb(255,255,255));
                    body.setBackgroundColor(Color.rgb(255, 255, 255));
                }
                dbManager.update_todo(map);
                myAdapter2.notifyDataSetChanged();
            }
        });

        //listview的长按监听
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                Object itemAtPosition = listView.getItemAtPosition(position);
                // HashMap<String,String> map = (HashMap<String, String>) itemAtPosition;
                TodoItem map = (TodoItem) itemAtPosition;
                final int id1 = map.getId();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示")
                        .setMessage("请确认是否删除当前数据")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i(TAG, "onClick: 对话框事件处理");
                                //删除数据库中该数据项
                                myAdapter2.remove(listView.getItemAtPosition(position));
                                dbManager.delete(3, id1);
                            }
                        }).setNegativeButton("否", null);
                builder.create().show();
                //不触发短按
                return true;
            }
        });
    }

}