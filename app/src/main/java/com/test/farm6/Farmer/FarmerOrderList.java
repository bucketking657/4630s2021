package com.test.farm6.Farmer;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.farm6.FarmApplication;
import com.test.farm6.R;
import com.test.farm6.model.Order;

import java.util.ArrayList;

public class FarmerOrderList extends AppCompatActivity implements FarmerOrderAdapter.ListItemClickListener {

    private final ArrayList<Order> list = new ArrayList<>();
    private RecyclerView order_View;
    private FarmApplication farmApp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_order_list);
        farmApp = (FarmApplication) getApplication();

        order_View = (RecyclerView) findViewById(R.id.orderRecyclerView);

        setUp();

        FarmerOrderAdapter adapter = new FarmerOrderAdapter(list, this);
        order_View.setLayoutManager(new LinearLayoutManager(this));
        order_View.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, FarmerOrderDisplayActivity.class);
        intent.putExtra("selected_order", list.get(position));
        startActivity(intent);
    }

    private void setUp(){
        farmApp.getDao().retrieveProducts();

    }
}
