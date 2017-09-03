package cn.IceTesla.easyorder.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.IceTesla.easyorder.Data.Model.ShopcartModel;
import cn.IceTesla.easyorder.Network.Operation;
import cn.IceTesla.easyorder.R;

import static cn.IceTesla.easyorder.Activity.LoginActivity.cacheGet;
import static cn.IceTesla.easyorder.Activity.LoginActivity.cacheStore;
import static cn.IceTesla.easyorder.Network.Opcode.payOrder;

public class BalanceActivity extends AppCompatActivity {

    private TextView back, title, edit;
    private TextView textTableNO,textTotalPrice,btn_balance;
    public String tableNumber = "无";
    public String totalPrice = "0";

    final static int Status_GetBalance = 9999;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Status_GetBalance:
                    textTotalPrice.setText(totalPrice);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        textTableNO = (TextView) findViewById(R.id.text_balance_NO);
        textTotalPrice = (TextView) findViewById(R.id.text_balance_totalprice);
        btn_balance = (TextView) findViewById(R.id.btn_balance_balance);

        back = (TextView) findViewById(R.id.balance_toolbar).findViewById(R.id.toolbar_back);
        back.setText("＜返回");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        title = (TextView) findViewById(R.id.balance_toolbar).findViewById(R.id.toolbar_title);
        title.setText("结账");

        edit = (TextView) findViewById(R.id.balance_toolbar).findViewById(R.id.toolbar_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(BalanceActivity.this);
                AlertDialog.Builder inputDialog =
                        new AlertDialog.Builder(BalanceActivity.this);
                editText.setHint(tableNumber);
                editText.setHintTextColor(getResources().getColor(R.color.black));
                inputDialog.setTitle("请输入桌号查询").setView(editText);
                inputDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tableNumber = editText.getText().toString();
                                cacheStore("tableNumber", tableNumber, getApplicationContext());
                                new Thread(getTotalPrice).start();
                            }
                        }).show();
            }
        });

        textTableNO.setText(tableNumber);
        textTotalPrice.setText(totalPrice);
        btn_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(getTotalPrice).start();
            }
        });

    }

    Runnable sendBalance = new Runnable() {
        @Override
        public void run() {

        }
    };

    Runnable getTotalPrice = new Runnable() {

        @Override
        public void run() {
            Operation getTotalPrice = new Operation(getApplicationContext());

            try {
                String result = getTotalPrice.payMoney("payServlet","1");
                JSONObject json = new JSONObject(result);
                String code = json.getString("code");
                JSONArray array = json.getJSONArray("content");
                if (code.equals("1600")) {

                    Message msg = mHandler.obtainMessage();
                    msg.what = Status_GetBalance;
                    mHandler.sendMessage(msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };

}
