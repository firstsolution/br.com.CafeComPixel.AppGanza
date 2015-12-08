package br.com.cafecompixel.appganza;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class GanzaActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganza);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake() {
                playSound();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ganza, menu);
        return true;
    }

    public void irModoBaby (View view){
        Intent intencao =new Intent(this,BabyGanzaFragment.class);
        startActivity(intencao);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        int id = item.getItemId();
//
//        replaceFragment(new BabyGanzaFragment(), "MODO BABY");
//
//        if (id == R.id.action_settings) {
//        }
        switch (item.getItemId()) {
            case R.id.action_modo_baby:
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, new BabyGanzaFragment(),null);
                transaction.commit();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
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
