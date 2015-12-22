package br.com.cafecompixel.appganza.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

import br.com.cafecompixel.appganza.R;
import br.com.cafecompixel.appganza.activity.GanzaActivity;

/**
 * Created by Marcos on 11/12/15.
 */
public class ConfigGanzaFragment extends Fragment {

    public final static String BABY_MODE = "baby_mode";
    public final static String EGG_COLOR = "egg_color";

    SharedPreferences settings;
    View layout;

    CheckBox eggBlack;
    CheckBox eggPurple;
    CheckBox eggRed;
    CheckBox eggPink;
    CheckBox eggGreen;
    CheckBox eggBlue;
    CheckBox eggYellow;


    int lastCheckboxId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        layout = inflater.inflate(R.layout.fragment_ganza_config, container, false);

        settings = this.getActivity().getSharedPreferences(GanzaActivity.PREFS_NAME, Context.MODE_PRIVATE);

        setupBabyModeSwitch();

        setupBabyModeColors();

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Configurações");

        return layout;
    }


    private void setupBabyModeSwitch() {

        boolean isBabyMode = settings.getBoolean(BABY_MODE, false);

        Switch babyModeSwitch = (Switch) layout.findViewById(R.id.action_modo_baby);

        babyModeSwitch.setChecked(isBabyMode);

        babyModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                setBabyMode(isChecked);
            }
        });

    }

    private void setupBabyModeColors() {

        eggBlack = (CheckBox) layout.findViewById(R.id.egg_black);
        eggPink = (CheckBox) layout.findViewById(R.id.egg_pink);
        eggRed = (CheckBox) layout.findViewById(R.id.egg_red);
        eggPurple = (CheckBox) layout.findViewById(R.id.egg_purple);
        eggBlue = (CheckBox) layout.findViewById(R.id.egg_blue);
        eggGreen = (CheckBox) layout.findViewById(R.id.egg_green);
        eggYellow = (CheckBox) layout.findViewById(R.id.egg_yellow);


        CompoundButton.OnCheckedChangeListener onChange = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {

                CheckBox checkbox = (CheckBox) view;

                if(!checkbox.isChecked()) return;
                if(lastCheckboxId == checkbox.getId()) return;

                eggBlack.setChecked(false);
                eggPink.setChecked(false);
                eggRed.setChecked(false);
                eggPurple.setChecked(false);
                eggBlue.setChecked(false);
                eggGreen.setChecked(false);
                eggYellow.setChecked(false);
                lastCheckboxId = checkbox.getId();
                checkbox.setChecked(true);
                setEggColor(checkbox);



            }
        };


        eggBlack.setOnCheckedChangeListener(onChange);
        eggPink.setOnCheckedChangeListener(onChange);
        eggRed.setOnCheckedChangeListener(onChange);
        eggPurple.setOnCheckedChangeListener(onChange);
        eggBlue.setOnCheckedChangeListener(onChange);
        eggGreen.setOnCheckedChangeListener(onChange);
        eggYellow.setOnCheckedChangeListener(onChange);
    }

    public void setEggColor(View item){

        String color;

        switch (item.getId())
        {

            case R.id.egg_black:
                color = BabyGanzaFragment.EGG_BLACk;
                break;

            case R.id.egg_pink:
                color = BabyGanzaFragment.EGG_PINK;
                break;

            case R.id.egg_red:
                color = BabyGanzaFragment.EGG_RED;
                break;

            case R.id.egg_purple:
                color = BabyGanzaFragment.EGG_PURPLE;
                break;

            case R.id.egg_blue:
                color = BabyGanzaFragment.EGG_BLUE;
                break;

            case R.id.egg_green:
                color = BabyGanzaFragment.EGG_GREEN;
                break;

            case R.id.egg_yellow:
                color = BabyGanzaFragment.EGG_YELLOW;
                break;
            default:
                color = BabyGanzaFragment.EGG_PURPLE;

        }

        SharedPreferences.Editor editor = settings.edit();
        editor.putString(EGG_COLOR, color);
        editor.commit();
    }

    private void setBabyMode(Boolean babyMode){
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(BABY_MODE, babyMode);
        editor.commit();
    }
}
