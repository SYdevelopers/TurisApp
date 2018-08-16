package com.santiago.turisapp.interfaces;

import com.santiago.turisapp.fragments.HotelesFragment;
import com.santiago.turisapp.fragments.RestaurantesFragment;
import com.santiago.turisapp.fragments.SitiosFragment;

public interface Callbacks extends
        SitiosFragment.OnSendSitios,
        HotelesFragment.OnSendHoteles,
        RestaurantesFragment.OnSendRestaurante {




}
