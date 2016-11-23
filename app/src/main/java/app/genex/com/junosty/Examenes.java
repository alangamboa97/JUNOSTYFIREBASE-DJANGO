package app.genex.com.junosty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Examenes extends AppCompatActivity {

    private EditText examen;
    private Button buttonagregex;
    private DatabaseReference database;
    private FirebaseAuth firebaseauth;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examenes);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_examenes);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        examen = (EditText) findViewById(R.id.agregarExamen);
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

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<ExamenModelo, ExamenesViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ExamenModelo, ExamenesViewHolder>(
                ExamenModelo.class, R.layout.listaexamenes, ExamenesViewHolder.class, database

        ) {
            @Override
            protected void populateViewHolder(ExamenesViewHolder viewHolder, ExamenModelo model, int position) {
            viewHolder.setExamen(model.getExamen());
            }


        };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }


    public static class ExamenesViewHolder extends RecyclerView.ViewHolder {
            View mView;
        public ExamenesViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setExamen(String Examen)

        {

            TextView examen = (TextView) mView.findViewById(R.id.NombreExamen);
            examen.setText(Examen);
        }
    }


}