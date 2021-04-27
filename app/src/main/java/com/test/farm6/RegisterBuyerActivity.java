package com.test.farm6;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.test.farm6.Buyer.BuyerMainActivity;
import com.test.farm6.Farmer.FarmerMainActivity;
import com.test.farm6.model.User;

public class RegisterBuyerActivity extends AppCompatActivity {
    private TextInputLayout buyer_first_name;
    private TextInputLayout buyer_last_name;
    private TextInputLayout buyer_Email;
    private TextInputLayout buyer_password;
    private Button submit;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    private String userid;
    private FarmApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_buyer);
        app = (FarmApplication) getApplication();

        buyer_first_name = findViewById(R.id.buyer_reg_fname);
        buyer_last_name = findViewById(R.id.buyer_reg_lname);
        buyer_Email = findViewById(R.id.buyer_reg_email);
        buyer_password = findViewById(R.id.buyer_reg_password);
        
        submit = findViewById(R.id.buyer_reg_submit);
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = buyer_Email.getEditText().getText().toString();
                String password = buyer_password.getEditText().getText().toString();
                String firstName = buyer_first_name.getEditText().getText().toString();
                String lastname = buyer_last_name.getEditText().getText().toString();

                if (TextUtils.isEmpty(email)) {
                    buyer_Email.setError("Email Required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    buyer_Email.setError("Password Required");
                    return;
                }
                System.out.println("pass text utils");

                //user Registered here
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        System.out.println("inside on complete");
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterBuyerActivity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();

                            userid = fAuth.getCurrentUser().getUid();

                            User buyer = new User();
                            buyer.setFirstName(firstName);
                            buyer.setLastName(lastname);
                            buyer.setEmail(email);
                            buyer.setPassword(password);

                            app.getDao().saveUser(buyer,userid);


                            startActivity(new Intent(getApplicationContext(), BuyerMainActivity.class));

                        } else {
                            System.out.println("Inside exception else");
                            Toast.makeText(RegisterBuyerActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}