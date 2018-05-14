package com.example.ga_mlsdiscovery.viewmodelproject2.view.activity_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.ga_mlsdiscovery.viewmodelproject2.R;
import com.example.ga_mlsdiscovery.viewmodelproject2.view.main_activity.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {
    private int sleepTimer = 3;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Make sure content screen comes after window manager call

        //In android No way of knowing this existing
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Must be after window call
        setContentView(R.layout.activity_splash_screen);

        //must come after setContentView
        getSupportActionBar().hide();

        //instantiate image
        imageView = findViewById(R.id.action_image);
        imageView.setAlpha(0f);
        imageView.animate()
                .alpha(1f)
                .setDuration(2700)
                .setListener(null);

        //starts our thread
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();

    }

    private class LogoLauncher extends Thread{
        public void run(){
            try {
                sleep(1000 * sleepTimer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(i);
            //will destroy the activity
            SplashScreenActivity.this.finish();
        }
    }
}
