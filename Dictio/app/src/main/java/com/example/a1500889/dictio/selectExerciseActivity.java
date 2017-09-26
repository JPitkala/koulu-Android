package com.example.a1500889.dictio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class selectExerciseActivity extends AppCompatActivity {


    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_selectexercise);

        text = (TextView)findViewById(R.id.textView);


                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);

                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        startActivity(new         Intent(getApplicationContext(),MainActivity.class));
        }
        });

        }


public void gotoTheme1(View view){
        Intent intent = new Intent(this, theme1.class);
        startActivity(intent);
        }

public void gotoSpell(View view){
        Intent intent2 = new Intent(this, theme2.class);
        startActivity(intent2);
        }

public void gotoTheme3(View view){
        Intent intent3 = new Intent(this, theme3.class);
        startActivity(intent3);
        }

        }
