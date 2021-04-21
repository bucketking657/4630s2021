package com.test.farm6.Farmer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.test.farm6.R;

public class FarmerStockDisplayActivity extends AppCompatActivity {
    private TextView stockName;
    private TextInputLayout stockPrice;
    private TextInputLayout farmerEnteredQuanity;
    private Button updateQuantity;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_stock_update_display);

        stockName = findViewById(R.id.farmer_stock_name_update);
        stockPrice = findViewById(R.id.stock_update_price);
        farmerEnteredQuanity = findViewById(R.id.stock_update_quantity);

        updateQuantity = (Button) findViewById(R.id.farmer_stock_update);

        updateQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }

}
