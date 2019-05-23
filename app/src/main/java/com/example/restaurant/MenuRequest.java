package com.example.restaurant;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context2;
    private Callback callback2;
    private String categorysearch;

    public MenuRequest(Context context) {
        context2 = context;
    }

    public void getMenu(MenuActivity activity, String categoryname) {

        callback2 = activity;
        categorysearch = categoryname;
        String url = "https://resto.mprog.nl/menu";
        RequestQueue queue = Volley.newRequestQueue(context2);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }

    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menuItemArrayList);
        void gotMenuError(String message);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String errorMessage = error.getMessage();
        callback2.gotMenuError(errorMessage);
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray menuArray = null;
        try {
            menuArray = response.getJSONArray("items");
            Log.d("JSON", String.valueOf(menuArray));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<MenuItem> menuItemArrayList = new ArrayList<MenuItem>();

        JSONObject menuItemObject = null;
        try {
            for (int i = 0; i < menuArray.length(); i++) {
                menuItemObject = menuArray.getJSONObject(i);
                Log.d("ITEMS", String.valueOf(menuItemObject));

                try {
                    if (menuItemObject.getString("category").equals(categorysearch)) {

                        Log.d("LOG100", menuItemObject.getString("name"));
                        String name = menuItemObject.getString("name");
                        String description = menuItemObject.getString("description");
                        String imageUrl = menuItemObject.getString("image_url");
                        double price = menuItemObject.getDouble("price");
                        String category = menuItemObject.getString("category");
                        MenuItem item = new MenuItem(name, description, imageUrl, price, category);
                        menuItemArrayList.add(item);
                        Log.d("menuitems", String.valueOf(item.getPrice()));
                        Log.d("menuitems", String.valueOf(item.getName()));
                        Log.d("menuitems", String.valueOf(item.getDescription()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        callback2.gotMenu(menuItemArrayList);
    }
}


