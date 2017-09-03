package cn.IceTesla.easyorder.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.IceTesla.easyorder.Data.Model.ShopcartModel;
import cn.IceTesla.easyorder.Data.Model.TableModel;
import cn.IceTesla.easyorder.Network.Operation;
import cn.IceTesla.easyorder.R;

import static cn.IceTesla.easyorder.Activity.LoginActivity.cacheStore;
import static cn.IceTesla.easyorder.Network.Opcode.checkTableFail;
import static cn.IceTesla.easyorder.Network.Opcode.checkTableSucc;
import static cn.IceTesla.easyorder.Network.Opcode.loginFailed;
import static cn.IceTesla.easyorder.Network.Opcode.loginSucceed;

public class MenuActivity extends AppCompatActivity {

    private TextView back, title, edit;
    private TextView openTable, menu;
    private List<String> mList = new ArrayList();
    final static int Status_EmptyTable = 10000;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Status_EmptyTable:
                    AlertDialog.Builder listDialog =
                            new AlertDialog.Builder(MenuActivity.this);
                    listDialog.setTitle("桌子列表");
                    String[] items = (String[]) mList.toArray(new String[mList.size()]);
                    listDialog.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    listDialog.show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        back = (TextView) findViewById(R.id.balance_toolbar).findViewById(R.id.toolbar_back);
        back.setText("＜返回");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        title = (TextView) findViewById(R.id.balance_toolbar).findViewById(R.id.toolbar_title);
        title.setText("点餐");

        edit = (TextView) findViewById(R.id.balance_toolbar).findViewById(R.id.toolbar_edit);

        openTable = (TextView) findViewById(R.id.btn_menu_openTable);
        openTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(getOpenTable).start();
            }
        });


        menu = (TextView) findViewById(R.id.btn_menu_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ShopcartActivity.class);
                MenuActivity.this.startActivity(intent);
            }
        });
    }

    Runnable getOpenTable = new Runnable() {
        @Override
        public void run() {
            Operation CheckTable = new Operation(getApplicationContext());
            String code = null;
            try {
                String result = CheckTable.checkTable();
                JSONObject json = new JSONObject(result);
                code = json.getString("code");
                JSONArray array = json.getJSONArray("content");
                if (code.equals(checkTableSucc)) {
                    mList = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject info = array.getJSONObject(i);
                        String number = info.getString("num");
                        String flag = info.getString("flag");
                        if (flag.equals("0"))
                            number = "桌号: " + number + "\t空";

                        mList.add(number);
                    }
                    Message msg = mHandler.obtainMessage();
                    msg.what = Status_EmptyTable;
                    mHandler.sendMessage(msg);
                } else if (code.equals(checkTableFail))
                    Toast.makeText(getApplicationContext(), "查桌失败", Toast.LENGTH_LONG);

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (code == null)
                Toast.makeText(getApplicationContext(), "未知错误", Toast.LENGTH_LONG);
        }
    };


}
