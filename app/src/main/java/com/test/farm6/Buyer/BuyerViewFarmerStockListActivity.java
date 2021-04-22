package com.test.farm6.Buyer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.farm6.FarmApplication;
import com.test.farm6.FarmDAO;
import com.test.farm6.R;
import com.test.farm6.model.Farmer;
import com.test.farm6.model.Stock;
import com.test.farm6.model.User;

import java.util.ArrayList;
import java.util.List;

public class BuyerViewFarmerStockListActivity extends AppCompatActivity implements BuyerViewFarmerStockAdapter.ListItemClickListener {
    private final ArrayList<Stock> stocks = new ArrayList<>();
    private FarmApplication app;
    private RecyclerView buyerViewStockRecyclerView;
    private Farmer farmer;
    private User user;
    private BuyerViewFarmerStockAdapter adapter;


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shoppingmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_view_farmer_stock_list);
        setUp();

    }
    @Override
    public void onItemClick(Stock stock) {
        Intent intent = new Intent(this, BuyerViewFarmerStockDisplayActivity.class);
        intent.putExtra("selected_stock", stock);
        startActivity(intent);
    }

    private void setUp(){
        app = (FarmApplication) getApplication();
        buyerViewStockRecyclerView = (RecyclerView) findViewById(R.id.buyer_ViewFarmerStockRecyclerView);
        adapter = new BuyerViewFarmerStockAdapter(stocks, this);
        buyerViewStockRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        buyerViewStockRecyclerView.setAdapter(adapter);
        farmer = getIntent().getParcelableExtra("selected_farmer");
        stocks.addAll(farmer.getStock().values());
        adapter.notifyDataSetChanged();


    }
}
