package com.files.promentori.dictio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class selectExerciseActivity extends AppCompatActivity {


    TextView text;
    RadioButton radioButton1;
    RadioButton radioButton2;
    Button btn;
    Button btn2;
    Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectexercise);

        text = (TextView) findViewById(R.id.textView);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        btn = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        btn.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);

        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                radioButton2.setChecked(false);
                radioButton1.setChecked(true);
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                radioButton1.setChecked(false);
                radioButton2.setChecked(true);
            }
        });
    }

    public Bundle bundlewords(){
        Bundle array = new Bundle();
        if (radioButton1.isChecked()) {
            array.putStringArray("array", getResources().getStringArray(R.array.testwords));
        }
        if (radioButton2.isChecked()){
            array.putStringArray("array", getResources().getStringArray(R.array.testwords2));
        }
        return  array;
    }

    public void gotoTheme1(View view) {
        Intent intent = new Intent(this, theme1.class);
        intent.putExtras(bundlewords());
        startActivity(intent);
    }

    public void gotoSpell(View view) {
        Intent intent2 = new Intent(this, theme2.class);
        startActivity(intent2);
    }

    public void gotoTheme3(View view) {
        Intent intent3 = new Intent(this, theme3.class);
        startActivity(intent3);
    }

}
