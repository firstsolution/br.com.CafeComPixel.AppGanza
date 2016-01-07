package br.com.cafecompixel.appganza.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

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

        RadioGroup radioGroup = (RadioGroup) layout.findViewById(R.id.radio_group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                RadioButton radioButton = (RadioButton) layout.findViewById(checkedId);

                cleanCheckedIcon(checkedId);

                radioButton.setBackgroundResource(R.drawable.check);

                setEggColor(checkedId);
            }
        });

    }

    private void cleanCheckedIcon(int checkedId) {

        int[] raddioButtonIds = {R.id.egg_black, R.id.egg_pink, R.id.egg_red, R.id.egg_purple,
                R.id.egg_blue, R.id.egg_green, R.id.egg_yellow};

        RadioButton radioButton;

        for(int id : raddioButtonIds) {

            if(id == checkedId)
                continue;

            radioButton = (RadioButton) layout.findViewById(id);
            radioButton.setBackgroundResource(0);
        }
    }

    public void setEggColor(int itemId){

        String color;

        switch (itemId)
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
