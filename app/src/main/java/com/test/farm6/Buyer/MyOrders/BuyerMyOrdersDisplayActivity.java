package com.test.farm6.Buyer.MyOrders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.test.farm6.Buyer.MenuActivity;
import com.test.farm6.R;
import com.test.farm6.model.Order;

public class BuyerMyOrdersDisplayActivity extends MenuActivity {
    private TextView buyer_total;
    private TextView product_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_placed_order_display);
        buyer_total = findViewById(R.id.Buyer_Placed_total);
        product_list = findViewById(R.id.Buyer_placed_order_list);
        Intent intent = getIntent();

        if (getIntent().hasExtra("selected_order")) {

            Order order = intent.getParcelableExtra("selected_order");
            String total = "Total: " + order.getOrderTotalString();
            String list = order.getList().toString();
            buyer_total.setText(total);
            product_list.setText(list);
        }
    }
}
