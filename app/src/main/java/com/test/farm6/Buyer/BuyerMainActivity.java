package com.test.farm6.Buyer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.test.farm6.R;

public class BuyerMainActivity extends AppCompatActivity {

    private Button myOrders;
    private Button myFarmers;
    private Button findFarmers;
    private Button currentOrder;

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shoppingmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_activity_main);

        myOrders = (Button) findViewById(R.id.My_Orders);
        myFarmers = (Button) findViewById(R.id.my_Farmers);
        findFarmers = (Button) findViewById(R.id.find_farmers);
        currentOrder = (Button) findViewById(R.id.current_orders);

        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(BuyerMainActivity.this, BuyerPlacedOrderListActivity.class);
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

        currentOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyerMainActivity.this, BuyerCurrentOrderActivity.class);
                startActivity(intent);
            }
        });
    }


}

