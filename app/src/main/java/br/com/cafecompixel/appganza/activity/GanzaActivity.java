package br.com.cafecompixel.appganza.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.com.cafecompixel.appganza.R;
import br.com.cafecompixel.appganza.fragment.BabyGanzaFragment;
import br.com.cafecompixel.appganza.fragment.ConfigGanzaFragment;
import br.com.cafecompixel.appganza.fragment.GanzaFragment;
import br.com.cafecompixel.appganza.util.ShakeDetector;

public class GanzaActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private FragmentTransaction transaction;
    public static final String PREFS_NAME = "BabyConfig";


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

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake() {
                playSound();
            }
        });

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ganza, menu);
        return true;
    }

//    public void irModoBaby (View view) {

//        Switch babyToggle = (Switch) findViewById(R.id.action_modo_baby);
//
//        if(babyToggle.isChecked()) {
//            irParaFragment(new BabyGanzaFragment());
//        } else {
//            irParaFragment(new GanzaActivityFragment());
//        }
//    }

    public void irParaFragment(Fragment fragment) {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment,null);
        transaction.commit();
    }

    public void playSound() {
        MediaPlayer player = MediaPlayer.create(this, R.raw.shek);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,
                SensorManager.SENSOR_DELAY_UI);

    }
//    Evento para saber quando tocou na tela
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        // TODO Auto-generated method stub
//        Toast.makeText(this, "clicou na tela", Toast.LENGTH_SHORT).show();
//        return super.onTouchEvent(event);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                irParaFragment(new ConfigGanzaFragment());
                return true;

            case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;


            default:
                return super.onOptionsItemSelected(item);

        }

    }


    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mShakeDetector);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, GanzaActivity.class);
        startActivity(intent);
        finish();
    }


}
