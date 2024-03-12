package com.example.baseadapterstudy;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import java.util.ArrayList;

public class BoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Product> objects;

    BoxAdapter(Context context, ArrayList<Product> products){
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater)
                ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    //the amount of elements
    @Override
    public int getCount() {
        return objects.size();
    }

    //element on position
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }


    //id on position
    @Override
    public long getItemId(int position) {
        return position;
    }


    //List item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //using the made-ones, but not using view
        View view = convertView;
        if (view == null){
            view = lInflater.inflate(R.layout.item, parent, false);
        }

        Product p = getProduct(position);

        //filling the View in a item list with products: name, price, image
        ((TextView)view.findViewById(R.id.tvDescr)).setText(p.name);
        ((TextView)view.findViewById(R.id.tvPrice)).setText(p.price + "");
        ((ImageView)view.findViewById(R.id.ivImage)).setImageResource(p.image);

        CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
        //assigning a handler for a checkbox
        cbBuy.setOnCheckedChangeListener(myCheckChangeList);
        //writing down a position
        cbBuy.setTag(position);
        //filling products data: is it in box or not
        cbBuy.setChecked(p.box);
        return view;
    }

    //product on position
    Product getProduct(int position){
        return ((Product) getItem(position));
    }

    //what box does contain
    ArrayList<Product> getBox(){
        ArrayList<Product> box = new ArrayList<Product>();
        for (Product p : objects){
            //if it's in the box
            if (p.box)
                box.add(p);
        }
        return box;
    }

    //Checkbox handler
    OnCheckedChangeListener myCheckChangeList = new OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            //changing the products data (it is in the box or it is not)
            getProduct((Integer) buttonView.getTag()).box = isChecked;
        }
    };
}
