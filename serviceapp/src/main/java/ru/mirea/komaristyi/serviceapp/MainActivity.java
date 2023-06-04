package ru.mirea.komaristyi.serviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView coverImage = findViewById(R.id.cover_image);
        TextView trackTitle = findViewById(R.id.track_title);
        Button playButton = findViewById(R.id.play_button);
        Button stopButton = findViewById(R.id.stop_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, PlayerService.class));
                coverImage.setImageResource(R.drawable.pablohoney);
                trackTitle.setText("Radiohead - Creep");

            }

        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, PlayerService.class));
                coverImage.setImageResource(R.drawable.unknow);
                trackTitle.setText("Music stopped");
            }
        });
    }
}