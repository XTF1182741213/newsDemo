package com.example.xietufei.tabdemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MyBaseAdapter extends BaseAdapter {

    Context context;
    List<News> list=new ArrayList<>();

    public MyBaseAdapter(Context context, List<News> list) {
        this.context = context;
        this.list = list;
    }

    public MyBaseAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(context).inflate(R.layout.item_layout,null);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        Intent intent=new Intent();
                        intent.setClass(context,SingleActivity.class);
                        intent.putExtra("title","title"+list.get(i).getTitle());
                        intent.putExtra("content","content"+list.get(i).getContent());
                        context.startActivity(intent);

//                Intent intent=new Intent();
//                intent.setClass(context,SingleActivity.class);
//                intent.putExtra("title","title"+i);
//                intent.putExtra("content","content"+i);
//                context.startActivity(intent);
            }
        });
        TextView textView=(TextView)view.findViewById(R.id.textView);
        TextView textView2=(TextView)view.findViewById(R.id.textView2);
        textView.setText("No"+i);
        textView2.setText("title "+list.get(i).getTitle());
        return view;
    }
}
