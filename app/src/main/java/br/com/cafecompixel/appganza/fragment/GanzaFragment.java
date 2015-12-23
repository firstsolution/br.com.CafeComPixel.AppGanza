package br.com.cafecompixel.appganza.fragment;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.cafecompixel.appganza.R;
import br.com.cafecompixel.appganza.util.ShakeDetector;
import br.com.cafecompixel.appganza.util.SoundManager;

/**
 * A placeholder fragment containing a simple view.
 */
public class GanzaFragment extends Fragment {

    private SensorManager mSensorManager;
    private Sensor        mAccelerometer;
    private ShakeDetector mShakeDetector;

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

        return inflater.inflate(R.layout.fragment_ganza, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();

        mSensorManager.registerListener(mShakeDetector, mAccelerometer,
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        super.onPause();

        mSensorManager.unregisterListener(mShakeDetector);
    }
}
