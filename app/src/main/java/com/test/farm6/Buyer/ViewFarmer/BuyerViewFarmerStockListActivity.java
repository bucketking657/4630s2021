package com.test.farm6.Buyer.ViewFarmer;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.farm6.Buyer.MenuActivity;
import com.test.farm6.FarmApplication;
import com.test.farm6.R;
import com.test.farm6.model.Farmer;
import com.test.farm6.model.Stock;
import com.test.farm6.model.User;

import java.util.ArrayList;

import javax.xml.transform.Result;

public class BuyerViewFarmerStockListActivity extends MenuActivity implements BuyerViewFarmerStockAdapter.ListItemClickListener {
    private final ArrayList<Stock> stocks = new ArrayList<>();
    private FarmApplication app;
    private RecyclerView buyerViewStockRecyclerView;
    private Farmer farmer;
    private User user;
    private BuyerViewFarmerStockAdapter adapter;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_view_farmer_stock_list);
        setUp();
    }

    @Override
    public void onItemClick(Stock stock) {
        Intent intent = new Intent(this, BuyerViewFarmerStockDisplayActivity.class);
        intent.putExtra("selected_stock", stock);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == AppCompatActivity.RESULT_OK) {
            app.getCurrentOrder().setFarmer(farmer);
        }
    }

    private void setUp(){
        app = (FarmApplication) getApplication();
        buyerViewStockRecyclerView = (RecyclerView) findViewById(R.id.buyer_ViewFarmerStockRecyclerView);
        adapter = new BuyerViewFarmerStockAdapter(stocks, this);
        buyerViewStockRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL );
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_for_white_background));
        buyerViewStockRecyclerView.addItemDecoration(dividerItemDecoration);
        buyerViewStockRecyclerView.setAdapter(adapter);
        farmer = getIntent().getParcelableExtra("selected_farmer");
        stocks.addAll(farmer.getStock().values());
        adapter.notifyDataSetChanged();
    }
}
