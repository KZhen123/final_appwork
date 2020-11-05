package com.example.final_appwork;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;

public class MyAdapter2 extends ArrayAdapter {
    private static final String TAG = "MyAdapter";

    List object;

    public MyAdapter2(Context context, int resource, List objects) {
        super(context, resource, objects);
        object=objects;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemview = convertView;
        ViewHolder holder;
        if (itemview == null) {
            itemview = LayoutInflater.from(getContext()).inflate(R.layout.listconfig2, parent, false);
        }
        //获取传来的TodoItem
        final TodoItem map = (TodoItem) getItem(position);

        final TextView body = itemview.findViewById(R.id.list_todo_body);
       // RadioGroup rg=itemview.findViewById(R.id.radio_group);
        final RadioButton rb=itemview.findViewById(R.id.radio_btn);
        //显示
        String state=map.getState();
        Log.i(TAG,"state"+state);
        if(state.equals("1")){//被选中，设置成灰色
           // body.setTextColor(Color.rgb(0,0,0));
           // rb.get
            rb.setChecked(true);
            rb.setBackgroundColor(Color.rgb(220,220,220));
            body.setBackgroundColor(Color.rgb(220,220,220));
        }else{//未选中，白色
            //body.setTextColor(Color.rgb(105,105,105));
            rb.setChecked(false);
            rb.setBackgroundColor(Color.rgb(255,255,255));
            body.setBackgroundColor(Color.rgb(255,255,255));
        }
        body.setText(map.getBody());
        return itemview;
    }


}