package com.example.xietufei.tabdemo;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity{

    private EditText etxtUserName;
    private EditText etxtPassword;
    private Button btnRegister;
    private Button backButton;

    private static final String ipAdress="123.207.239.81";

    //////////////////
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        etxtUserName=(EditText) findViewById(R.id.registerAccount);
        etxtPassword=(EditText) findViewById(R.id.registerPassword);

        btnRegister=(Button) findViewById(R.id.RegisterConfirmBtn);
        backButton=(Button)findViewById(R.id.back_btn);

        btnRegister.setOnClickListener(new MyButtonOnClickListener());



        //注册返回登录
        backButton.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {
                /*此处用Intent来实现Activity与Activity之间的跳转*/
                Intent intent=new Intent();
                intent.setClass(RegisterActivity.this,LoginActivity.class);
                //Intent intent=new Intent(IntentTest.this,MyActivity.class);
                startActivity(intent);
            }
        });
    }

    /////////////////////////////////////////////////////////////////////////////////////
    class MyButtonOnClickListener implements OnClickListener {

        public void onClick(View arg0) {

            Intent intent = new Intent();
            switch (arg0.getId()) {
                case R.id.RegisterConfirmBtn:
                    try{
                        Socket s1=new Socket(ipAdress,6666);
                        OutputStream os=s1.getOutputStream();
                        DataOutputStream dos=new DataOutputStream(os);
                        //传给服务器账号和密码
                        dos.writeUTF(etxtUserName.getText().toString() + " "
                                + etxtPassword.getText().toString()+" "+"Registered");
                        dos.close();
                        s1.close();
                    }catch(Exception e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "注册失败",
                                Toast.LENGTH_SHORT).show();
                    }
                    intent.setClass(RegisterActivity.this, LoginActivity.class);
                    break;


            }
            RegisterActivity.this.startActivity(intent);
            finish();
        }
    }
}
