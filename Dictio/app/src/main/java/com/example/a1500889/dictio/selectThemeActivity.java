package com.example.a1500889.dictio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class selectThemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecttheme);

    }

    public void gotoTheme1(View view){
        Intent intent = new Intent(this, theme1.class);
        startActivity(intent);
    }

    public void gotoSpell(View view){
        Intent intent2 = new Intent(this, theme2.class);
        startActivity(intent2);
    }

}
