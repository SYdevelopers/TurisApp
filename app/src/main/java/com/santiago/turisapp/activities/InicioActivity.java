package com.santiago.turisapp.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.santiago.turisapp.R;
import com.santiago.turisapp.fragments.DetailsFragment;
import com.santiago.turisapp.fragments.HotelesFragment;
import com.santiago.turisapp.fragments.InicioFragment;
import com.santiago.turisapp.fragments.MapSitiosFragment;
import com.santiago.turisapp.fragments.RestaurantesFragment;
import com.santiago.turisapp.fragments.SitiosFragment;
import com.santiago.turisapp.interfaces.Callbacks;
import com.santiago.turisapp.models.Hoteles;
import com.santiago.turisapp.models.Restaurantes;
import com.santiago.turisapp.models.Sitios;
import com.santiago.turisapp.models.Utils;

public class InicioActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,Callbacks {

    private Fragment fragment = new DetailsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        rotacion(savedInstanceState);

    }

    private void rotacion(Bundle savedInstanceState) {

        if (savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new InicioFragment()).commit();
            Utils.menu_item=Utils.INICIO;
        }else {
            switch (Utils.menu_item) {
                case Utils.INICIO:
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedorland, new InicioFragment()).commit();
                    Utils.menu_item = Utils.RESET;
                    break;
                case Utils.SITE:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragList, new SitiosFragment()).commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragDetalle, new DetailsFragment()).commit();
                    Utils.menu_item = Utils.RESET;

                    break;
                case Utils.HOTEL:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragList, new HotelesFragment()).commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragDetalle, new DetailsFragment()).commit();
                    Utils.menu_item = Utils.RESET;
                    break;
                case Utils.RESTAURANT:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragList, new RestaurantesFragment()).commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragDetalle, new DetailsFragment()).commit();
                    Utils.menu_item = Utils.RESET;
                    break;
            }
            Utils.optMenu=true;
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,new InicioFragment()).commit();
            Utils.menu_item=Utils.INICIO;

        } else if (id == R.id.nav_sitios) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,new SitiosFragment()).commit();
            Utils.menu_item=Utils.SITE;
            Utils.optMenu=true;

        } else if (id == R.id.nav_hoteles) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,new HotelesFragment()).commit();
            Utils.menu_item=Utils.HOTEL;
            Utils.optMenu=true;

        } else if (id == R.id.nav_restaurantes) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,new RestaurantesFragment()).commit();
            Utils.menu_item=Utils.RESTAURANT;
            Utils.optMenu=true;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void sendSitio(Sitios sitios) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("obj",sitios);
        bundle.putInt("type", Utils.SITE);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragment).addToBackStack(null).commit();

    }

    @Override
    public void mapSitios() {
        fragment=new MapSitiosFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragment).addToBackStack(null).commit();

    }

    @Override
    public void sendHotel(Hoteles hotel) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("obj",hotel);
        bundle.putInt("type",Utils.HOTEL);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragment).addToBackStack(null).commit();

    }

    @Override
    public void sendRestaurante(Restaurantes restaurantes) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("obj",restaurantes);
        bundle.putInt("type",Utils.RESTAURANT);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragment).addToBackStack(null).commit();

    }
}
