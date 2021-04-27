package com.test.farm6.Cart;

//Fragment??
//activity??

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.test.farm6.FarmApplication;
import com.test.farm6.R;
import com.test.farm6.model.Order;
import com.test.farm6.model.OrderLine;

public class CartActivity extends AppCompatActivity implements CartAdapter.EditItemClickListener {
    private TextView orderTotal;
    private RecyclerView orderLinesRecyclerView;
    private Order order;
    private FarmApplication app;
    private CartAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_cart);
        orderTotal = findViewById(R.id.orderTotalTextView);
        orderLinesRecyclerView = findViewById(R.id.cartRecyclerView);
        app = (FarmApplication) getApplication();
        order = app.getCurrentOrder();
        adapter = new CartAdapter(order.getOrderLines(),this);
        orderLinesRecyclerView.setAdapter(adapter);
        setTitle("Your shopping cart");
        orderTotal.setText(String.format("%.2f$",order.calcTotal()));
    }

    @Override
    public void onItemClick(OrderLine orderLine) {

    }

    public void placeOrderClicked(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm order submit");
        builder.setMessage("You are about to submit your order. Are you sure?");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                app.placeOrder();
                Toast.makeText(CartActivity.this,"Your order has been placed",Toast.LENGTH_LONG).show();
                finish();
            }
        });
        builder.setNegativeButton("Cancel",null).show();
    }
}
