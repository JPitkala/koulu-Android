package kansio.kansio.a1500889.dictio;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Joonas on 27.9.2017.
 */

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;

    //Haetaan activity_login filusta tiedot ja verrataan kovakoodattuihin arvoihin,
    //Jos väärä: Herjaa tunnuksista, Jos oikein: heitetään Intentin avulla eteenpäin.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = username.getText().toString().trim();
                String passWord = password.getText().toString().trim();

                if(userName.equals("admin") && passWord.equals("passu")) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else {
                    Toast.makeText(LoginActivity.this, "Incorrect username / password", Toast.LENGTH_LONG).show();
                }

            }

        });

    }

}
