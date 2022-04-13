package com.example.quizzingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText name,email,password,confirmpassword;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        name=findViewById(R.id.Name);
        email=findViewById(R.id.EmailRegister);
        password=findViewById(R.id.PasswordRegister);
        confirmpassword=findViewById(R.id.ConfirmPasswordRegister);
        register=findViewById(R.id.buttonregister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(email.getText().toString().trim())){
                    Toast.makeText(Register.this, "Email cannot be Empty", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(password.getText().toString().trim())) {
                    Toast.makeText(Register.this, "Password cannot be Empty", Toast.LENGTH_LONG).show();
                }
                else {
                    if(password.getText().toString().trim().equals(confirmpassword.getText().toString().trim())){
                        mAuth.createUserWithEmailAndPassword(email.getText().toString().trim(),password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if((task.isSuccessful())) {
                                    User user=new User(name.getText().toString(),email.getText().toString().trim());
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(Register.this, "User Registered successfully", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(Register.this, MainActivity.class));
                                        }
                                    });
                                }
                                else {
                                    Toast.makeText(Register.this, "Registration Error .", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                        Log.i("Information",name.getText().toString()+" "+email.getText().toString()+" "+password.getText().toString());
                    }
                    else {
                        Toast.makeText(Register.this, "Password and Confirm Password are not equal", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(Register.this, Profile.class));
        }
    }
}