package com.example.final_appwork;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/*
listview行数据的适配器
 */
public class MyAdapter extends ArrayAdapter {
    private static final String TAG = "MyAdapter";

    public MyAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemview = convertView;
        if (itemview == null) {
            itemview = LayoutInflater.from(getContext()).inflate(R.layout.listconfig, parent, false);
        }
        //获取传来的memoItem
        MemoItem map = (MemoItem) getItem(position);
        TextView title = itemview.findViewById(R.id.list_item_title);
        TextView time = itemview.findViewById(R.id.list_item_time);
        TextView body = itemview.findViewById(R.id.list_item_body);
        //显示
        title.setText(map.getTitle());
        time.setText(map.getTime());
        body.setText(map.getBody());
        return itemview;
    }
}

