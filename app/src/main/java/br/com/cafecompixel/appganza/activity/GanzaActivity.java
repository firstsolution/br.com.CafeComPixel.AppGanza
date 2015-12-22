package br.com.cafecompixel.appganza.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import java.util.Random;

import br.com.cafecompixel.appganza.R;
import br.com.cafecompixel.appganza.fragment.BabyGanzaFragment;
import br.com.cafecompixel.appganza.fragment.ConfigGanzaFragment;
import br.com.cafecompixel.appganza.fragment.GanzaFragment;
import br.com.cafecompixel.appganza.util.ShakeDetector;

public class GanzaActivity extends AppCompatActivity {

    private FragmentTransaction transaction;
    public static final String PREFS_NAME = "BabyConfig";

    Integer touchesLeft = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean isBabyMode = settings.getBoolean(ConfigGanzaFragment.BABY_MODE, false);

        setContentView(R.layout.activity_ganza);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(isBabyMode) {
            irParaFragment(new BabyGanzaFragment());
        } else {
            irParaFragment(new GanzaFragment());
        }

        resetTouchesLeft();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ganza, menu);
        return true;
    }

    public void irParaFragment(Fragment fragment) {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment,null);
        transaction.commit();
    }

    //Evento para saber quando tocou na tela
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        this.touchesLeft--;


//        Toast.makeText(this, "falta " + touchesLeft.toString() + " toques", Toast.LENGTH_SHORT).show();
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                irParaFragment(new ConfigGanzaFragment());
                return true;

            case android.R.id.home:
                FragmentManager fm = getSupportFragmentManager();
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
                if(fragment instanceof ConfigGanzaFragment) {
                    Intent intent = new Intent(this, GanzaActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(this, BabyGanzaFragment.class);
                    startActivity(intent);
                    finish();
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, GanzaActivity.class);
        startActivity(intent);
        finish();
    }


    private void resetTouchesLeft() {
        this.touchesLeft = (new Random()).nextInt(10);
    }

}
