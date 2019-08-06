package com.example.cursofirebasecf.Ejemplo3_RecyclerViewCRUD;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cursofirebasecf.R;

import java.util.List;

public class GrupoAdapter extends RecyclerView.Adapter<GrupoAdapter.ViewHolder>{
    private List<Grupo> grupos;
    private Context context;

    public GrupoAdapter(List<Grupo> grupos, Context context){
        this.grupos = grupos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.idtxt.setText(String.valueOf(grupos.get(position).getId()));
        viewHolder.nametxt.setText(grupos.get(position).getGroupname());
    }

    @Override
    public int getItemCount() {
        return grupos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView idtxt, nametxt;
        public ImageView imageViewTrash;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idtxt = itemView.findViewById(R.id.idGrupo);
            nametxt = itemView.findViewById(R.id.nameGrupo);
            imageViewTrash = itemView.findViewById(R.id.btnTrash);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
