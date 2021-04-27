package com.test.farm6.Buyer.MyOrders;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.farm6.Buyer.MenuActivity;
import com.test.farm6.FarmApplication;
import com.test.farm6.FarmDAO;
import com.test.farm6.Farmer.Order.FarmerOrderDisplayActivity;
import com.test.farm6.R;
import com.test.farm6.model.Order;
import java.util.ArrayList;
import java.util.List;

public class BuyerMyOrdersOrderListActivity extends MenuActivity implements BuyerMyOrdersOrderAdapter.OrderListItemClickListener {

    private final ArrayList<Order> orders = new ArrayList<>();
    private RecyclerView buyerOrderRecyclerView;
    private FarmApplication farmApp;
    private BuyerMyOrdersOrderAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_order_list);
        setUp();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, FarmerOrderDisplayActivity.class);
        intent.putExtra("selected_order", orders.get(position));
        startActivity(intent);
    }

    private void setUp(){
        farmApp = (FarmApplication) getApplication();
        buyerOrderRecyclerView = (RecyclerView) findViewById(R.id.buyer_orderRecyclerView);
        adapter = new BuyerMyOrdersOrderAdapter(orders, this);
        buyerOrderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL );
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider));
        buyerOrderRecyclerView.addItemDecoration(dividerItemDecoration);
        buyerOrderRecyclerView.setAdapter(adapter);

        farmApp.getDao().retrieveOrderForUser(farmApp.getCurrentUser().getId(), new FarmDAO.RetrieverOrderHandler() {
            @Override
            public void orderRetrieved(List<Order> orders) {
                BuyerMyOrdersOrderListActivity.this.orders.clear();
                BuyerMyOrdersOrderListActivity.this.orders.addAll(orders);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
