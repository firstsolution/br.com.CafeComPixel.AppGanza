package br.com.cafecompixel.appganza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;


/**
 * Created by Marcos on 04/12/15.
 */
public class BabyGanzaFragment  extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ganza_baby, container, false);
    }


}
