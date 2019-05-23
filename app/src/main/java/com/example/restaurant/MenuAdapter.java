package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.CookieHandler;
import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter<MenuItem>{

    ArrayList<MenuItem> menuItemArrayList;
    Context context1;

    public MenuAdapter(Context context, int adapter_menu, ArrayList<MenuItem> object) {
        super(context, adapter_menu, object);
        context1 = context;
        menuItemArrayList = object;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_menu, parent, false);
        }

        MenuItem item = menuItemArrayList.get(position);

        String name = item.getName();
        String price = "€ " + String.valueOf(item.getPrice());
        String url = item.getImageUrl();

        ImageView image = convertView.findViewById(R.id.MenuPicture);
        TextView nameView = convertView.findViewById(R.id.MenuText);
        TextView priceView = convertView.findViewById(R.id.MenuPrice);

        nameView.setText(name);
        priceView.setText(price);

        // nog ff if statement om URL heen

        // https://github.com/square/picasso
        Picasso.get().load(url).into(image);

        return convertView;



    }
}
