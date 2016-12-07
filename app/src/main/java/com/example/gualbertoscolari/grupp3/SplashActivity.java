package com.example.gualbertoscolari.grupp3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * the intro screen
 */
public class SplashActivity extends Activity implements Animation.AnimationListener{
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        db = new DbHelper(this);
        db.addStandardItemsSQL();
        db.close();
        final Thread timer= new Thread(){
            public void run(){
                try{

                    ImageView rotate_image =(ImageView) findViewById(R.id.imageView);
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
