package com.example.a1500889.dictio;

/**
 * Created by a1500908 on 6.9.2017.
 */

import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class theme3 extends AppCompatActivity {

    TextToSpeech t;
    Button  buttonSay;
    Button  buttonChoose;
    TextView text;
    Random random = new Random();
    TextView txtSpeechInput;
    Button btnSpeak;
    RelativeLayout layout;
    int convoStep;
    final int REQ_CODE_SPEECH_INPUT = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme3);
        buttonSay=(Button)findViewById(R.id.start);
        buttonChoose = (Button)findViewById(R.id.choose);
        text = (TextView)findViewById(R.id.textToSpeak);
        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        btnSpeak = (Button) findViewById(R.id.btnSpeak);
        layout = (RelativeLayout) findViewById(R.id.layout3);
        convoStep = 0;


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new         Intent(getApplicationContext(),selectThemeActivity.class));
            }
        });

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
                conversation();
            }
        });



        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
    }



    public void checkInput() {
        String[] ConvoCorrect = getResources().getStringArray(R.array.testConvoCorrect);
        String input = txtSpeechInput.getText().toString();
        String correct = ConvoCorrect[convoStep];
        if (correct.equalsIgnoreCase(input)) {
            convoStep = convoStep + 1;
            if (convoStep == getResources().getStringArray(R.array.testConvo).length){
                layout.setBackgroundColor(Color.GREEN);
            }else {
                conversation();
            }
        } else {
            String toSpeak = "Sorry i do not understand";
            Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT);
            t.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public void conversation(){
        String[] Convo = getResources().getStringArray(R.array.testConvo);
        String selected = Convo[convoStep];
        text.setText(selected);
        String toSpeak = text.getText().toString();
        Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT);
        t.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }


    }
    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                }
                checkInput();
                break;
            }

        }
    }

    }




