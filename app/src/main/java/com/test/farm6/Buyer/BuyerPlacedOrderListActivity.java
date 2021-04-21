package com.test.farm6.Buyer;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.test.farm6.FarmApplication;
import com.test.farm6.Farmer.FarmerOrderDisplayActivity;
import com.test.farm6.R;
import com.test.farm6.model.Order;
import java.util.ArrayList;

public class BuyerPlacedOrderListActivity extends AppCompatActivity implements BuyerPlacedOrderAdapter.OrderListItemClickListener {

    private final ArrayList<Order> list = new ArrayList<>();
    private RecyclerView order_View;
    private FarmApplication farmApp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_order_list);
        farmApp = (FarmApplication) getApplication();

        order_View = (RecyclerView) findViewById(R.id.buyer_orderRecyclerView);

        setUp();

        BuyerPlacedOrderAdapter adapter = new BuyerPlacedOrderAdapter(list, this);
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

        //need to retrieve all orders placed
        farmApp.getDao().retrieveProducts();

    }
}
