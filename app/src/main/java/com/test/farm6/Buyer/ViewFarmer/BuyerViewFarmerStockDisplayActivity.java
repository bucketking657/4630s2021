package com.test.farm6.Buyer.ViewFarmer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.test.farm6.Buyer.MenuActivity;
import com.test.farm6.FarmApplication;
import com.test.farm6.R;
import com.test.farm6.model.OrderLine;
import com.test.farm6.model.Stock;
import com.test.farm6.model.User;

public class BuyerViewFarmerStockDisplayActivity extends MenuActivity {

    private TextView buyerProductDisplay;
    private TextView buyerPriceDisplay;
    private TextInputEditText buyerEnterQuantity;
    private Button addStockToCart;
    private FarmApplication app;
    private Stock stock;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_add_farmer_stock);
        buyerProductDisplay = findViewById(R.id.product_name);
        buyerPriceDisplay = findViewById(R.id.product_price);
        buyerEnterQuantity = findViewById(R.id.quantity_entered_by_user_in_stock);
        addStockToCart = (Button) findViewById(R.id.add_to_bag);
        app = (FarmApplication) getApplication();
        stock = getIntent().getParcelableExtra("selected_stock");

        addStockToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantityText = buyerEnterQuantity.getText().toString();
                if (quantityText.isEmpty()){
                    Toast.makeText(BuyerViewFarmerStockDisplayActivity.this,"You need to specify a quantity",Toast.LENGTH_LONG).show();
                    return;
                }
                Double quantity = Double.parseDouble(quantityText);
                OrderLine orderLine = new OrderLine();
                orderLine.setProduct(stock.getProduct());
                orderLine.setQuantity(quantity);
                app.getCurrentOrder().getOrderLines().add(orderLine);
                setResult(AppCompatActivity.RESULT_OK);
                finish();
            }
        });
    }
}
