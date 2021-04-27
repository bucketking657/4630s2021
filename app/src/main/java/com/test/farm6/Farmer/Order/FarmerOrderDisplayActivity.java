package com.test.farm6.Farmer.Order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.farm6.FarmApplication;
import com.test.farm6.R;
import com.test.farm6.model.Order;

public class FarmerOrderDisplayActivity extends AppCompatActivity {
    public static final int ORDER_PROCESSED = 25;
    private Order order;
    private RecyclerView orderDisplayRecyclerView;
    private FarmApplication farmApp;
    private FarmerOrderDisplayAdapter adapter;
    private TextView orderOwnerTextView;
    private TextView totalTextView;
    private Button process;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_order_display);
        orderOwnerTextView = findViewById(R.id.order_name_textView);
        totalTextView = findViewById(R.id.total);
        process = (Button) findViewById(R.id.Process);
        setUp();
        process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setStatus("processed");
                farmApp.getDao().processOrder(order);
                setResult(ORDER_PROCESSED);
                finish();
            }
        });
    }

    private void setUp(){
        farmApp = (FarmApplication) getApplication();
        orderDisplayRecyclerView = (RecyclerView) findViewById(R.id.farmer_ViewOrderDisplayRecyclerView);
        order = getIntent().getParcelableExtra("selected_order");
        adapter = new FarmerOrderDisplayAdapter(order.getOrderLines());
        orderOwnerTextView.setText(order.getOwner().getFullName());
        totalTextView.setText(String.format("%.2f",order.calcTotal()));
        orderDisplayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderDisplayRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}



