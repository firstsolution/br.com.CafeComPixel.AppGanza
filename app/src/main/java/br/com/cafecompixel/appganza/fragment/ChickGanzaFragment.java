package br.com.cafecompixel.appganza.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.cafecompixel.appganza.R;

/**
 * Created by Marcos on 17/12/15.
 */
public class ChickGanzaFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        return inflater.inflate(R.layout.fragment_ganza_chick, container, false);

    }
}
