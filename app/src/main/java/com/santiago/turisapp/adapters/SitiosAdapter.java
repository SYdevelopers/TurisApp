package com.santiago.turisapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.santiago.turisapp.R;
import com.santiago.turisapp.models.Sitios;
import com.santiago.turisapp.models.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SitiosAdapter extends RecyclerView.Adapter<SitiosAdapter.ViewHolder> {
    private int layout;
    private ArrayList<Sitios> sitios;
    private onItemClick itemClick;

    public SitiosAdapter(int layout, ArrayList<Sitios> sitios, onItemClick itemClick) {
        this.layout = layout;
        this.sitios = sitios;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Bind(sitios.get(position),itemClick);
    }

    @Override
    public int getItemCount() {
        return sitios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre,descripcion,ubicacion;
        private ImageView imagen;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.nameItemList);
            if (Utils.visualizacion== Utils.LIST){
                descripcion=itemView.findViewById(R.id.descItemList);
            }
            ubicacion=itemView.findViewById(R.id.ubicItemList);
            imagen=itemView.findViewById(R.id.imageItemList);
        }
        public void Bind(final Sitios sitios, final onItemClick onItemClick){
            nombre.setText(sitios.getNombre());
            if (Utils.visualizacion==Utils.LIST){
                descripcion.setText(sitios.getDescripsion());
            }

            ubicacion.setText(sitios.getUbicacion());
            Picasso.get().load(sitios.getImagen()).error(R.drawable.error).placeholder(R.drawable.loading).fit().into(imagen);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.itemClick(sitios,getAdapterPosition());
                }
            });
        }
    }

    public interface onItemClick{
        void itemClick(Sitios sitios, int position);
    }
}
