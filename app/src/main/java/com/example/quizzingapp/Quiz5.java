package com.example.quizzingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Quiz5 extends AppCompatActivity {
    RadioButton True,False;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz5);

        True=findViewById(R.id.Q5True);
        False=findViewById(R.id.Q5False);
        next=findViewById(R.id.NextQ5);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Quiz5.this, Score.class);
                if(True.isChecked()){
                    intent.putExtra("Q5Answer",getIntent().getIntExtra("Q4Answer",-1)+1);
                    startActivity(intent);
                    finish();
                }
                else if(False.isChecked()){
                    intent.putExtra("Q5Answer",getIntent().getIntExtra("Q4Answer",-1));
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}