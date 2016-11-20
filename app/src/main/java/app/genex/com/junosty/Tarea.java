package app.genex.com.junosty;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Tarea extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseReference databaseReference;

    private FirebaseAuth firebaseAuth;
    private EditText tareaNombre;
    private Button agregar;
    private EditText tareaNombre2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button buttonagregar = (Button) findViewById(R.id.agregartarea);

        buttonagregar.setOnClickListener(this);
        tareaNombre = (EditText) findViewById(R.id.tarea);
        tareaNombre2 = (EditText) findViewById(R.id.tarea);

        agregar = (Button) findViewById(R.id.agregar);

        agregar.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View view) {


                        guardarTarea();

            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        firebaseAuth = FirebaseAuth.getInstance();




    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<ListaTareas, TareasViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ListaTareas, TareasViewHolder>(
                ListaTareas.class, R.layout.listatareas, TareasViewHolder.class, databaseReference

        ) {
            @Override
            protected void populateViewHolder(TareasViewHolder viewHolder, ListaTareas model, int position) {
                viewHolder.setTitle(model.getNombre());
                viewHolder.setDesc(model.getBoleta());
                viewHolder.setDesc2(model.getTarea1());


            }
        };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }


    @Override
    public void onClick(View view) {
        final  EditText taskEditText = new EditText(this);
        final String tarea = taskEditText.getText().toString().trim();


        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Añade una tarea")
                .setMessage("¿Que quieres hacer?")
                .setView(taskEditText)
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        firebaseAuth.getCurrentUser();
                        if(!TextUtils.isEmpty(tarea)){


                            String user_id = firebaseAuth.getCurrentUser().getUid();

                            DatabaseReference currend_user_bd = databaseReference.child(user_id);



                            currend_user_bd.child("Titulo").setValue(tarea);


                        }





                    }

                    })
                .create();
        dialog.show();
    }

    private void guardarTarea() {

        final String tarea_nombre = tareaNombre.getText().toString().trim();


        String user_id = firebaseAuth.getCurrentUser().getUid();

        DatabaseReference currend_user_bd = databaseReference.child(user_id);



        currend_user_bd.child("Tarea").setValue(tarea_nombre);





    }


    public static class TareasViewHolder extends RecyclerView.ViewHolder {
        View mView;


        public TareasViewHolder(View itemView) {
            super(itemView);

            mView = itemView;


        }

        public void setTitle(String title) {

            TextView nom_desc = (TextView) mView.findViewById(R.id.NombreTarea);
            nom_desc.setText(title);


        }

        public void setDesc(String desc) {
            TextView desc_tarea = (TextView) mView.findViewById(R.id.descripcionTarea);
            desc_tarea.setText(desc);





        }

        public void setDesc2 (String desc2){
            TextView desc_tarea2 = (TextView) mView.findViewById(R.id.descripcionTarea1);
            desc_tarea2.setText(desc2);

        }


    }





}
