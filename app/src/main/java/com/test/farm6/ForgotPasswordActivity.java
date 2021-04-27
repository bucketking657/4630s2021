package com.test.farm6;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    private TextView message;
    private EditText enteremail;
    private Button submitEmail;
    private FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);
        mAuth = FirebaseAuth.getInstance();
        message = findViewById(R.id.message);
        enteremail = (EditText) findViewById(R.id.forgot_password_enterEmail);
        submitEmail = findViewById(R.id.send_email);

        submitEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userEmail = enteremail.getText().toString().trim();
                System.out.println("This is email" + userEmail);
                if(TextUtils.isEmpty(userEmail)){
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter email address", Toast.LENGTH_LONG).show();
                }
                else{
                   mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if (task.isSuccessful()){
                               Toast.makeText(ForgotPasswordActivity.this, "sucess", Toast.LENGTH_LONG).show();
                               startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                           }else{
                               Toast.makeText(ForgotPasswordActivity.this, "faile", Toast.LENGTH_LONG).show();

                           }
                       }
                   });
                }
            }
        });
    }
}
