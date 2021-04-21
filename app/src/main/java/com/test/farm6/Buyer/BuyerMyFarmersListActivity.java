package com.test.farm6.Buyer;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.farm6.FarmApplication;
import com.test.farm6.FarmDAO;
import com.test.farm6.Farmer.FarmerStockListActivity;
import com.test.farm6.R;
import com.test.farm6.model.Farmer;

import java.util.ArrayList;
import java.util.List;

public class BuyerMyFarmersListActivity extends AppCompatActivity implements BuyerMyFarmerAdapter.FarmerListClickListener  {
    private final List<Farmer> farmers = new ArrayList<>();
    private RecyclerView myFarmerRecyclerView;
    private FarmApplication farmApp;
    private BuyerMyFarmerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_my_farmers_list);
        farmApp = (FarmApplication) getApplication();
        myFarmerRecyclerView = (RecyclerView) findViewById(R.id.buyer_MyFarmerRecyclerView);
        setUp();


    }

    @Override
    public void onItemClick(Farmer farmer) {
        //Must get farmer ID
        //Then that farmers Stock
        Intent intent = new Intent(this, BuyerViewFarmerStockListActivity.class);
        intent.putExtra("selected_farmer", farmer);
        startActivity(intent);
    }

    private void setUp(){
        adapter = new BuyerMyFarmerAdapter(farmers, this);
        myFarmerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myFarmerRecyclerView.setAdapter(adapter);
        farmApp.getDao().retrieveUserFarmers(farmApp.getCurrentUser().getId(), new FarmDAO.RetrieveFarmersHandler() {
            @Override
            public void farmersRetrieved(List<Farmer> farmers) {
                BuyerMyFarmersListActivity.this.farmers.clear();
                BuyerMyFarmersListActivity.this.farmers.addAll(farmers);
                adapter.notifyDataSetChanged();
            }
        });

    }


}
