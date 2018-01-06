package com.example.xietufei.tabdemo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity{

    private EditText etxtUserName;
    private EditText etxtPassword;
    private Button btnLogin;
    private Button btnRegister;

    //这里要改成所处局域网的ip，也就是你现在手机和电脑同时的ip，在命令行中用ipconfig查看
    private static final String ipAdress="123.207.239.81";
    //这是个test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        etxtUserName=(EditText) findViewById(R.id.accountEdit);
        etxtPassword=(EditText) findViewById(R.id.passwordEdit);

        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnRegister=(Button) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new MyButtonOnClickListener());
        btnRegister.setOnClickListener(new MyButtonOnClickListener());

    }


    /////////////////////////////////////////////////////////////////////////////////////
    class MyButtonOnClickListener implements OnClickListener {

        public void onClick(View arg0) {
            Intent intent = new Intent();
            switch (arg0.getId()) {
                case R.id.btnLogin:
                    try{
                        //开了个后门，可以用这个账号直接登录
                        if (etxtUserName.getText().toString().equals("xietufei")
                                && etxtPassword.getText().toString()
                                .equals("xietufei")) {
                            intent.setClass(LoginActivity.this,
                                    MainTabActivity.class);
                            LoginActivity.this.startActivity(intent);
                            finish();
                        }
                        else{
                            /**
                             * 从android 4.0以后就不允许这样操作了，因为如果在主线程里联网，
                             * 如果网络不好那么整个程序就卡死在那里了。
                             * 所以android4.0以后对要联网的代码必须在新开一个线程来做。
                             */
                            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                                    .detectDiskReads().detectDiskWrites()
                                    .detectNetwork().penaltyLog().build());
                            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                                    .detectLeakedSqlLiteObjects().penaltyLog()
                                    .penaltyDeath().build());
                            Socket s1=new Socket(ipAdress,6666);
                            OutputStream os=s1.getOutputStream();
                            DataOutputStream dos=new DataOutputStream(os);
                            dos.writeUTF(etxtUserName.getText().toString() + " "
                                    + etxtPassword.getText().toString()+" "+"Login");// 向服务器传送登录账号和密码
                            //等一秒钟
                            new Handler().postDelayed(new Runnable(){
                                public void run() {
                                    //execute the task
                                }
                            }, 1000);
                            //
                            InputStream is=s1.getInputStream();
                            DataInputStream dis=new DataInputStream(is);
                            String getStr=dis.readUTF();//YES或者NO
                            if(getStr.equals("YES")){
                                intent.setClass(LoginActivity.this,MainTabActivity.class);
                                LoginActivity.this.startActivity(intent);
                                finish();
                            }
                            else if(getStr.equals("NO")){
                                Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
                            }
                            dis.close();
                            dos.close();
                            s1.close();
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    break;
                case R.id.btnRegister:
                    try{
                        /**
                         * 从android 4.0以后就不允许这样操作了，因为如果在主线程里联网，
                         * 如果网络不好那么整个程序就卡死在那里了。
                         * 所以android4.0以后对要联网的代码必须在新开一个线程来做。
                         */
                        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                                .detectDiskReads().detectDiskWrites()
                                .detectNetwork().penaltyLog().build());
                        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                                .detectLeakedSqlLiteObjects().penaltyLog()
                                .penaltyDeath().build());
                        Socket s1=new Socket(ipAdress,6666);
                        OutputStream os=s1.getOutputStream();
                        DataOutputStream dos=new DataOutputStream(os);
                        dos.writeUTF("# # Register");//向服务器传送# # Register字符
                        dos.close();
                        s1.close();
                        //如果没有异常则跳转到注册界面

                    }catch(Exception e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "注册失败 IOException"+e.toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                    intent.setClass(LoginActivity.this, RegisterActivity.class);
                    LoginActivity.this.startActivity(intent);
                    finish();
                    break;
            }

        }
    }
}
