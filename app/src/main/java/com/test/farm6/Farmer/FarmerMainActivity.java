package com.test.farm6.Farmer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.test.farm6.R;

public class FarmerMainActivity extends AppCompatActivity {
    private Button mBtLaunchActivity;
    private Button view_stock;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_activity_main);

        mBtLaunchActivity = (Button) findViewById(R.id.View_Orders);
        view_stock = (Button) findViewById(R.id.View_Stock);
        mBtLaunchActivity.setOnClickListener(view -> launchActivity());
        view_stock.setOnClickListener(v -> launchStock());
    }

    private void launchStock(){
        Intent stock_intent = new Intent(this, FarmerStockUpdateChoiceActivity.class);
        startActivity(stock_intent);
    }

    private void launchActivity () {
        Intent intent = new Intent(this, FarmerOrderList.class);
        startActivity(intent);
    }

}
