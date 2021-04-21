package com.test.farm6;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.test.farm6.Farmer.FarmerMainActivity;
import com.test.farm6.model.Farmer;

public class RegisterSellerActivity extends AppCompatActivity {

    private TextInputLayout first_name;
    private TextInputLayout last_name;
    private TextInputLayout business_name;
    private TextInputLayout seller_address;
    private TextInputLayout seller_city;
    private TextInputLayout email_input;
    private TextInputLayout password_input;
    private Button submit;
    private String TAG = "RegisterActivity";
    private String userid;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    private FarmApplication app;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_farmer);
        app = (FarmApplication) getApplication();

        first_name = findViewById(R.id.seller_reg_fname);
        last_name = findViewById(R.id.seller_last_name);
        business_name = findViewById(R.id.seller_reg_Business_Name);
        seller_address = findViewById(R.id.seller_reg_address);
        seller_city = findViewById(R.id.seller_reg_city);
        email_input = (TextInputLayout) findViewById(R.id.seller_reg_email);
        password_input =(TextInputLayout) findViewById(R.id.seller_reg_password);

        submit = findViewById(R.id.seller_reg_submit);

        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = first_name.getEditText().getText().toString();
                String lastName = last_name.getEditText().getText().toString();
                String businessName = business_name.getEditText().getText().toString();
                String address = seller_address.getEditText().getText().toString();
                String city = seller_city.getEditText().getText().toString();
                String email = email_input.getEditText().getText().toString();
                String password = password_input.getEditText().getText().toString();

                if (TextUtils.isEmpty(email)) {
                    email_input.setError("Email Required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    password_input.setError("Password Required");
                    return;
                }

                //user Registerd here
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterSellerActivity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();

                            userid = fAuth.getCurrentUser().getUid();
                            Farmer farmer = new Farmer();
                            farmer.setFirstName(firstName);
                            farmer.setLastName(lastName);
                            farmer.setBusinessName(businessName);
                            farmer.setAddress(address);
                            farmer.setCity(city);
                            farmer.setEmail(email);
                            farmer.setPassword(password);


                            app.getDao().saveFarmer(farmer,userid);

                        /*    DocumentReference docRef = fstore.collection("Seller").document(userid);
                            Map<String, Object> seller = new HashMap<>();

                            seller.put("fname",firstName);
                            seller.put("lname",lastname);
                            seller.put("email",email);
                            seller.put("password",password);
                            seller.put("address",address);
                            seller.put("Busniess Name",businessName);

                            docRef.set(seller).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Log.d("TAG","onSucess");

                                }
                            });*/

                            startActivity(new Intent(getApplicationContext(), FarmerMainActivity.class));
                        } else {
                            Toast.makeText(RegisterSellerActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            System.out.println(task.getException().getMessage());
                        }
                    }
                });
            }
        });
    }
}