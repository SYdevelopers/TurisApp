package com.santiago.turisapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
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
import com.santiago.turisapp.adapters.RestaurantesAdap;
import com.santiago.turisapp.models.Restaurantes;
import com.santiago.turisapp.models.Utils;

import java.util.ArrayList;

public class RestaurantesFragment extends Fragment implements RestaurantesAdap.onItemClick {

    private OnSendRestaurante mListener;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private RestaurantesAdap adapter;
    private ArrayList<Restaurantes> restaurantes;
    private FloatingActionButton fab;


    public RestaurantesFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static RestaurantesFragment newInstance(String param1, String param2) {
        RestaurantesFragment fragment = new RestaurantesFragment();

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

        View view= inflater.inflate(R.layout.fragment_restaurantes, container, false);
        getActivity().setTitle("Restaurantes");
        recyclerView=view.findViewById(R.id.recyclerRestaurantes);
        fab=view.findViewById(R.id.fabRestaurant);
        restaurantes=getAllRestaurants();
        manager=new LinearLayoutManager(getContext());
        adapter=new RestaurantesAdap(R.layout.content_item_list, restaurantes, new RestaurantesAdap.onItemClick() {
            @Override
            public void itemClick(Restaurantes restaurantes, int position) {

            }
        });
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy>0){
                    fab.hide();
                }else if (dy<0){
                    fab.show();
                }
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    private ArrayList<Restaurantes> getAllRestaurants() {
        return new ArrayList<Restaurantes>(){{
            add(new Restaurantes("La Fogata",getString(R.string.la_fogata),"QUINDO",R.drawable.fogata));
            add(new Restaurantes("La Fogata",getString(R.string.la_fogata),"QUINDO",R.drawable.fogata));
            add(new Restaurantes("La Fogata",getString(R.string.la_fogata),"QUINDO",R.drawable.fogata));
            add(new Restaurantes("La Fogata",getString(R.string.la_fogata),"QUINDO",R.drawable.fogata));
            add(new Restaurantes("La Fogata",getString(R.string.la_fogata),"QUINDO",R.drawable.fogata));
            add(new Restaurantes("La Fogata",getString(R.string.la_fogata),"QUINDO",R.drawable.fogata));
            add(new Restaurantes("La Fogata",getString(R.string.la_fogata),"QUINDO",R.drawable.fogata));
            add(new Restaurantes("La Fogata",getString(R.string.la_fogata),"QUINDO",R.drawable.fogata));
            add(new Restaurantes("La Fogata",getString(R.string.la_fogata),"QUINDO",R.drawable.fogata));
            add(new Restaurantes("La Fogata",getString(R.string.la_fogata),"QUINDO",R.drawable.fogata));
            add(new Restaurantes("La Fogata",getString(R.string.la_fogata),"QUINDO",R.drawable.fogata));
            add(new Restaurantes("La Fogata",getString(R.string.la_fogata),"QUINDO",R.drawable.fogata));
            add(new Restaurantes("La Fogata",getString(R.string.la_fogata),"QUINDO",R.drawable.fogata));
            add(new Restaurantes("La Fogata",getString(R.string.la_fogata),"QUINDO",R.drawable.fogata));
            add(new Restaurantes("La Fogata",getString(R.string.la_fogata),"QUINDO",R.drawable.fogata));
            add(new Restaurantes("La Fogata",getString(R.string.la_fogata),"QUINDO",R.drawable.fogata));
        }};
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
                Utils.visualizacion = Utils.GRID;
                cambiarVista();
                return true;
            case R.id.list:
                Utils.visualizacion =Utils.LIST;
                cambiarVista();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void cambiarVista() {
        if (Utils.visualizacion == Utils.GRID) {
            adapter = new RestaurantesAdap(R.layout.content_item_grid, restaurantes, this);
            manager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
            Utils.itemGrid.setVisible(false);
            Utils.itemlist.setVisible(true);
        } else if (Utils.visualizacion == Utils.LIST) {
            adapter = new RestaurantesAdap(R.layout.content_item_list, restaurantes, this);
            manager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
            Utils.itemGrid.setVisible(true);
            Utils.itemlist.setVisible(false);

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSendRestaurante) {
            mListener = (OnSendRestaurante) context;
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
    public void itemClick(Restaurantes restaurantes, int position) {
        mListener.sendRestaurante(this.restaurantes.get(position));

    }

    public interface OnSendRestaurante {

        void sendRestaurante(Restaurantes restaurantes);
    }
}
