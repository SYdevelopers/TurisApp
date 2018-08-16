package com.santiago.turisapp.fragments;

import android.content.Context;
import android.content.Intent;
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
import com.santiago.turisapp.activities.MapsActivity;
import com.santiago.turisapp.adapters.HotelsAdapter;
import com.santiago.turisapp.models.Hoteles;
import com.santiago.turisapp.models.Utils;
import java.util.ArrayList;

public class HotelesFragment extends Fragment implements HotelsAdapter.onItemClick, View.OnClickListener {

    private OnSendHoteles mListener;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private HotelsAdapter adapter;
    private ArrayList<Hoteles> hoteles;
    private FloatingActionButton fab;

    public HotelesFragment() {
        // Required empty public constructor
    }

    public static HotelesFragment newInstance(String param1, String param2) {
        HotelesFragment fragment = new HotelesFragment();

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

        View view=inflater.inflate(R.layout.fragment_hoteles, container, false);
        getActivity().setTitle("Hoteles");
        recyclerView=view.findViewById(R.id.recyclerHotels);
        fab=view.findViewById(R.id.fabHotels);
        hoteles=getAllHotels();
        manager=new LinearLayoutManager(getContext());
        adapter=new HotelsAdapter(R.layout.content_item_list, hoteles,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy>0) {
                    fab.hide();
                } else if (dy<0){
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
        switch (item.getItemId()) {
            case R.id.grid:
                Utils.visualizacion= Utils.GRID;
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
        if (Utils.visualizacion == Utils.GRID) {
            adapter = new HotelsAdapter(R.layout.content_item_grid, hoteles, this);
            manager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
            Utils.itemGrid.setVisible(false);
            Utils.itemlist.setVisible(true);
        } else if (Utils.visualizacion == Utils.LIST) {
            adapter = new HotelsAdapter(R.layout.content_item_list, hoteles, this);
            manager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
            Utils.itemGrid.setVisible(true);
            Utils.itemlist.setVisible(false);

        }
    }

    private ArrayList<Hoteles> getAllHotels() {
        return new ArrayList<Hoteles>(){{
            add(new Hoteles("Las Camelias",getString(R.string.camelias),"QUINDIO",R.drawable.camelias));
            add(new Hoteles("Las Camelias",getString(R.string.camelias),"QUINDIO",R.drawable.camelias));
            add(new Hoteles("Allure Café Mocawa",getString(R.string.camelias),"QUINDIO",R.drawable.mocawa));
            add(new Hoteles("Allure Café Mocawa",getString(R.string.camelias),"QUINDIO",R.drawable.mocawa));
            add(new Hoteles("Las Camelias",getString(R.string.camelias),"QUINDIO",R.drawable.camelias));
            add(new Hoteles("Las Camelias",getString(R.string.camelias),"QUINDIO",R.drawable.camelias));
            add(new Hoteles("Allure Café Mocawa",getString(R.string.camelias),"QUINDIO",R.drawable.mocawa));
            add(new Hoteles("Allure Café Mocawa",getString(R.string.camelias),"QUINDIO",R.drawable.mocawa));
            add(new Hoteles("Las Camelias",getString(R.string.camelias),"QUINDIO",R.drawable.camelias));
            add(new Hoteles("Las Camelias",getString(R.string.camelias),"QUINDIO",R.drawable.camelias));
            add(new Hoteles("Allure Café Mocawa",getString(R.string.camelias),"QUINDIO",R.drawable.mocawa));
            add(new Hoteles("Allure Café Mocawa",getString(R.string.camelias),"QUINDIO",R.drawable.mocawa));
            add(new Hoteles("Las Camelias",getString(R.string.camelias),"QUINDIO",R.drawable.camelias));
            add(new Hoteles("Las Camelias",getString(R.string.camelias),"QUINDIO",R.drawable.camelias));
            add(new Hoteles("Allure Café Mocawa",getString(R.string.camelias),"QUINDIO",R.drawable.mocawa));
            add(new Hoteles("Allure Café Mocawa",getString(R.string.camelias),"QUINDIO",R.drawable.mocawa));
        }};
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSendHoteles) {
            mListener = (OnSendHoteles) context;
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
    public void itemClick(Hoteles hoteles, int position) {
        mListener.sendHotel(this.hoteles.get(position));

    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getContext(), MapsActivity.class));
    }


    public interface OnSendHoteles {

        void sendHotel(Hoteles hotel);
    }
}
