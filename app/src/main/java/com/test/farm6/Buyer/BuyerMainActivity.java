package com.test.farm6.Buyer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.test.farm6.Buyer.FindFarmer.BuyerFindFarmersListActivity;
import com.test.farm6.Buyer.MyFarmers.BuyerMyFarmersListActivity;
import com.test.farm6.Buyer.MyOrders.BuyerMyOrdersOrderListActivity;
import com.test.farm6.R;

public class BuyerMainActivity extends MenuActivity {

    private Button myOrders;
    private Button myFarmers;
    private Button findFarmers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_activity_main);
        myOrders = (Button) findViewById(R.id.My_Orders);
        myFarmers = (Button) findViewById(R.id.my_Farmers);
        findFarmers = (Button) findViewById(R.id.find_farmers);

        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyerMainActivity.this, BuyerMyOrdersOrderListActivity.class);
                startActivity(intent);
            }
        });

        myFarmers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyerMainActivity.this, BuyerMyFarmersListActivity.class);
                startActivity(intent);
            }
        });

        findFarmers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyerMainActivity.this, BuyerFindFarmersListActivity.class);
                startActivity(intent);
            }
        });
    }
}

