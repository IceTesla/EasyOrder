package cn.IceTesla.easyorder.Adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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

    public ShopcartAdapter(Activity activity, List<ShopcartModel> list){
        this.shopcartList = list;
        this.activity = activity;
    }

    public void add(List<ShopcartModel> list){
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
        if(convertView ==null){
            layoutInflater = LayoutInflater.from(activity);
            convertView = layoutInflater.inflate(R.layout.item_shopcart_layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else
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
        LinearLayout singleItem;

        public ViewHolder(View view){
            image = (ImageView) view.findViewById(R.id.image_item_shopcart);
            name = (TextView) view.findViewById(R.id.text_item_shopcart_name);
            sale = (TextView) view.findViewById(R.id.text_item_shopcart_sale);
            good = (TextView) view.findViewById(R.id.text_item_shopcart_good);
            price = (TextView) view.findViewById(R.id.text_item_shopcart_price);
            number = (TextView) view.findViewById(R.id.text_item_shopcart_number);
            singleItem = (LinearLayout) view.findViewById(R.id.item_shopcart);
        }

        public void setValues(final ShopcartModel item){
            Resources res=activity.getResources();
            name.setText(item.getName());
            sale.setText(Integer.toString(item.getSale()));
            good.setText(Integer.toString(item.getGood()));
            price.setText(Integer.toString(item.getPrice()));
            number.setText(Integer.toString(item.getNumber()));

            ArrayList<String> picList = item.getImage();

            singleItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            //if (!picList.isEmpty()){

            //}
        }
    }
}
