package br.com.cafecompixel.appganza.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.cafecompixel.appganza.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class GanzaFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ganza, container, false);
    }
}
