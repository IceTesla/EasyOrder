package cn.IceTesla.easyorder.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.IceTesla.easyorder.R;

public class BalanceActivity extends AppCompatActivity {

    private TextView back, title, edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

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
    }

}
