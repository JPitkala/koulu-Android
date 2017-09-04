package com.example.a1500889.dictio;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class theme1 extends AppCompatActivity {

    TextToSpeech t;
    Button  buttonSay;
    Button  buttonChoose;
    TextView text;
    Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        buttonSay=(Button)findViewById(R.id.say);
        buttonChoose = (Button)findViewById(R.id.choose);
        text = (TextView)findViewById(R.id.textToSpeak);

        t = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t.setLanguage(Locale.UK);
                }
            }
        });

        buttonSay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = text.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT);
                t.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] TestWords = getResources().getStringArray(R.array.testwords);
                int randselect = random.nextInt((TestWords.length - 0));
                String selected = TestWords[randselect];
                text.setText(selected);

            }
        });
    }



    public void onPause(){
        if(t !=null){
            t.stop();
            t.shutdown();
        }
        super.onPause();
    }


}
