package com.santiago.turisapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.santiago.turisapp.R;
import com.santiago.turisapp.models.Hoteles;
import com.santiago.turisapp.models.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.ViewHolder> {
    private int layout;
    private ArrayList<Hoteles> hoteles;
    private onItemClick onItemClick;

    public HotelsAdapter(int layout, ArrayList<Hoteles> hoteles, HotelsAdapter.onItemClick onItemClick) {
        this.layout = layout;
        this.hoteles = hoteles;
        this.onItemClick = onItemClick;
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
        holder.Bind(hoteles.get(position),onItemClick);

    }

    @Override
    public int getItemCount() {
        return hoteles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre,descripsion,ubicacion;
        private ImageView imagen;
        public ViewHolder(View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.nameItemList);
            if (Utils.visualizacion== Utils.LIST){
                descripsion=itemView.findViewById(R.id.descItemList);
            }
            ubicacion=itemView.findViewById(R.id.ubicItemList);
            imagen=itemView.findViewById(R.id.imageItemList);

        }

        public void Bind(final Hoteles hoteles, final onItemClick onItemClick) {
            nombre.setText(hoteles.getNombre());

            if (Utils.visualizacion==Utils.LIST) {
                descripsion.setText(hoteles.getDescripcion());
            }
            ubicacion.setText(hoteles.getUbicacion());
            Picasso.get().load(hoteles.getImagen()).error(R.drawable.error).placeholder(R.drawable.loading).fit().into(imagen);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.itemClick(hoteles,getAdapterPosition());
                }
            });

        }
    }

    public interface onItemClick{
        void itemClick(Hoteles hoteles, int position);
    }
}
