package cn.IceTesla.easyorder.Activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.IceTesla.easyorder.R;

import static cn.IceTesla.easyorder.Activity.LoginActivity.cacheStore;

public class TableActivity extends AppCompatActivity {

    private TextView back, title, edit;
    private LinearLayout table_change,table_search,table_merge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        back = (TextView) findViewById(R.id.balance_toolbar).findViewById(R.id.toolbar_back);
        back.setText("＜返回");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        title = (TextView) findViewById(R.id.balance_toolbar).findViewById(R.id.toolbar_title);
        title.setText("桌位管理");

        edit = (TextView) findViewById(R.id.balance_toolbar).findViewById(R.id.toolbar_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        table_change = (LinearLayout) findViewById(R.id.btn_table_change);
        table_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        table_search = (LinearLayout) findViewById(R.id.btn_table_search);
        table_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        table_merge = (LinearLayout) findViewById(R.id.btn_table_merge);
        table_merge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
