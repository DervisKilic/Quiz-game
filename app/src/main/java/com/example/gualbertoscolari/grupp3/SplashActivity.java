package com.example.gualbertoscolari.grupp3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity implements Animation.AnimationListener{
    private Animation fadeout;
    private ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        DbHelper db = new DbHelper(this);
        db.addStandardItemsSQL();

        img1 = (ImageView) findViewById(R.id.imageView);
        fadeout = AnimationUtils.loadAnimation(this,R.anim.fade_out);
        img1.startAnimation(fadeout);


        final Thread timer= new Thread(){
            public void run(){
                try{
                    sleep (3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent menuIntent = new Intent(SplashActivity.this,MenuActivity.class);
                    startActivity(menuIntent);
                    finish();
                    isFinishing();
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
