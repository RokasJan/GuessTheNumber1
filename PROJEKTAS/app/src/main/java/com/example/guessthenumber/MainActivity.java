package com.example.guessthenumber;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String PREFERENCES_FILE_NAME = "SettingsPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startGameBtn = findViewById(R.id.start_game_btn);

        final MediaPlayer bingoSound = MediaPlayer.create(this, R.raw.bingo_music_song);

        boolean MPsound;
        boolean sound = false;
        MPsound = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE).getBoolean("sound", sound);

        if (MPsound){
            bingoSound.start();
        }

        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClick(v);
            }
        });
    }

    public void startClick(View view){

        if(view == findViewById(R.id.start_game_btn)) {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        }
        else if (view == findViewById(R.id.results_btn)){
            Intent intent = new Intent(this, ResultsActivity.class);
            startActivity(intent);
        }
        else if (view == findViewById(R.id.settings_btn)){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        else if (view == findViewById(R.id.about_btn)){
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
    }
}
