package com.santiago.turisapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.santiago.turisapp.R;
import com.santiago.turisapp.adapters.SitiosAdapter;
import com.santiago.turisapp.models.Sitios;
import com.santiago.turisapp.models.Utils;


import java.util.ArrayList;

public class SitiosFragment extends Fragment implements SitiosAdapter.onItemClick, View.OnClickListener {

    private OnSendSitios mListener;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private SitiosAdapter adapter;
    private ArrayList<Sitios> sitios;
    private FloatingActionButton fab;





    public SitiosFragment() {
        // Required empty public constructor
    }

    public static SitiosFragment newInstance(String param1, String param2) {
        SitiosFragment fragment = new SitiosFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_sitios, container, false);

        getActivity().setTitle("Sitios");
        sitios=getAllSitios();
        recyclerView=view.findViewById(R.id.recyclerSitios);
        fab=view.findViewById(R.id.fabSites);
        manager=new LinearLayoutManager(getContext());
        adapter=new SitiosAdapter(R.layout.content_item_list, sitios,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy>0){
                    fab.hide();
                }if (dy<0){
                    fab.show();
                }
            }
        });

        fab.setOnClickListener(this);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (Utils.optMenu){
            inflater.inflate(R.menu.inicio,menu);
            Utils.itemGrid=menu.findItem(R.id.grid);
            Utils.itemlist=menu.findItem(R.id.list);
            cambiarVista();
            Utils.optMenu=false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.grid:
                Utils.visualizacion=Utils.GRID;
                cambiarVista();
                return true;
            case R.id.list:
                Utils.visualizacion=Utils.LIST;
                cambiarVista();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }



    }

    private void cambiarVista() {
            if (Utils.visualizacion== Utils.GRID){
                adapter=new SitiosAdapter(R.layout.content_item_grid,sitios,this);
                manager=new GridLayoutManager(getContext(),2);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
                Utils.itemGrid.setVisible(false);
                Utils.itemlist.setVisible(true);
            }else if (Utils.visualizacion==Utils.LIST){
                adapter=new SitiosAdapter(R.layout.content_item_list,sitios,this);
                manager=new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
                Utils.itemGrid.setVisible(true);
                Utils.itemlist.setVisible(false);

            }

    }

    private ArrayList<Sitios> getAllSitios() {
        return new ArrayList<Sitios>(){{
            add(new Sitios("Valle de Cocora",getString(R.string.cocora),"QUINDIO",R.drawable.cocora));
            add(new Sitios("Parque nacional del Café",getString(R.string.cafe),"QUINDIO",R.drawable.cafe));
            add(new Sitios("Parque nacional de la Cultura ",getString(R.string.cultura),"QUINDIO",R.drawable.cultura));
            add(new Sitios("Parque los Arrieros",getString(R.string.arrieros),"QUINDIO",R.drawable.arrieros));
            add(new Sitios( "Jardín Botánico Del Quindío",getString(R.string.botanico),"QUINDIO",R.drawable.jardin));
            add(new Sitios("Nevado del Quindío",getString(R.string.nevado),"QUINDIO",R.drawable.nevado)); add(new Sitios("Valle de Cocora",getString(R.string.cocora),"QUINDIO",R.drawable.cocora));
            add(new Sitios("Parque nacional del Café",getString(R.string.cafe),"QUINDIO",R.drawable.cafe));
            add(new Sitios("Parque nacional de la Cultura ",getString(R.string.cultura),"QUINDIO",R.drawable.cultura));
            add(new Sitios("Parque los Arrieros",getString(R.string.arrieros),"QUINDIO",R.drawable.arrieros));
            add(new Sitios( "Jardín Botánico Del Quindío",getString(R.string.botanico),"QUINDIO",R.drawable.jardin));
            add(new Sitios("Nevado del Quindío",getString(R.string.nevado),"QUINDIO",R.drawable.nevado)); add(new Sitios("Valle de Cocora",getString(R.string.cocora),"QUINDIO",R.drawable.cocora));
            add(new Sitios("Parque nacional del Café",getString(R.string.cafe),"QUINDIO",R.drawable.cafe));
            add(new Sitios("Parque nacional de la Cultura ",getString(R.string.cultura),"QUINDIO",R.drawable.cultura));
            add(new Sitios("Parque los Arrieros",getString(R.string.arrieros),"QUINDIO",R.drawable.arrieros));
            add(new Sitios( "Jardín Botánico Del Quindío",getString(R.string.botanico),"QUINDIO",R.drawable.jardin));
            add(new Sitios("Nevado del Quindío",getString(R.string.nevado),"QUINDIO",R.drawable.nevado));
        }};

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSendSitios) {
            mListener = (OnSendSitios) context;

        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSendHoteles");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void itemClick(Sitios sitios, int position) {
        mListener.sendSitio(this.sitios.get(position));

    }

    @Override
    public void onClick(View view) {
       mListener.mapSitios();
    }


    public interface OnSendSitios {
        void sendSitio(Sitios sitios);
        void mapSitios();
    }


}
