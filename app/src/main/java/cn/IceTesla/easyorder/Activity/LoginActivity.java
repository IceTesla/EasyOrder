package cn.IceTesla.easyorder.Activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Network;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import cn.IceTesla.easyorder.Network.Operation;
import cn.IceTesla.easyorder.R;

import static cn.IceTesla.easyorder.Network.Opcode.loginFailed;
import static cn.IceTesla.easyorder.Network.Opcode.loginIn;
import static cn.IceTesla.easyorder.Network.Opcode.loginSucceed;


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
    public static String IP = "localhost";
    public static String ID = "Administrator";

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
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
                    text_login_ID.setText("");
                    text_login_Password.setText("");
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(intent);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String temp = cacheGet("IP", getApplicationContext());
        if(temp != "")
            IP = temp;

        icon_login = (ImageView) findViewById(R.id.icon_login);
        text_login_ID = (TextView) findViewById(R.id.text_login_ID);
        text_login_Password = (TextView) findViewById(R.id.text_login_Password);
        btn_login = (Button) findViewById(R.id.btn_login);

        icon_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });

        text_login_ID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Message msg = mHandler.obtainMessage();
                if (text_login_ID.getText().length() == 0) {
                    msg.what = Status_Wait;
                    mHandler.sendMessage(msg);
                } else {
                    if (text_login_Password.getText().length() == 0) {
                    } else {
                        msg.what = Status_Ready;
                        mHandler.sendMessage(msg);
                    }
                }
            }
        });
        text_login_Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Message msg = mHandler.obtainMessage();
                if (text_login_Password.getText().length() == 0) {
                    msg.what = Status_Wait;
                    mHandler.sendMessage(msg);
                } else {
                    if (text_login_ID.getText().length() == 0) {
                    } else {
                        msg.what = Status_Ready;
                        mHandler.sendMessage(msg);
                    }
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(networkTask).start();
            }
        });
        btn_login.setClickable(false);


    }

    private void showInputDialog() {
        final EditText editText = new EditText(LoginActivity.this);
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(LoginActivity.this);
        editText.setHint(IP);
        editText.setHintTextColor(getResources().getColor(R.color.black));
        inputDialog.setTitle("请输入IP地址").setView(editText);
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        IP = editText.getText().toString();
                        cacheStore("IP", IP, getApplicationContext());
                    }
                }).show();
    }

    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // TODO
            // 在这里进行 http request.网络请求相关操作
            Operation login = new Operation(getApplicationContext());
            String code = null;
            try {
                String result = login.loginIn(text_login_ID.getText().toString(), text_login_Password.getText().toString());
                JSONObject json = new JSONObject(result);
                code = json.getString("code");
                if (code.equals(loginSucceed)) {
                    Message msg = mHandler.obtainMessage();
                    msg.what = Status_Login;
                    mHandler.sendMessage(msg);
                }
                else if(code.equals(loginFailed))
                    Toast.makeText(getApplicationContext(),"登陆失败",Toast.LENGTH_LONG);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(code == null)
                Toast.makeText(getApplicationContext(),"未知错误",Toast.LENGTH_LONG);
        }
    };

    public static void cacheStore(String key, String vaule, Context context) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("EasyOrder",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(key, vaule);
        editor.commit();
    }

    public static String cacheGet(String key, Context context) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("EasyOrder",
                Activity.MODE_PRIVATE);
        return mySharedPreferences.getString(key, "");
    }
}
