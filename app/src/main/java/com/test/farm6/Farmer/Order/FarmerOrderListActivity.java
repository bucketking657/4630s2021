package com.test.farm6.Farmer.Order;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.farm6.FarmApplication;
import com.test.farm6.FarmDAO;
import com.test.farm6.R;
import com.test.farm6.model.Order;

import java.util.ArrayList;
import java.util.List;

public class FarmerOrderListActivity extends AppCompatActivity implements FarmerOrderAdapter.ListItemClickListener {

    private final ArrayList<Order> orders = new ArrayList<>();
    private RecyclerView orderRecyclerView;
    private FarmApplication farmApp;
    private FarmerOrderAdapter adapter;
    private Order selectedOrder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_order_list);
        setUp();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, FarmerOrderDisplayActivity.class);
        selectedOrder = orders.get(position);
        intent.putExtra("selected_order",selectedOrder);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == FarmerOrderDisplayActivity.ORDER_PROCESSED) {
            selectedOrder.setStatus("processed");
            adapter.notifyDataSetChanged();
        }
    }

    private void setUp(){
        farmApp = (FarmApplication) getApplication();
        orderRecyclerView = (RecyclerView) findViewById(R.id.orderRecyclerView);
        adapter = new FarmerOrderAdapter(orders, this);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL );
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider));
        orderRecyclerView.addItemDecoration(dividerItemDecoration);
        orderRecyclerView.setAdapter(adapter);

        farmApp.getDao().retrieveOrderForFarmer(farmApp.getCurrentUser().getId(), new FarmDAO.RetrieverOrderHandler()  {
            @Override
            public void orderRetrieved(List<Order> orders  ) {
                FarmerOrderListActivity.this.orders.clear();
                FarmerOrderListActivity.this.orders.addAll(orders);
                adapter.notifyDataSetChanged();

            }
        });

    }
}
