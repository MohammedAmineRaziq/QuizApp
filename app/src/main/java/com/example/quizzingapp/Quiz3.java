package com.example.quizzingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Quiz3 extends AppCompatActivity {
    RadioButton True,False;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz3);

        True=findViewById(R.id.Q3True);
        False=findViewById(R.id.Q3False);
        next=findViewById(R.id.NextQ3);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Quiz3.this, Quiz4.class);
                if(True.isChecked()){
                    intent.putExtra("Q3Answer",getIntent().getIntExtra("Q2Answer",-1)+1);
                    startActivity(intent);
                    finish();
                }
                else if(False.isChecked()){
                    intent.putExtra("Q3Answer",getIntent().getIntExtra("Q2Answer",-1));
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}