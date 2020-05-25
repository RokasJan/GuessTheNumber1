package com.example.guessthenumber;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREFERENCES_FILE_NAME = "SettingsPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences prefs = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE);

        String playerName = prefs.getString("playerName", "Name");
        int playerAge = prefs.getInt("playerAge", 1);
        int difficulty = prefs.getInt("difficulty", 0);
        boolean sound = prefs.getBoolean("sound", true);

        EditText nameField = findViewById(R.id.name_field);
        EditText ageField = findViewById(R.id.age_field);
        Spinner spinner = findViewById(R.id.difficulty_spinner);
        Switch soundSwitch = findViewById(R.id.sound_switch);

        nameField.setText(playerName);
        ageField.setText(Integer.toString(playerAge));
        spinner.setSelection(difficulty);
        soundSwitch.setChecked(sound);
    }

    public void saveSettings(View v){
        EditText playerNameField = findViewById(R.id.name_field);
        EditText playerAgeField = findViewById(R.id.age_field);
        Spinner spinner = findViewById(R.id.difficulty_spinner);
        Switch soundSwitch = findViewById(R.id.sound_switch);

        String playerName = playerNameField.getText().toString();
        int playerAge = Integer.parseInt(playerAgeField.getText().toString());
        int difficulty = spinner.getSelectedItemPosition();
        boolean sound = soundSwitch.isChecked();

        SharedPreferences.Editor prefs = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE).edit();

        prefs.putString("playerName", playerName);
        prefs.putInt("playerAge", playerAge);
        prefs.putInt("difficulty", difficulty);
        prefs.putBoolean("sound", sound);

        prefs.apply();

        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, "Your information has been saved!", duration);
        toast.show();

        final MediaPlayer bingoSound = MediaPlayer.create(this, R.raw.bingo_music_song);

        if (sound){
            bingoSound.start();
        }
        if (!sound){
            if (bingoSound.isPlaying()){
                bingoSound.stop();
                bingoSound.reset();
                bingoSound.release();
            }
//            bingoSound.stop();
//            bingoSound.reset();
//            bingoSound.release();
        }
    }
}
