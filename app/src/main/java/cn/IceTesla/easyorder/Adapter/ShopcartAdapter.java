package cn.IceTesla.easyorder.Adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.IceTesla.easyorder.Activity.ShopcartActivity;
import cn.IceTesla.easyorder.Data.Model.ShopcartModel;
import cn.IceTesla.easyorder.R;

/**
 * Created by IceTesla on 2017/8/30.
 */

public class ShopcartAdapter extends BaseAdapter {
    private List<ShopcartModel> shopcartList;
    private Activity activity;
    private LayoutInflater layoutInflater;
    private ViewHolder viewHolder;

    public ShopcartAdapter(Activity activity, List<ShopcartModel> list) {
        this.shopcartList = list;
        this.activity = activity;
    }

    public void add(List<ShopcartModel> list) {
        this.shopcartList.addAll(list);
    }

    public void refresh(List<ShopcartModel> list) {
        this.shopcartList = list;
    }

    @Override
    public int getCount() {
        return shopcartList.size();
    }

    @Override
    public ShopcartModel getItem(int position) {
        return shopcartList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            layoutInflater = LayoutInflater.from(activity);
            convertView = layoutInflater.inflate(R.layout.item_shopcart_layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        ShopcartModel item = getItem(position);
        viewHolder.setValues(item);

        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView name;
        TextView sale;
        TextView good;
        TextView price;
        TextView number;
        ImageView reduce;
        ImageView plus;
        LinearLayout singleItem;

        PopupWindow mPopupWindow;
        View popupView;
        ImageView image_add;
        TextView name_add;
        TextView sale_add;
        TextView good_add;
        TextView price_add;
        TextView note_add;
        TextView number_add;
        ImageView number_reduce;
        ImageView number_plus;
        TextView add;
        ImageView close;

        int num;


        public ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.image_item_shopcart);
            name = (TextView) view.findViewById(R.id.text_item_shopcart_name);
            sale = (TextView) view.findViewById(R.id.text_item_shopcart_sale);
            good = (TextView) view.findViewById(R.id.text_item_shopcart_good);
            price = (TextView) view.findViewById(R.id.text_item_shopcart_price);
            number = (TextView) view.findViewById(R.id.text_item_shopcart_number);
            reduce = (ImageView) view.findViewById(R.id.btn_item_shopcart_number_reduce);
            plus = (ImageView) view.findViewById(R.id.btn_item_shopcart_number_plus);
            singleItem = (LinearLayout) view.findViewById(R.id.item_shopcart);
        }

        public void setValues(final ShopcartModel item) {
            Resources res = activity.getResources();
            name.setText(item.getName());
            sale.setText(Integer.toString(item.getSale()));
            good.setText(Integer.toString(item.getGood()));
            price.setText(Integer.toString(item.getPrice()));
            number.setText(Integer.toString(item.getNumber()));

            ArrayList<String> picList = item.getImage();

            reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.getNumber() > 0) {
                        item.setNumber(item.getNumber() - 1);
                        number.setText(Integer.toString(item.getNumber()));
                    }
                }
            });

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.setNumber(item.getNumber() + 1);
                    number.setText(Integer.toString(item.getNumber()));
                }
            });

            singleItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initpopupView(v, item);
                    mPopupWindow.showAsDropDown(v);
                    backgroundAlpha(0.5f);
                }
            });
        }

        void initpopupView(View view, final ShopcartModel item) {
            popupView = activity.getLayoutInflater().inflate(R.layout.item_add_shopcart_layout, null);
            mPopupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            mPopupWindow.setFocusable(true);
            mPopupWindow.setTouchable(true);
            mPopupWindow.setAnimationStyle(R.style.Popupwindow);
            int[] location = new int[2];
            view.getLocationOnScreen(location);
            mPopupWindow.showAtLocation(view, Gravity.LEFT | Gravity.BOTTOM, 0, -location[1]);
            mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
            mPopupWindow.setOnDismissListener(new poponDismissListener());

            image_add = (ImageView) popupView.findViewById(R.id.image_add_shopcart);
            name_add = (TextView) popupView.findViewById(R.id.text_add_shopcart_name);
            sale_add = (TextView) popupView.findViewById(R.id.text_add_shopcart_sale);
            good_add = (TextView) popupView.findViewById(R.id.text_add_shopcart_good);
            price_add = (TextView) popupView.findViewById(R.id.text_add_shopcart_price);
            note_add = (TextView) popupView.findViewById(R.id.text_add_shopcart_note);
            number_add = (TextView) popupView.findViewById(R.id.text_add_shopcart_number);
            number_reduce = (ImageView) popupView.findViewById(R.id.btn_add_shopcart_number_reduce);
            number_plus = (ImageView) popupView.findViewById(R.id.btn_add_shopcart_number_plus);
            add = (TextView) popupView.findViewById(R.id.btn_add_shopcart_add);
            close = (ImageView) popupView.findViewById(R.id.btn_add_shopcart_close);

            num = Integer.valueOf(number.getText().toString());
            name_add.setText(name.getText());
            sale_add.setText(sale.getText());
            good_add.setText(good.getText());
            price_add.setText(price.getText());
            number_add.setText(Integer.toString(num));
            note_add.setText(item.getNote());

            number_reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (num > 0) {
                        num--;
                        number_add.setText(Integer.toString(num));
                    }
                }
            });

            number_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num++;
                    number_add.setText(Integer.toString(num));
                }
            });

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.setNumber(num);
                    item.setNote(note_add.getText().toString());
                    number.setText(Integer.toString(num));
                    mPopupWindow.dismiss();
                }
            });

            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopupWindow.dismiss();
                }
            });
        }

        class poponDismissListener implements PopupWindow.OnDismissListener {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        }

        public void backgroundAlpha(float bgAlpha) {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.alpha = bgAlpha;
            activity.getWindow().setAttributes(lp);
        }
    }


}

