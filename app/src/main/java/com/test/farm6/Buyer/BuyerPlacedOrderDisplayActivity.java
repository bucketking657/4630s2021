package com.test.farm6.Buyer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.test.farm6.R;
import com.test.farm6.model.Order;

public class BuyerPlacedOrderDisplayActivity extends AppCompatActivity {

    private TextView buyer_total;
    private TextView product_list;

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shoppingmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

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
