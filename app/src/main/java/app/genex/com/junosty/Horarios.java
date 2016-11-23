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

public class Horarios extends AppCompatActivity {

    private EditText examen;
    private Button buttonagregex;
    private DatabaseReference database;
    private FirebaseAuth firebaseauth;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_materias);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        examen = (EditText) findViewById(R.id.agregarMateria);
        buttonagregex = (Button) findViewById(R.id.btnagregarMateria);



        database = FirebaseDatabase.getInstance().getReference().child("Users");
        firebaseauth = FirebaseAuth.getInstance();


        buttonagregex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                guardarMateria();

            }
        });

    }

    private void guardarMateria() {

        final String materia_nombre = examen.getText().toString().trim();


        String user_id = firebaseauth.getCurrentUser().getUid();

        DatabaseReference currend_user_bd = database.child(user_id);


        currend_user_bd.child("Materia").setValue(materia_nombre);


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<HorariosModelo, HorariosViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<HorariosModelo, HorariosViewHolder>(
                HorariosModelo.class, R.layout.listamaterias, HorariosViewHolder.class, database

        ) {
            @Override
            protected void populateViewHolder(HorariosViewHolder viewHolder, HorariosModelo model, int position) {
                viewHolder.setMateria(model.getMateria());
            }


        };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }


    public static class HorariosViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public HorariosViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setMateria(String Materia)

        {

            TextView materia = (TextView) mView.findViewById(R.id.NombreMateria);
            materia.setText(Materia);
        }
    }


}

