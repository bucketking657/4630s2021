package com.test.farm6.Farmer.Stock;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
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
    private Stock selectedStock;
    FarmerStockAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_stock_list);
        setUp();
    }

    @Override
    public void onItemClick(Stock stock) {
        Intent intent = new Intent(this, FarmerStockDisplayActivity.class);
        intent.putExtra("selected_stock", stock);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Stock stock = data.getParcelableExtra("stock");
            farmer.getStock().put(stock.getId(),stock);
            stocks.clear();
            stocks.addAll(farmer.getStock().values());
            adapter.notifyDataSetChanged();
        }
    }

    private void setUp(){
        app = (FarmApplication) getApplication();
        farmerStockRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_stock);
        adapter = new FarmerStockAdapter( stocks, this);
        farmerStockRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL );
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_for_white_background));
        farmerStockRecyclerView.addItemDecoration(dividerItemDecoration);
        farmerStockRecyclerView.setAdapter(adapter);
        farmer = (Farmer) app.getCurrentUser();
        stocks.addAll(farmer.getStock().values());
        adapter.notifyDataSetChanged();

    }
}