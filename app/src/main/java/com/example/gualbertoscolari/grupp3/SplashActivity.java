package com.example.gualbertoscolari.grupp3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

/**
 * the intro screen
 */
public class SplashActivity extends Activity implements Animation.AnimationListener{
    private DbHelper db;
    final float growTo = 1.1f;
    final long duration = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        db = new DbHelper(this);
        db.addStandardItemsSQL();
        db.close();

        ImageView test = (ImageView) findViewById(R.id.splash_logo);
        ScaleAnimation grow = new ScaleAnimation(1, growTo, 1, growTo,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        grow.setDuration(duration / 2);
        ScaleAnimation shrink = new ScaleAnimation(growTo, 1, growTo, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        shrink.setDuration(duration / 2);
        shrink.setStartOffset(duration / 2);
        AnimationSet growAndShrink = new AnimationSet(true);
        growAndShrink.setInterpolator(new LinearInterpolator());
        growAndShrink.addAnimation(grow);
        growAndShrink.addAnimation(shrink);
        test.startAnimation(growAndShrink);

        final Thread timer= new Thread(){
            public void run(){
                try{

                    ImageView rotate_image =(ImageView) findViewById(R.id.rotateSplashImg);
                    RotateAnimation rotate = new RotateAnimation(30, 360, Animation.RELATIVE_TO_SELF, 0.5f,  Animation.RELATIVE_TO_SELF, 0.5f);
                    rotate.setDuration(2500);
                    rotate_image.startAnimation(rotate);
                    sleep (3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    Intent menuIntent = new Intent(SplashActivity.this,MenuActivity.class);
                    startActivity(menuIntent);
                    finish();
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }
}
