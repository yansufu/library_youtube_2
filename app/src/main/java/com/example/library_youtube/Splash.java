package com.example.library_youtube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, login.class);
                startActivity(intent);
                finish();

            }
        }, 3000);

        VideoView bg;
        bg = findViewById(R.id.background);
        String path = "app/src/main/res/raw/splash.mp4";
        Uri u = Uri.parse(path);
        bg.setVideoURI(u);
        bg.start();
    }
    }
