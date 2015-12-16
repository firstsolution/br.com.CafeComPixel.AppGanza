package br.com.cafecompixel.appganza.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.cafecompixel.appganza.R;
import br.com.cafecompixel.appganza.activity.GanzaActivity;


/**
 * Created by Marcos on 04/12/15.
 */
public class BabyGanzaFragment  extends Fragment {

    public static final String EGG_BLACk = "egg_black";
    public static final String EGG_BLUE = "egg_blue";
    public static final String EGG_GREEN = "egg_green";
    public static final String EGG_ORANGE = "egg_orange";
    public static final String EGG_PURPLE = "egg_purple";
    public static final String EGG_PINK = "egg_pink";
    public static final String EGG_YELLOW = "egg_yellow";

    SharedPreferences settings;
    View layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.setHasOptionsMenu(true);

        settings =  getActivity(). getSharedPreferences(GanzaActivity.PREFS_NAME, 0);
        layout = inflater.inflate(R.layout.fragment_ganza_baby, container, false);

        return layout;
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_ganza, menu);
    }

    @Override
    public void onStart() {
        super.onStart();

        String color = settings.getString(ConfigGanzaFragment.EGG_COLOR, EGG_PURPLE);

        switch(color) {

            case EGG_BLACk:
                ImageView img1= (ImageView) layout.findViewById(R.id.eggImg);
                img1.setImageResource(R.drawable.egg_black);
                break;

            case EGG_PINK:
                ImageView img7= (ImageView) layout.findViewById(R.id.eggImg);
                img7.setImageResource(R.drawable.egg_pink);
                break;

            case EGG_ORANGE:
                ImageView img3= (ImageView) layout.findViewById(R.id.eggImg);
                img3.setImageResource(R.drawable.egg_orange);
                break;
            case EGG_PURPLE:
                ImageView img4= (ImageView) layout.findViewById(R.id.eggImg);
                img4.setImageResource(R.drawable.egg_purple);
                break;

            case EGG_BLUE:
                ImageView img2= (ImageView) layout.findViewById(R.id.eggImg);
                img2.setImageResource(R.drawable.egg_blue);
                break;

            case EGG_GREEN:
                ImageView img5= (ImageView) layout.findViewById(R.id.eggImg);
                img5.setImageResource(R.drawable.egg_gree);
                break;

            case EGG_YELLOW:
                ImageView img6= (ImageView) layout.findViewById(R.id.eggImg);
                img6.setImageResource(R.drawable.egg_yellow);
                break;


        }

        Toast.makeText(getContext(), color, Toast.LENGTH_SHORT).show();
    }

}
