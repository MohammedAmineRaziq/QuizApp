    package com.example.quizzingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

    public class Quiz4 extends AppCompatActivity {
        RadioButton True,False;
        Button next;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_quiz4);

            True=findViewById(R.id.Q4True);
            False=findViewById(R.id.Q4False);
            next=findViewById(R.id.NextQ4);



            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Quiz4.this, Quiz5.class);
                    if(True.isChecked()){
                        intent.putExtra("Q4Answer",getIntent().getIntExtra("Q3Answer",-1)+1);
                        startActivity(intent);
                        finish();
                    }
                    else if(False.isChecked()){
                        intent.putExtra("Q4Answer",getIntent().getIntExtra("Q3Answer",-1));
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    }