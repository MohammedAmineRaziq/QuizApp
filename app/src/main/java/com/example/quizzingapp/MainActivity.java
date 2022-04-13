package com.example.quizzingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText email, password;
    Button login;
    TextView toregister ,forgotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.EmailLogin);
        password = findViewById(R.id.LoginPassword);
        login = findViewById(R.id.buttonlogin);
        toregister = findViewById(R.id.textRegister);
        forgotPassword=findViewById(R.id.forgot);

        login.setOnClickListener(view -> {
            if(TextUtils.isEmpty(email.getText().toString().trim())){
                Toast.makeText(MainActivity.this, "Email cannot be Empty", Toast.LENGTH_LONG).show();
            }
            else if(TextUtils.isEmpty(password.getText().toString().trim())) {
                Toast.makeText(MainActivity.this, "Password cannot be Empty", Toast.LENGTH_LONG).show();
            }
            else {
                mAuth.signInWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();

                            if (user.isEmailVerified()){
                                startActivity(new Intent(MainActivity.this, Profile.class));
                            }
                            else
                            {
                                user.sendEmailVerification();
                                Toast.makeText(MainActivity.this, "Check Email to verify your account .", Toast.LENGTH_SHORT).show();
                            }
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Email or password is incorrect", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        toregister.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, Register.class)));
        forgotPassword.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ForgotPassword.class)));

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(MainActivity.this, Profile.class));
            finish();
        }
    }
}