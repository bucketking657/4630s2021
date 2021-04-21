package com.test.farm6.Buyer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.test.farm6.R;

public class BuyerCurrentOrderActivity extends AppCompatActivity  {
    private TextView current_order_total;
    private TextView current_order_products;
    private Button edit;
    private Button place_order;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_current_order_display);
        current_order_total = findViewById(R.id.current_order_total);
        current_order_products = findViewById(R.id.current_order_all_items);
        edit = (Button) findViewById(R.id.current_order_edit);
        place_order = (Button) findViewById(R.id.current_order_place_order);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
