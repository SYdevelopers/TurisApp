package com.santiago.turisapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.santiago.turisapp.R;
import com.santiago.turisapp.models.Hoteles;
import com.santiago.turisapp.models.Restaurantes;
import com.santiago.turisapp.models.Sitios;
import com.santiago.turisapp.models.Utils;
import com.squareup.picasso.Picasso;


public class DetailsFragment extends Fragment {

    private ImageView imagen;
    private TextView nombre,descripcion;
    private Sitios sitios;
    private Hoteles hoteles;
    private Restaurantes restaurantes;



    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_details, container, false);
        imagen=view.findViewById(R.id.imageDetail);
        nombre=view.findViewById(R.id.nameDetail);
        descripcion=view.findViewById(R.id.descDetail);

        Bundle bundle=getArguments();
        if (null!=bundle){
            switch (bundle.getInt("type")){
                case Utils.SITE:
                    sitios= (Sitios) bundle.getSerializable("obj");
                    asignarSitio(sitios);
                break;
                case Utils.HOTEL:
                    hoteles= (Hoteles) bundle.getSerializable("obj");
                    asignarHotel(hoteles);
                    break;
                case Utils.RESTAURANT:
                    restaurantes= (Restaurantes) bundle.getSerializable("obj");
                    asignarRestaurante(restaurantes);


            }



        }

        return view;
    }

    private void asignarRestaurante(Restaurantes restaurantes) {
        getActivity().setTitle(restaurantes.getNombre());
        Picasso.get().load(restaurantes.getImagen()).error(R.drawable.error).placeholder(R.drawable.loading).fit().into(imagen);
        nombre.setText(restaurantes.getNombre());
        descripcion.setText(restaurantes.getDescripsion());

    }

    private void asignarHotel(Hoteles hoteles) {
        getActivity().setTitle(hoteles.getNombre());
        Picasso.get().load(hoteles.getImagen()).error(R.drawable.error).placeholder(R.drawable.loading).fit().into(imagen);
        nombre.setText(hoteles.getNombre());
        descripcion.setText(hoteles.getDescripcion());
    }

    private void asignarSitio(Sitios sitios) {
        getActivity().setTitle(sitios.getNombre());
        Picasso.get().load(sitios.getImagen()).error(R.drawable.error).placeholder(R.drawable.loading).fit().into(imagen);
        nombre.setText(sitios.getNombre());
        descripcion.setText(sitios.getDescripsion());
    }

}
