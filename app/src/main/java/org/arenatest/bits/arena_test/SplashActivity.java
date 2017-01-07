package org.arenatest.bits.arena_test;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

/*
        startService(new Intent(this, EventsUpdateService.class));
        startService(new Intent(this, MedalsUpdateService.class));
        startService(new Intent(this, SponsorsUpdateService.class));
*/

        startAnimations();
        new Handler().postDelayed(new Runnable() {
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(i);

                // close this activity
                finish();

            }
        }, 3000);
    }

    private void startAnimations() {
        /*Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        TextView l = (TextView) findViewById(R.id.splash_title);
        l.clearAnimation();
        l.startAnimation(anim);*/


        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        TextView iv = (TextView) findViewById(R.id.splash_title);
        Typeface typeFace= Typeface.createFromAsset(getAssets(),"fonts/GOTHAM-ULTRA1.TTF");
        iv.setTypeface(typeFace);
        anim.setDuration(1500);
        iv.clearAnimation();
        iv.startAnimation(anim);

/*
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        TextView l2 = (TextView) findViewById(R.id.splash_title);
        l2.setVisibility(View.VISIBLE);
        l2.clearAnimation();
        l2.startAnimation(anim);
*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
