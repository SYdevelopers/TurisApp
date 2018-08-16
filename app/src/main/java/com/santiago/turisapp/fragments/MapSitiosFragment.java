package com.santiago.turisapp.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.santiago.turisapp.R;

public class MapSitiosFragment extends Fragment implements OnMapReadyCallback {

    private View vista;
    private GoogleMap gMap;
    private MapView mapView;

    public MapSitiosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         vista=inflater.inflate(R.layout.fragment_map_sitios, container, false);
        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView=vista.findViewById(R.id.mapView);
        if (mapView!=null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap=googleMap;

        LatLng medellin=new LatLng(6.244203,-75.58121189999997);
        gMap.addMarker(new MarkerOptions().position(medellin).title("medellin"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(medellin));


    }
}
