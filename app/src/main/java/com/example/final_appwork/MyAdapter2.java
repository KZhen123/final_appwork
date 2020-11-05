package com.example.final_appwork;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;

public class MyAdapter2 extends ArrayAdapter {
    private static final String TAG = "MyAdapter";

    public MyAdapter2(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemview = convertView;
        ViewHolder holder;
        if (itemview == null) {
            itemview = LayoutInflater.from(getContext()).inflate(R.layout.listconfig2, parent, false);
            /*holder = new ViewHolder();
            holder.editText=convertView.findViewById(R.id.et_todo);
            holder.radioButton=convertView.findViewById(R.id.radio_btn);
            convertView.setTag(holder);*/
        }

        //获取传来的memoItem
        TodoItem map = (TodoItem) getItem(position);
        TextView body = itemview.findViewById(R.id.list_todo_body);
        //显示
        String state=map.getState();
        Log.i(TAG,"state"+state);
        if(state.equals("0")){//未被选中，设置成黑色
            body.setTextColor(Color.rgb(0,0,0));
        }else{//被选中，设置为白色
            body.setTextColor(Color.rgb(105,105,105));
        }
        body.setText(map.getBody());
        return itemview;
    }


}