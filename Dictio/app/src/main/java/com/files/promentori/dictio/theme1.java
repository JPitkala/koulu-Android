package com.files.promentori.dictio;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class theme1 extends AppCompatActivity {

    TextToSpeech t;
    Button buttonSay;
    Button buttonChoose;
    TextView text;
    Random random = new Random();
    TextView txtSpeechInput;
    Button btnSpeak;
    RelativeLayout layout;
    ArrayList<String> words;
    TextView Score;
    TextView MaxScore;
    int wordcount;
    int intscore;
    final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        buttonSay = (Button) findViewById(R.id.say);
        buttonChoose = (Button) findViewById(R.id.choose);
        text = (TextView) findViewById(R.id.textToSpeak);
        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        btnSpeak = (Button) findViewById(R.id.btnSpeak);
        layout = (RelativeLayout) findViewById(R.id.layout1);
        words = new ArrayList<>();
        Score = (TextView) findViewById(R.id.score);
        MaxScore = (TextView) findViewById(R.id.maxscore);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new         Intent(getApplicationContext(),selectExerciseActivity.class));
            }
        });

        String[] tempList = getResources().getStringArray(R.array.testwords);
        wordcount = tempList.length;
        MaxScore.setText((Integer.toString(wordcount)));
        Score.setText(Integer.toString(0));
        for(String x : tempList){
            words.add(x);
        }

        btnSpeak.setEnabled(false);

        t = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t.setLanguage(Locale.UK);
                }
            }
        });

        buttonSay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = text.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT);
                t.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randselect = random.nextInt((words.size()));
                String selected = words.get(randselect);
                text.setText(selected);
                btnSpeak.setEnabled(true);

            }
        });

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
    }

    public void checkInput(){
        String input = txtSpeechInput.getText().toString();
        String correct = text.getText().toString();
        if (correct.equalsIgnoreCase(input)){
          //  layout.setBackgroundColor(Color.GREEN);
            TextView textView = (TextView) findViewById(R.id.txtSpeechInput);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.checked, 0);
            words.remove(correct);
            intscore++;
            int result = intscore;
            Score.setText(Integer.toString(result));
        }else {
            TextView textView = (TextView) findViewById(R.id.txtSpeechInput);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.wrong, 0);
          //  layout.setBackgroundColor(Color.RED);
        }
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getAvailableLocales());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        intent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, true);
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
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String[] y = result.get(0).split(" ");
                    txtSpeechInput.setText(y[0]);
                }
                checkInput();
                break;

            }

        }
    }
}