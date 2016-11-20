package app.genex.com.junosty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Examenes extends AppCompatActivity {

    private EditText examen;
    private Button buttonagregex;
    private DatabaseReference database;
    private FirebaseAuth firebaseauth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examenes);

        examen= (EditText) findViewById(R.id.agregarExamen);
        buttonagregex = (Button) findViewById(R.id.btnagregarexa);

        database = FirebaseDatabase.getInstance().getReference().child("Users");
        firebaseauth = FirebaseAuth.getInstance();





        buttonagregex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                guardarExamen();

            }
        });

    }

    private void guardarExamen() {

        final String examen_nombre = examen.getText().toString().trim();


        String user_id = firebaseauth.getCurrentUser().getUid();

        DatabaseReference currend_user_bd = database.child(user_id);



        currend_user_bd.child("Examen").setValue(examen_nombre);









    }
}
