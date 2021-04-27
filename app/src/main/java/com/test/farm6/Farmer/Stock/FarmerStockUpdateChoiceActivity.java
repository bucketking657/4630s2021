package com.test.farm6.Farmer.Stock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.test.farm6.FarmApplication;
import com.test.farm6.R;
import com.test.farm6.model.Farmer;

public class FarmerStockUpdateChoiceActivity extends AppCompatActivity {
    private Button updateStock;
    private Button add;
    private FarmApplication farmApplication;
    private Farmer farmer;

    @Override
    protected void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       setContentView(R.layout.farmer_stock_update_choice);
       updateStock = findViewById(R.id.Update_Products);
       add = findViewById(R.id.new_stock);

       updateStock.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(FarmerStockUpdateChoiceActivity.this, FarmerStockListActivity.class);
              // intent.putExtra("selected_farmer", farmer);
               startActivity(intent);
           }
       });

       add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                Intent intent = new Intent(FarmerStockUpdateChoiceActivity.this, FarmerStockNewActivity.class);
                startActivity(intent);
           }
       });
    }
}
