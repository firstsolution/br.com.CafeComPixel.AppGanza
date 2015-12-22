package br.com.cafecompixel.appganza.util;

import android.content.Context;
import android.media.MediaPlayer;

import br.com.cafecompixel.appganza.R;

/**
 * Created by lucas on 22/12/15.
 */
public class SoundManager {

    public static void playSound(Context context) {

        MediaPlayer player = MediaPlayer.create(context, R.raw.shek);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }
}
