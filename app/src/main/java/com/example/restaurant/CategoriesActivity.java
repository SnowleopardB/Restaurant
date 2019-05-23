package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    ListView CategorieslistView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        CategoriesRequest x = new CategoriesRequest(this);
        x.getCategories(this);
        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();

        CategorieslistView = findViewById(R.id.Categories);
        CategorieslistView.setOnItemClickListener(new ListItemClickListener());

    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        ArrayAdapter<String> Adapter =  new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        CategorieslistView.setAdapter(Adapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            String categoryname = (String) adapterView.getItemAtPosition(i);
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("categoryname", categoryname);
            startActivity(intent);
        }
    }
}
