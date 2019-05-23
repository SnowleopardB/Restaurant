package com.example.restaurant;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String category = intent.getStringExtra("categoryname");
        MenuRequest x = new MenuRequest(this);
        x.getMenu(this, category);
        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();

        listview = findViewById(R.id.Menu);
        listview.setOnItemClickListener(new ListItemClickListener());
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menuItemArrayList) {

        MenuAdapter adapter = new MenuAdapter(this, R.layout.adapter_menu, menuItemArrayList);
        listview.setAdapter(adapter);
    }

    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            MenuItem clickedItem = (MenuItem) adapterView.getItemAtPosition(i);
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("clickeditem", clickedItem);
            startActivity(intent);
        }

    }
}
