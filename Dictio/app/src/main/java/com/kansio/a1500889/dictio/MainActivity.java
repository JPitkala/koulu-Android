package kansio.kansio.a1500889.dictio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //final Button button1 = (Button) findViewById(R.id.button1);
        //button1.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        TextView textView1 = (TextView) findViewById(R.id.textView1);
        //        textView1.setVisibility(View.VISIBLE);
        //    }
        //});
    }

    public void gotoSelectTheme(View view){
        Intent intent = new Intent(this, selectExerciseActivity.class);
        startActivity(intent);
    }
}
