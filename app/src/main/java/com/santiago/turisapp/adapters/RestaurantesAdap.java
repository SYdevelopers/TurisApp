package com.santiago.turisapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.santiago.turisapp.R;
import com.santiago.turisapp.models.Restaurantes;
import com.santiago.turisapp.models.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RestaurantesAdap extends RecyclerView.Adapter<RestaurantesAdap.ViewHolder> {
    private int layout;
    private ArrayList<Restaurantes> restaurantes;
    private onItemClick onItemClick;

    public RestaurantesAdap(int layout, ArrayList<Restaurantes> restaurantes, RestaurantesAdap.onItemClick onItemClick) {
        this.layout = layout;
        this.restaurantes = restaurantes;
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
        holder.Bind(restaurantes.get(position),onItemClick);
    }

    @Override
    public int getItemCount() {
        return restaurantes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre,desc,ubic;
        private ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.nameItemList);

            if (Utils.visualizacion== Utils.LIST) {
                desc=itemView.findViewById(R.id.descItemList);
            }
            ubic=itemView.findViewById(R.id.ubicItemList);
            img=itemView.findViewById(R.id.imageItemList);
        }

        public void Bind(final Restaurantes restaurantes, final onItemClick onItemClick) {
            nombre.setText(restaurantes.getNombre());
            if (Utils.visualizacion==Utils.LIST) {
                desc.setText(restaurantes.getDescripsion());
            }
            ubic.setText(restaurantes.getUbicacon());
            Picasso.get().load(restaurantes.getImagen()).error(R.drawable.error).placeholder(R.drawable.loading).fit().into(img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.itemClick(restaurantes,getAdapterPosition());
                }
            });
        }
    }

    public interface onItemClick{
        void itemClick(Restaurantes restaurantes, int position);
    }
}
