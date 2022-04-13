package com.example.quizzingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

public class Quiz1 extends AppCompatActivity {
    RadioButton True,False;
    Button next;
    ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);

        True=findViewById(R.id.Q1True);
        False=findViewById(R.id.Q1False);
        next=findViewById(R.id.NextQ1);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Quiz1.this, Quiz2.class);
                if(True.isChecked()){
                    intent.putExtra("Q1Answer",1);
                    startActivity(intent);
                    finish();
                }
                else if(False.isChecked()){
                    intent.putExtra("Q1Answer",0);
                    startActivity(intent);
                    finish();
                }
            }
        });


        profile = findViewById(R.id.profile);
        profile.setOnClickListener(view -> startActivity(new Intent(Quiz1.this, Profile.class)));
    }
}