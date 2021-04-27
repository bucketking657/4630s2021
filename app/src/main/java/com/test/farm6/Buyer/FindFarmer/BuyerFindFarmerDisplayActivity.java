package com.test.farm6.Buyer.FindFarmer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.farm6.Buyer.MenuActivity;
import com.test.farm6.Buyer.ViewFarmer.BuyerViewFarmerStockListActivity;
import com.test.farm6.FarmApplication;
import com.test.farm6.Farmer.Stock.FarmerStockListActivity;
import com.test.farm6.R;
import com.test.farm6.model.Farmer;

public class BuyerFindFarmerDisplayActivity extends MenuActivity {
    private TextView find_Farmer_business_name;
    private TextView find_Farmer_address;
    private TextView find_farmer_city;
    private Button add;
    private Button add_and_view;
    private Farmer farmer;
    private FarmApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_find_farmer_display);
        app = (FarmApplication) getApplication();
        farmer = new Farmer();
        find_Farmer_business_name = findViewById(R.id.Find_Farmer_Display_City);
        find_Farmer_address = findViewById(R.id.Find_Farmer_Display_business_Name);
        find_farmer_city = findViewById(R.id.Find_Farmer_Display_Address);
        add = findViewById(R.id.Add);
        add_and_view = findViewById(R.id.Add_And_View);
        farmer = getIntent().getParcelableExtra("selected_farmer");
        find_Farmer_business_name.setText(farmer.getBusinessName());
        find_Farmer_address.setText(farmer.getAddress());
        find_farmer_city.setText(farmer.getCity());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                app.getDao().addFarmerToUserList(farmer, app.getCurrentUser());
                Intent intent = new Intent(BuyerFindFarmerDisplayActivity.this, BuyerFindFarmersListActivity.class);
                intent.putExtra("selected_farmer", farmer);
                startActivity(intent);
            }
        });

        add_and_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("here "+farmer.getId());
                app.getDao().addFarmerToUserList(farmer, app.getCurrentUser());
                Intent intent = new Intent(BuyerFindFarmerDisplayActivity.this, BuyerViewFarmerStockListActivity.class);
                intent.putExtra("selected_farmer",farmer);
                startActivity(intent);
            }
        });
    }
}
