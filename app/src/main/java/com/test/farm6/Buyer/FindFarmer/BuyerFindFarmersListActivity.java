package com.test.farm6.Buyer.FindFarmer;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.farm6.Buyer.MenuActivity;
import com.test.farm6.FarmDAO;
import com.test.farm6.FarmApplication;
import com.test.farm6.R;
import com.test.farm6.model.Farmer;

import java.util.ArrayList;
import java.util.List;

public class BuyerFindFarmersListActivity extends MenuActivity implements BuyerFindFarmersAdapter.FindFarmerClickListener {
    private final ArrayList<Farmer> farmers = new ArrayList<>();
    private RecyclerView farmerRecyclerView;
    private FarmApplication farmApp;
    private BuyerFindFarmersAdapter adapter;

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
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL );
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider));
        farmerRecyclerView.addItemDecoration(dividerItemDecoration);
        farmerRecyclerView.setAdapter(adapter);

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
