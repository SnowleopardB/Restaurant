package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);
        Intent intent = getIntent();
        MenuItem item = (MenuItem) intent.getSerializableExtra("clickeditem");
        String name = item.getName();
        String price = "€ " + String.valueOf(item.getPrice());
        String url = item.getImageUrl();
        String description = item.getDescription();


        TextView nameView = findViewById(R.id.textname);
        TextView priceView = findViewById(R.id.textprice);
        ImageView fotoView = findViewById(R.id.imagemeal);
        TextView descriptionView = findViewById(R.id.textdescription);

        nameView.setText(name);
        priceView.setText(price);
        // https://github.com/square/picasso
        Picasso.get().load(url).into(fotoView);
        descriptionView.setText(description);
    }
}
