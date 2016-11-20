package app.genex.com.junosty.ConsumoDeApi.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import app.genex.com.junosty.ConsumoDeApi.Modelos.Usuario;
import app.genex.com.junosty.ConsumoDeApi.Modelos.UsuarioRespuesta;
import app.genex.com.junosty.R;

/**
 * Created by Invitado1 on 20/11/2016.
 */

public class ListaUsuarioAdapter extends RecyclerView.Adapter<ListaUsuarioAdapter.ViewHolder>{

    private ArrayList<UsuarioRespuesta> dataset;
    private ArrayList<Usuario> usuario;

    public ListaUsuarioAdapter(){
        dataset = new ArrayList<>();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main,parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Usuario u = dataset.get(position).getUser();
        holder.nombreTextView.setText(u.getUsername());


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarlsitaUsario(ArrayList<UsuarioRespuesta> listausuario) {
        dataset.addAll(listausuario);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nombreTextView = (TextView) itemView.findViewById(R.id.editNombre);



        }
    }
}