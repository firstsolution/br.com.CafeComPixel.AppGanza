package br.com.cafecompixel.appganza.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.cafecompixel.appganza.activity.GanzaActivity;
import br.com.cafecompixel.appganza.R;

/**
 * Created by Marcos on 08/12/15.
 */
public class SplashScreen extends Activity implements Runnable {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Handler handler = new Handler();
        handler.postDelayed(this, 3000);
    }


    @Override
    public void run() {
        startActivity(new Intent(this, GanzaActivity.class));
        finish();
    }
}
