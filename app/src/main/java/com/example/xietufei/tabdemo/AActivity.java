package com.example.xietufei.tabdemo;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AActivity extends Activity{

	private ListView lv;
	private MyBaseAdapter myBaseAdapter;
	private String getstr;
	private Context context;
	private List<News> list=new ArrayList<>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_layout);
		lv= (ListView) findViewById(R.id.lv);
		new Myat().execute();
		context=this;
		//lv.setAdapter(myBaseAdapter);
	}
	Handler handler=new Handler();

	Runnable runnable=new Runnable() {
		@Override
		public void run() {
			MyBaseAdapter myBaseAdapter =new MyBaseAdapter(context,list);
			lv.setAdapter(myBaseAdapter);
		}
	};
	class Myat extends AsyncTask {

		@Override
		protected Object doInBackground(Object[] objects) {

			try {
				//请求

				URL url=new URL("http://www.tengfeistudio.cn:5678/getallnews");
				HttpURLConnection connection= (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setRequestProperty("username","tom");
				connection.connect();

				//接收服务器response
				InputStream dis=connection.getInputStream();
				//String getstr="";
				//int ch=dis.read();
				//Log.e("tag1:","beginng...");
				// System.out.print("sdfkljsdklf :"+getstr);
				Scanner scan=new Scanner(dis);
				while(scan.hasNext()){
					getstr=scan.next();
				}
				// System.out.print(getstr);
				// Log.e("tag1:",getstr);
				//close
				dis.close();
				connection.disconnect();


				Log.d("begin",getstr);
				JSONArray array=new JSONArray(getstr);
				for (int i=0;i<array.length();i++){
					JSONObject object=array.getJSONObject(i);
					News news=new News();
					news.setTitle(object.getString("title"));
					news.setContent(object.getString("content"));
					news.getId(object.getInt("id"));
					//news= (News) array.get(i);
					list.add(news);
				}
                Log.d("after",list.get(0).getContent());
                handler.post(runnable);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
