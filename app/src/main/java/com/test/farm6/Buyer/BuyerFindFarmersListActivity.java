package com.test.farm6.Buyer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.farm6.FarmDAO;
import com.test.farm6.FarmApplication;
import com.test.farm6.R;
import com.test.farm6.model.Farmer;

import java.util.ArrayList;
import java.util.List;

public class BuyerFindFarmersListActivity extends AppCompatActivity implements BuyerFindFarmersAdapter.FindFarmerClickListener {
    private final ArrayList<Farmer> farmers = new ArrayList<>();
    private RecyclerView farmerRecyclerView;
    private FarmApplication farmApp;
    private BuyerFindFarmersAdapter adapter;

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shoppingmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_find_farmer_list);
        setUp();
    }

    @Override
    public void onItemClick(Farmer farmer) {
        Intent intent = new Intent(this, BuyerFindFarmerDisplayActivity.class);
        intent.putExtra("selected_farmer", farmer);
        startActivity(intent);
    }

    private void setUp(){
        farmApp = (FarmApplication) getApplication();
        farmerRecyclerView = (RecyclerView) findViewById(R.id.buyer_FindFarmerRecyclerView);
        adapter = new BuyerFindFarmersAdapter(farmers, this);
        farmerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        farmerRecyclerView.setAdapter(adapter);
        //needs to be retrieve all farmers
        farmApp.getDao().retrieveFarmers(new FarmDAO.RetrieveFarmersHandler() {
            @Override
            public void farmersRetrieved(List<Farmer> farmers) {
                BuyerFindFarmersListActivity.this.farmers.clear();
                BuyerFindFarmersListActivity.this.farmers.addAll(farmers);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
