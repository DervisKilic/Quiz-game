package com.example.gualbertoscolari.grupp3;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Siniom on 27/11/16.
 */
public class PopUp extends Activity {

    private static final String TAG = "POPUP_ACTIVITY";
    CountDownTimer timer;
    TextView popUpTV;
    private static final int REQUEST_CODE =100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_cd);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        popUpTV = (TextView) findViewById(R.id.popup_countdown_tv);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .8));
        countdown();

    }

    public void countdown() {
        timer = new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished) {
                popUpTV.setText("" + (millisUntilFinished / 1000));

            }

            public void onFinish() {



                finish();
            }
        }.start();
        Log.d(TAG, "countdown: Timer started");

    }
}
