package cn.IceTesla.easyorder.Activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.IceTesla.easyorder.Adapter.ShopcartAdapter;
import cn.IceTesla.easyorder.Data.Model.ShopcartModel;
import cn.IceTesla.easyorder.Network.NetRequest;
import cn.IceTesla.easyorder.R;
import cn.IceTesla.easyorder.View.RefreshView;

/**
 * Created by IceTesla on 2017/8/30.
 */

public class ShopcartActivity extends AppCompatActivity {
    private RefreshView refreshView;
    private ListView listView;
    private TextView back,title,edit;
    private ImageView reduce,plus;
    private List<ShopcartModel> mList = new ArrayList<>();
    private NetRequest requestFragment;
    private ShopcartAdapter adapter;
    private static final int Status_Refresh = 903;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
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

        back= (TextView) findViewById(R.id.toolbar_back);
        back.setText("＜返回");
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });

        title=(TextView)findViewById(R.id.toolbar_title);
        title.setText("订单");

        refreshView = (RefreshView) findViewById(R.id.shopcart_swiperefresh_layout);
        listView = (ListView)findViewById(R.id.shopcart_list);

        adapter = new ShopcartAdapter(this,mList);
        listView.setAdapter(adapter);

        reduce = (ImageView) findViewById(R.id.btn_item_shopcart_number_reduce);

        plus = (ImageView) findViewById(R.id.btn_item_shopcart_number_plus);
        //requestFragment = new NetRequest(this,this);

        mList = new ArrayList<>();
        for(int i =0;i<5;i++) {
            ShopcartModel companion = new ShopcartModel();
            companion.setGood(20);
            companion.setName("北京烤鸭"+mList.size());
            companion.setNumber(3);
            companion.setPrice(40);
            companion.setSale(12);
            mList.add(companion);
        }

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

    private void doRefresh(){
//        Map<String, Object> map = new HashMap<>();
//        map.put("type",Status_Refresh);
//        requestFragment.httpRequest(map, CommonUrl.companionList);

        ShopcartModel companion = new ShopcartModel();
        for(int i =0;i<5;i++) {
            companion.setName("北京烤鸭" + mList.size());
            companion.setSale(12);
            companion.setGood(20);
            companion.setNumber(3);
            companion.setPrice(40);

            mList.add(companion);
        }
        Message msg=mHandler.obtainMessage();
        msg.what= Status_Refresh;
        mHandler.sendMessage(msg);

    }
}
