package app.genex.com.junosty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.genex.com.junosty.ConsumoDeApi.Adapters.ListaUsuarioAdapter;
import app.genex.com.junosty.ConsumoDeApi.ApiService.ApiService;
import app.genex.com.junosty.ConsumoDeApi.Modelos.Usuario;
import app.genex.com.junosty.ConsumoDeApi.Modelos.UsuarioRespuesta;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static app.genex.com.junosty.R.id.textView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaselistener;

    private DatabaseReference mDataUsers;
    private TextView nombre;
    private String userName;
    ImageView imageView;

    private Retrofit retrofit;
    private static final String TAG = "Usuario";

    private RecyclerView recyclerView;
    private ListaUsuarioAdapter listaUsuarioAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = (TextView) findViewById(R.id.editNombre);

        imageView = (ImageView) this.findViewById(R.id.imageView);
        Bitmap bitmap = getIntent().getParcelableExtra("pic");
        imageView.setImageBitmap(bitmap);
        TextView nombre = (TextView) findViewById(R.id.editNombre);

        /*


        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listaUsuarioAdapter);
        */


        retrofit = new Retrofit.Builder()
                .baseUrl("http://52.39.36.176:8000/apiv1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obtenerDatos();


        firebaseAuth = FirebaseAuth.getInstance();
        mDataUsers = FirebaseDatabase.getInstance().getReference().child("Users");
        firebaselistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() == null) {
                    Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                    startActivity(intent);


                } else {

                }


            }
        };


        mDataUsers.keepSynced(true);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        firebaseAuth.addAuthStateListener(firebaselistener);


    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<PerfilHelper, ExamenesViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PerfilHelper, ExamenesViewHolder>(
                PerfilHelper.class, R.layout.activity_main, ExamenesViewHolder.class, mDataUsers

        ) {
            @Override
            protected void populateViewHolder(ExamenesViewHolder viewHolder, PerfilHelper model, int position) {
                viewHolder.setNombre(model.getNombre());
                //viewHolder.(model.getBoleta());
                //viewHolder.setDesc2(model.getTarea1());


            }
        };

        //mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            //super.onBackPressed();


        }
        moveTaskToBack(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_logout) {

            logout();
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {

        firebaseAuth.signOut();


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(this, Tarea.class);
            startActivity(intent);

            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent2 = new Intent(MainActivity.this, Horarios.class);
            startActivity(intent2);

        } else if (id == R.id.nav_slideshow) {
            Intent intent3 = new Intent(this, Examenes.class);
            startActivity(intent3);

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }


    public class ExamenesViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public ExamenesViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setNombre(String nombre) {
            TextView porf_username = (TextView) findViewById(R.id.editNombre);
            porf_username.setText(nombre);

        }


    }

    private void getStudentDetails() {

    }

    private void obtenerDatos() {

        ApiService service = retrofit.create(ApiService.class);
        retrofit2.Call<List<UsuarioRespuesta>> usuarioRespuestaCall = service.obtenerListaUsuario();

        usuarioRespuestaCall.enqueue(new Callback<List<UsuarioRespuesta>>() {
            @Override
            public void onResponse(retrofit2.Call<List<UsuarioRespuesta>> call, Response<List<UsuarioRespuesta>> response) {

            }
                                             /*if (response.isSuccessful()) {

                                                 ArrayList<UsuarioRespuesta> usuarioRespuesta = (ArrayList<UsuarioRespuesta>) response.body();
                                                 Usuario usuario = usuarioRespuesta.get(0).getUser();
                                                 listaUsuarioAdapter.adicionarlsitaUsario(usuarioRespuesta);
                                                 //listaUsuarioAdapter.adicionarlsitaUsario(listausuario);

                                                 for (int i =0; i<listausuario.size(); i++){
                                                     Usuario u = listausuario.get(i);
                                                     Log.i(TAG, "Usuairo:"+ u.getUsername());

                                              //  Log.i(TAG, "Usuario: " + usuario.getUsername());


                                             } else {
                                                 Log.e(TAG, "onResponse : " + response.errorBody());

                                             }

                                         }
                                         */

                                         @Override
                                         public void onFailure(retrofit2.Call<List<UsuarioRespuesta>> call, Throwable t) {
                                             Log.e(TAG, "onFailure : " + t.getMessage());
                                         }
                                     }
        );


    }


    public void setUserName() {

        TextView nombre = (TextView) findViewById(R.id.editNombre);
        nombre.setText((CharSequence) nombre);
    }


}







