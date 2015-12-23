package br.com.cafecompixel.appganza.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.com.cafecompixel.appganza.R;
import br.com.cafecompixel.appganza.fragment.BabyGanzaFragment;
import br.com.cafecompixel.appganza.fragment.ChickGanzaFragment;
import br.com.cafecompixel.appganza.fragment.ConfigGanzaFragment;
import br.com.cafecompixel.appganza.fragment.EggGanzaFragment;
import br.com.cafecompixel.appganza.fragment.GanzaFragment;

public class GanzaActivity extends AppCompatActivity {

    private FragmentTransaction transaction;
    public static final String PREFS_NAME = "BabyConfig";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isBabyMode;
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        isBabyMode = settings.getBoolean(ConfigGanzaFragment.BABY_MODE, false);

        setContentView(R.layout.activity_ganza);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(isBabyMode) {
            irParaFragment(new BabyGanzaFragment());
        } else {
            irParaFragment(new GanzaFragment());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ganza, menu);

        Fragment actualFragment = getSupportFragmentManager().findFragmentById(R.id.fragment);

        if(actualFragment instanceof ChickGanzaFragment || actualFragment instanceof EggGanzaFragment) {

            MenuItem item = menu.findItem(R.id.action_settings);
            item.setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                irParaFragment(new ConfigGanzaFragment());
                return true;

            case android.R.id.home:
                Fragment actualFragment = getSupportFragmentManager().findFragmentById(R.id.fragment);

                if(actualFragment instanceof ChickGanzaFragment || actualFragment instanceof EggGanzaFragment) {

                    irParaFragment(new BabyGanzaFragment());
                }
                else {
                    if (actualFragment instanceof ConfigGanzaFragment) {
                        Intent intent = new Intent(this, GanzaActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(this, BabyGanzaFragment.class);
                        startActivity(intent);
                        finish();
                    }
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void irParaFragment(Fragment fragment) {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment , null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        Fragment actualFragment = getSupportFragmentManager().findFragmentById(R.id.fragment);

        if(actualFragment instanceof BabyGanzaFragment || actualFragment instanceof GanzaFragment) {
            finish();
        } else {
            Intent intent = new Intent(this, GanzaActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
