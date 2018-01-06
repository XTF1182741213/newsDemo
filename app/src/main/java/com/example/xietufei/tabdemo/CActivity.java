package com.example.xietufei.tabdemo;


import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;



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

public class CActivity extends AppCompatActivity {

	private Button button;
	private Button button2;
	private int sum=0;
	private String getstr="";
	private List<News> list=new ArrayList<>();
	Handler handler=new Handler();

	Runnable runnable=new Runnable() {
		@Override
		public void run() {
			button.setText(sum+"秒");
			sum++;
			handler.postDelayed(runnable,1000);
		}
	};

	Runnable runnable1=new Runnable() {
		@Override
		public void run() {
			button2.setText("get message is "+getstr);
		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c_layout);
		button=(Button)findViewById(R.id.button);
		button2= (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				handler.post(runnable);
			}
		});
		new Myat().execute();

	}

	class Myat extends AsyncTask{

		@Override
		protected Object doInBackground(Object[] objects) {

			try {
				//请求

				URL url=new URL("http://192.168.2.43:5678/getallnews");
				HttpURLConnection connection= (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setRequestProperty("username","tom");
				connection.connect();

				//接收服务器response
				InputStream dis=connection.getInputStream();
				//String getstr="";
				int ch=dis.read();
				Log.e("tag1:","beginng...");
				// System.out.print("sdfkljsdklf :"+getstr);
				while(ch!=-1){
					getstr+=(char)ch;
					ch=dis.read();
					// System.out.print(getstr);
				}
				// System.out.print(getstr);
				// Log.e("tag1:",getstr);
				//close
				dis.close();
				connection.disconnect();


				Log.v("begin",getstr);
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

				handler.post(runnable1);

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
