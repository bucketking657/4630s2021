package com.test.farm6;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.test.farm6.Buyer.BuyerMainActivity;
import com.test.farm6.Farmer.FarmerMainActivity;
import com.test.farm6.model.Farmer;
import com.test.farm6.model.User;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private TextView titleTextView, registerTextView, forgetPassTextView;
    private TextInputLayout passwordEditText;
    private TextInputLayout emailEditText;
    private ImageView logoImageView;
    private Button loginButton;
    private RadioButton accountFlag;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private FarmApplication farmApplication;

    private boolean radioStatus(View v) {
        return accountFlag.isChecked();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        farmApplication = (FarmApplication) getApplication();
        registerTextView = findViewById(R.id.register_textview);
        forgetPassTextView = findViewById(R.id.forget_password_textview);
        emailEditText = findViewById(R.id.emailogin_edittext);
        passwordEditText = findViewById(R.id.password_edittext);
        loginButton = findViewById(R.id.View_Orders);
        accountFlag = findViewById(R.id.radio);
        mAuth = FirebaseAuth.getInstance();
        registerTextView.setOnClickListener((View v) -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userEnteredName = emailEditText.getEditText().getText().toString().trim();
                final String userEnteredPword = passwordEditText.getEditText().getText().toString().trim();

                if (TextUtils.isEmpty(userEnteredName)) {
                    emailEditText.setError("Email required");
                    return;
                }
                if (TextUtils.isEmpty(userEnteredPword)) {
                    passwordEditText.setError("Password Required");
                    return;
                }
                if (userEnteredPword.length() < 6) {
                    passwordEditText.setError("Password to short");
                    return;
                }

                mAuth.signInWithEmailAndPassword(userEnteredName, userEnteredPword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        System.out.println("This is my flag: " + accountFlag.isChecked());
                        if (task.isSuccessful()) {

                            Toast.makeText(LoginActivity.this, "Log in Successful", Toast.LENGTH_SHORT).show();

                            if (accountFlag.isChecked()) {
                                farmApplication.getDao().findFarmerByUid(FirebaseAuth.getInstance().getCurrentUser().getUid(), new FarmDAO.RetrieveFarmerHandler() {
                                    @Override
                                    public void farmerRetrieved(Farmer farmer) {
                                        farmApplication.setCurrentUser(farmer);
                                    }
                                });
                                startActivity(new Intent(getApplicationContext(), FarmerMainActivity.class));
                            } else {
                                System.out.println("In side buyer block");
                                farmApplication.getDao().findUserByUid(FirebaseAuth.getInstance().getCurrentUser().getUid(), new FarmDAO.RetrieveUserHandler() {
                                    @Override
                                    public void userRetrieved(User user) {
                                        farmApplication.setCurrentUser(user);
                                    }
                                });
                                startActivity(new Intent(getApplicationContext(), BuyerMainActivity.class));
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Erorr" + task.getException(), Toast.LENGTH_SHORT).show();
                            System.out.println(task.getException());

                        }
                    }
                });
            }
        });
    }
}
