package com.example.xietufei.tabdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BActivity extends Activity{

	private List<News> list=new ArrayList<>();

	//private String path = "http://www.tengfei.cn";
	private String path="http://192.168.2.43:5678/getallnews";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.b_layout);

		TextView textView = (TextView)this.findViewById(R.id.textView);
		try {
			String htmlContent = HtmlService.getHtml(path);
			textView.setText(htmlContent);


//			JSONArray array=new JSONArray(st);
//			for(int j=0;j<array.length();j++)
//			{
//				JSONObject object=array.getJSONObject(j);
//				News news=new News();
//				news.setTitle(object.getString("title"));
//				news.setContent(object.getString("content"));
//				news.getId(object.getInt("id"));
//				news=(News)array.get(j);
//				list.add(news);
//			}

		} catch (Exception e) {
			textView.setText("程序出现异常："+e.toString());
		}
	}
	
}
