package com.test.farm6.Farmer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.test.farm6.R;
import com.test.farm6.model.Order;

public class FarmerOrderDisplayActivity extends AppCompatActivity {
    private TextView order_status;
    private TextView total;
    private Button process;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_order_display);

        order_status = findViewById(R.id.Order_Display);
        total = findViewById(R.id.total);
        process = findViewById(R.id.Process);
        Intent intent = getIntent();

        if (getIntent().hasExtra("selected_order")) {

            Order order = intent.getParcelableExtra("selected_order");
            String status = "Status : " + order.getOrderStatus();
            String order_total = "Total: " + order.getOrderTotalString();

            order_status.setText(status);
            total.setText(order_total);

            process.setOnClickListener(view -> {

                order.setStatus(true);
                System.out.println(order.getStatus());

                launchActivity();
            });
        }
    }

        private void launchActivity () {

            Intent intent = new Intent(this, FarmerMainActivity.class);
            startActivity(intent);
        }
}



