package cn.IceTesla.easyorder.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.IceTesla.easyorder.R;

public class MainActivity extends AppCompatActivity {

    private ImageView back;
    private TextView title, edit;
    private LinearLayout menu,table,balance;

    @Override
    public void onBackPressed() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        back = (ImageView) findViewById(R.id.mainActivity_toolbar_icon);
        Bitmap temp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        Bitmap bmp = createCircleImage(temp);
        Drawable drawable =new BitmapDrawable(bmp);

        back.setBackground(drawable);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
            }
        });

        title = (TextView) findViewById(R.id.mainActivity_toolbar_title);
        title.setText("IceTesla");

        edit = (TextView) findViewById(R.id.mainActivity_toolbar_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        menu= (LinearLayout) findViewById(R.id.btn_main_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        table= (LinearLayout) findViewById(R.id.btn_main_table);
        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TableActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        balance= (LinearLayout) findViewById(R.id.btn_main_balance);
        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BalanceActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }

    /**
     * 将图片剪裁为圆形
     */
    public  Bitmap createCircleImage(Bitmap source) {
        int length = source.getWidth() < source.getHeight() ? source.getWidth() : source.getHeight();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(length, length, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        canvas.drawCircle(length / 2, length / 2, length / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }
}
