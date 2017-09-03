package cn.IceTesla.easyorder.Activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.IceTesla.easyorder.Adapter.ShopcartAdapter;
import cn.IceTesla.easyorder.Data.Model.ShopcartModel;
import cn.IceTesla.easyorder.Network.Operation;
import cn.IceTesla.easyorder.R;
import cn.IceTesla.easyorder.View.RefreshView;

import static cn.IceTesla.easyorder.Network.Opcode.payOrder;

/**
 * Created by IceTesla on 2017/8/30.
 */

public class ShopcartActivity extends AppCompatActivity {
    private RefreshView refreshView;
    private ListView listView;
    private TextView back, title, edit;
    private ImageView reduce, plus;
    private List<ShopcartModel> mList = new ArrayList<>();
    private ShopcartAdapter adapter;
    private static final int Status_Refresh = 903;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Status_Refresh:
                    adapter.refresh(mList);
                    adapter.notifyDataSetChanged();
                    refreshView.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcart);

        back = (TextView) findViewById(R.id.shopcart_toolbar).findViewById(R.id.toolbar_back);
        back.setText("＜返回");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        title = (TextView) findViewById(R.id.shopcart_toolbar).findViewById(R.id.toolbar_title);
        title.setText("订单");

        refreshView = (RefreshView) findViewById(R.id.shopcart_swiperefresh_layout);
        listView = (ListView) findViewById(R.id.shopcart_list);

        adapter = new ShopcartAdapter(this, mList);
        listView.setAdapter(adapter);

        reduce = (ImageView) findViewById(R.id.btn_item_shopcart_number_reduce);

        plus = (ImageView) findViewById(R.id.btn_item_shopcart_number_plus);

        doRefresh();

        refreshView.setProgressBackgroundColorSchemeResource(android.R.color.white);
        refreshView.setColorSchemeResources(R.color.black);

        refreshView.setOnRefreshListener(new RefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshView.setRefreshing(true);
                doRefresh();
            }
        });

        refreshView.setOnLoadListener(new RefreshView.OnLoadListener() {
            @Override
            public void onLoad() {
                refreshView.setRefreshing(true);
                doRefresh();
            }
        });

    }

    private void doRefresh() {
        new Thread(getMenu).start();
    }

    Runnable getMenu = new Runnable() {

        @Override
        public void run() {
            Operation request = new Operation(getApplicationContext());

            try {
                String result = request.getMenu();
                JSONObject json = new JSONObject(result);
                String code = json.getString("code");
                JSONArray array = json.getJSONArray("content");
                if (code.equals("1599")) {
                    mList = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject info = array.getJSONObject(i);
                        Gson gson = new Gson();
                        ShopcartModel companion = gson.fromJson(info.toString(), ShopcartModel.class);
                        mList.add(companion);
                    }
                    Message msg = mHandler.obtainMessage();
                    msg.what = Status_Refresh;
                    mHandler.sendMessage(msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };
}
