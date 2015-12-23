package br.com.cafecompixel.appganza.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Random;

import br.com.cafecompixel.appganza.R;
import br.com.cafecompixel.appganza.activity.GanzaActivity;
import br.com.cafecompixel.appganza.util.SoundManager;


/**
 * Created by Marcos on 04/12/15.
 */
public class BabyGanzaFragment  extends Fragment {


    public static final String EGG_BLACk  = "egg_black";
    public static final String EGG_BLUE   = "egg_blue";
    public static final String EGG_GREEN  = "egg_green";
    public static final String EGG_RED    = "egg_red";
    public static final String EGG_PURPLE = "egg_purple";
    public static final String EGG_PINK   = "egg_pink";
    public static final String EGG_YELLOW = "egg_yellow";
    private static final short RANDOM_NUMBER_LIMIT = 20;

    private ImageView eggImageView;
    private short     toques = 0;
    private int       randomNumber;
    private boolean   isEggFragment;

    SharedPreferences settings;
    View layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.setHasOptionsMenu(true);

        settings =  getActivity(). getSharedPreferences(GanzaActivity.PREFS_NAME, 0);
        layout = inflater.inflate(R.layout.fragment_ganza_baby, container, false);

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle("Ganzá Baby");

        randomNumber  = getRandomNumber();
        isEggFragment = getRandomBoolean();

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

        // ImageView que ira conter a imagem do ovo
        eggImageView = (ImageView) layout.findViewById(R.id.eggImg);

        switch(color) {

            case EGG_BLACk:
                eggImageView.setImageResource(R.drawable.egg_black);
                break;

            case EGG_PINK:
                eggImageView.setImageResource(R.drawable.egg_pink);
                break;

            case EGG_RED:
                eggImageView.setImageResource(R.drawable.egg_red);
                break;

            case EGG_PURPLE:
                eggImageView.setImageResource(R.drawable.egg_purple);
                break;

            case EGG_BLUE:
                eggImageView.setImageResource(R.drawable.egg_blue);
                break;

            case EGG_GREEN:
                eggImageView.setImageResource(R.drawable.egg_gree);
                break;

            case EGG_YELLOW:
                eggImageView.setImageResource(R.drawable.egg_yellow);

        }
    }

    @Override
    public void onResume() {
        super.onResume();

        // Evento que sera disparado quando o usuario clicar na imagem do ovo
        eggImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSound(getContext());
                toques++;
                if(toques == randomNumber) {
                    irParaFragment(isEggFragment);
                }
            }
        });
    }

    private int getRandomNumber() {

        Random random = new Random();

        int randomNumber = random.nextInt(RANDOM_NUMBER_LIMIT) + 1;

        return randomNumber;
    }

    private boolean getRandomBoolean() {

        Random random = new Random();

        boolean randomBoolean = random.nextBoolean();

        return randomBoolean;
    }

    private void irParaFragment(boolean isEggFragment) {
        FragmentTransaction transaction;
        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if(isEggFragment)
            transaction.replace(R.id.fragment, new EggGanzaFragment(),null);
        else
            transaction.replace(R.id.fragment, new ChickGanzaFragment(), null);
        transaction.commit();
    }

}
