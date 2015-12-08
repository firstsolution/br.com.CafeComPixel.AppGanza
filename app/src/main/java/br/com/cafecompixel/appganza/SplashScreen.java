package br.com.cafecompixel.appganza;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

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
