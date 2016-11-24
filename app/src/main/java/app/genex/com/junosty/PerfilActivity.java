package app.genex.com.junosty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PerfilActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_perfil);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        firebaseAuth = FirebaseAuth.getInstance();






    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<PerfilHelper, PerfilViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PerfilHelper, PerfilViewHolder>(
                PerfilHelper.class, R.layout.perfil, PerfilViewHolder.class, databaseReference

        ) {
            @Override
            protected void populateViewHolder(PerfilViewHolder viewHolder, PerfilHelper model, int position) {
                viewHolder.setExamen(model.getNombre());
            }


        };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }



    public static class PerfilViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public PerfilViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setExamen(String Nombre)

        {

            TextView nombre = (TextView) mView.findViewById(R.id.NombreUsuario);
            nombre.setText(Nombre);
        }
    }
}
