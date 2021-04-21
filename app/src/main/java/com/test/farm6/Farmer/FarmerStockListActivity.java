package com.test.farm6.Farmer;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.farm6.FarmApplication;
import com.test.farm6.FarmDAO;
import com.test.farm6.R;
import com.test.farm6.model.Farmer;
import com.test.farm6.model.Stock;

import java.util.ArrayList;
import java.util.List;

public class FarmerStockListActivity extends AppCompatActivity implements FarmerStockAdapter.ListItemClickListener {
    private final ArrayList<Stock> stocks = new ArrayList<>();
    private RecyclerView farmerStockRecyclerView;
    private FarmApplication app;
    private Farmer farmer;
    FarmerStockAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_stock_list);
        setUp();
        farmer = getIntent().getParcelableExtra("farmer");
       // app.getDao().retrieveStockForFarmer(farmer);
    }
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, FarmerStockDisplayActivity.class);
        intent.putExtra("selected_stock", farmer);
        startActivity(intent);
    }

    private void setUp(){
        app = (FarmApplication) getApplication();
        farmerStockRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_stock);
        adapter = new FarmerStockAdapter( stocks, this);
        farmerStockRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        farmerStockRecyclerView.setAdapter(adapter);

        app.getDao().retrieveStockForFarmer(new FarmDAO.RetrieveStockForFarmerHandler() {
            @Override
            public void stockRetrieved(List<Stock> stocks) {
                FarmerStockListActivity.this.stocks.clear();
                FarmerStockListActivity.this.stocks.addAll(stocks);
                adapter.notifyDataSetChanged();
            }
        });
    }
}