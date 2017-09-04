package com.example.a1500889.dictio;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

public class theme1 extends AppCompatActivity {

    TextToSpeech t;
    Button  button;
    Text text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        button=(Button)findViewById(R.id.button);

        t = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t.setLanguage(Locale.UK);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = "Does it work";
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT);
                t.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
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
