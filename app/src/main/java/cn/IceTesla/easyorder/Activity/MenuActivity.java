package cn.IceTesla.easyorder.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.IceTesla.easyorder.R;

public class MenuActivity extends AppCompatActivity {

    private TextView back, title, edit;
    private TextView openTable,menu;

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

        openTable= (TextView) findViewById(R.id.btn_menu_openTable);
        openTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        menu = (TextView) findViewById(R.id.btn_menu_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,ShopcartActivity.class);
                MenuActivity.this.startActivity(intent);
            }
        });
    }
}
