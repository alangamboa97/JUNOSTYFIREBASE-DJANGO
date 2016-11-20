package app.genex.com.junosty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {
    Button buttonregistrarse;
    Button buttoniniciarsesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       getSupportActionBar().hide();
        setContentView(R.layout.activity_intro);


        buttoniniciarsesion = (Button) findViewById(R.id.sesion);
        buttonregistrarse = (Button) findViewById(R.id.registrarse);

        buttonregistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent1);
            }
        });

        buttoniniciarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(IntroActivity.this, SingInActivity.class);
                startActivity(intent2);

            }
        });


    }
}
