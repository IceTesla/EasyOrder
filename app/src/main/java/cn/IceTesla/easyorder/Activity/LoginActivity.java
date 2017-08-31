package cn.IceTesla.easyorder.Activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.IceTesla.easyorder.R;

/**
 * Created by IceTesla on 2017/8/29.
 */

public class LoginActivity extends AppCompatActivity {
    private static final int Status_Wait = 900;
    private static final int Status_Ready = 901;
    private static final int Status_Login = 902;

    private ImageView icon_login;
    private TextView text_login_ID;
    private TextView text_login_Password;
    private Button btn_login;


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Status_Ready:
                    btn_login.setBackground(getDrawable(R.drawable.btn_login_ready));
                    btn_login.setClickable(true);
                    break;
                case Status_Wait:
                    btn_login.setBackground(getDrawable(R.drawable.btn_login_normal));
                    btn_login.setClickable(false);
                    break;
                //登陆网络请求处理
                case Status_Login:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        icon_login=(ImageView) findViewById(R.id.icon_login);
        text_login_ID= (TextView) findViewById(R.id.text_login_ID);
        text_login_Password= (TextView) findViewById(R.id.text_login_Password);
        btn_login= (Button) findViewById(R.id.btn_login);

        text_login_ID.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {
                Message msg=mHandler.obtainMessage();
                if(text_login_ID.getText().length()==0){
                    msg.what= Status_Wait;
                    mHandler.sendMessage(msg);
                } else {
                    if(text_login_Password.getText().length()==0){}else{
                        msg.what= Status_Ready;
                        mHandler.sendMessage(msg);
                    }
                }
            }
        });
        text_login_Password.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {
                Message msg=mHandler.obtainMessage();
                if(text_login_Password.getText().length()==0){
                    msg.what= Status_Wait;
                    mHandler.sendMessage(msg);
                } else {
                    if(text_login_ID.getText().length()==0){}else{
                        msg.what= Status_Ready;
                        mHandler.sendMessage(msg);
                    }
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg=mHandler.obtainMessage();
                msg.what= Status_Login;
                mHandler.sendMessage(msg);
            }
        });
    }
}
