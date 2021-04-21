package com.test.farm6.Farmer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.test.farm6.FarmApplication;
import com.test.farm6.R;
import com.test.farm6.model.Farmer;
import com.test.farm6.model.Product;
import com.test.farm6.model.Stock;

public class FarmerStockNewActivity extends AppCompatActivity {
    private EditText newProductName;
    private EditText newProductPrice;
    private EditText newProductQuantity;
    private Button addAndView;
    private Button addAnother;
    private Stock newStock;
    private FarmApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_stock_new);
        app = (FarmApplication) getApplication();
        newProductName = (EditText) findViewById(R.id.new_product_name);
        newProductPrice = (EditText) findViewById(R.id.new_product_price);
        newProductQuantity = (EditText) findViewById(R.id.new_product_quantity);
        addAndView = (Button) findViewById(R.id.Farmer_Add_And_View);
        addAnother = (Button) findViewById(R.id.Add_Another);

        addAndView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(newProductName != null && newProductPrice != null && newProductQuantity !=null) {
                    gatherData();

                    //add stock item to farmer stock
                    app.getDao().saveFarmerStock(newStock, app.getCurrentUser().getId());

                    Intent intent = new Intent(FarmerStockNewActivity.this, FarmerStockListActivity.class);
                    startActivity(intent);
                }
            }
        });

        addAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newProductName != null && newProductPrice != null && newProductQuantity !=null) {
                    gatherData();
                    //add stock Item
                    app.getDao().saveFarmerStock(newStock, app.getCurrentUser().getId());
                    newProductName.getText().clear();
                    newProductPrice.getText().clear();
                    newProductQuantity.getText().clear();
                }
            }
        });
    }

    public void gatherData(){
        Product newProduct = new Product();
        newProduct.setName(newProductName.getText().toString());
        newProduct.setPrice(Double.parseDouble(newProductPrice.getText().toString()));
        newStock = new Stock();
        newStock.setProduct(newProduct);
        newStock.setAmount(Integer.parseInt(newProductQuantity.getText().toString()));

    }
}
