package com.test.farm6.Farmer.Stock;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.test.farm6.FarmApplication;
import com.test.farm6.R;
import com.test.farm6.model.Farmer;
import com.test.farm6.model.Product;
import com.test.farm6.model.Stock;

public class FarmerStockDisplayActivity extends AppCompatActivity {
    private TextView stockName;
    private TextInputLayout stockPrice;
    private TextInputLayout farmerEnteredQuanity;
    private Button updateQuantity;
    private Stock stock;
    private Farmer farmer;
    private FarmApplication farmApp;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_stock_update_display);
        farmApp = (FarmApplication) getApplication();
        stockName = findViewById(R.id.farmer_stock_name_update);
        stockPrice = findViewById(R.id.stock_update_price);
        farmerEnteredQuanity = findViewById(R.id.stock_update_quantity);
        updateQuantity = (Button) findViewById(R.id.farmer_stock_update);
        stock = getIntent().getParcelableExtra("selected_stock");
        stockName.setText(stock.getProduct().getName() );
        farmer =getIntent().getParcelableExtra("current_farmer");
        farmer = (Farmer) farmApp.getCurrentUser();

        updateQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantityString = farmerEnteredQuanity.getEditText().getText().toString();
                String priceString = stockPrice.getEditText().getText().toString();
                if(quantityString.isEmpty() && priceString.isEmpty()){
                    Toast.makeText(getBaseContext(),"You need to specify at least a new quantity or price",Toast.LENGTH_LONG);
                }

                Double newPrice = (priceString.isEmpty()?null:Double.parseDouble(priceString));
                Double newQuantity = (quantityString.isEmpty()?null:Double.parseDouble(quantityString));
                if (newPrice!= null){
                    stock.getProduct().setPrice(newPrice);
                }
                if (newQuantity != null){
                    stock.setWeight(newQuantity);
                }
                farmApp.getDao().updateStock(farmApp.getCurrentUser(),stock);
                Intent intent = new Intent();
                intent.putExtra("stock",stock);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }
}
