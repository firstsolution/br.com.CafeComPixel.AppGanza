package br.com.cafecompixel.appganza.fragment;

import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import br.com.cafecompixel.appganza.R;
import br.com.cafecompixel.appganza.activity.GanzaActivity;
import br.com.cafecompixel.appganza.util.ShakeDetector;
import br.com.cafecompixel.appganza.util.SoundManager;

/**
 * A placeholder fragment containing a simple view.
 */
public class GanzaFragment extends Fragment {

    public static final String EGG_BLACk  = "egg_black";
    public static final String EGG_BLUE   = "egg_blue";
    public static final String EGG_GREEN  = "egg_green";
    public static final String EGG_RED    = "egg_red";
    public static final String EGG_PURPLE = "egg_purple";
    public static final String EGG_PINK   = "egg_pink";
    public static final String EGG_YELLOW = "egg_yellow";

    private ImageView eggImageView;

    private SensorManager mSensorManager;
    private Sensor        mAccelerometer;
    private ShakeDetector mShakeDetector;

    SharedPreferences settings;
    View layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mSensorManager = (SensorManager) getActivity().getSystemService(getActivity().SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake() {
                SoundManager.playSound(getContext());
            }
        });

        settings =  getActivity(). getSharedPreferences(GanzaActivity.PREFS_NAME, 0);
        layout = inflater.inflate(R.layout.fragment_ganza, container, false);

        return layout;

    }

    @Override
    public void onResume() {
        super.onResume();

        mSensorManager.registerListener(mShakeDetector, mAccelerometer,
                SensorManager.SENSOR_DELAY_FASTEST);
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
    public void onPause() {
        super.onPause();

        mSensorManager.unregisterListener(mShakeDetector);
    }
}
