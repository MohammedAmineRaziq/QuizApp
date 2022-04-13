package com.example.quizzingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Quiz2 extends AppCompatActivity {
    RadioButton True,False;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);

        True=findViewById(R.id.Q2True);
        False=findViewById(R.id.Q2False);
        next=findViewById(R.id.NextQ2);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Quiz2.this, Quiz3.class);
                if(True.isChecked()){
                    intent.putExtra("Q2Answer",getIntent().getIntExtra("Q1Answer",-1)+1);
                    startActivity(intent);
                    finish();
                }
                else if(False.isChecked()){
                    intent.putExtra("Q2Answer",getIntent().getIntExtra("Q1Answer",-1));
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}