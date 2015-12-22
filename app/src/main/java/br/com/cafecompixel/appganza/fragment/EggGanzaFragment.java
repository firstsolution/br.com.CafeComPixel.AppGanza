package br.com.cafecompixel.appganza.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.cafecompixel.appganza.R;

/**
 * Created by Marcos on 17/12/15.
 */
public class EggGanzaFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ganza_egg, container, false);

    }
}
