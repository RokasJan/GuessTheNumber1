package com.example.guessthenumber;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class GameOverActivity extends AppCompatActivity {

    private static final String PREFERENCES_FILE_NAME = "SettingsPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        int randomNumber = 0;
        int currentTurn = 0;
        String result = "";

        TextView answer = findViewById(R.id.win_lose);
        ImageView image = findViewById(R.id.win_lost_img);
        TextView numberoftries = findViewById(R.id.numberoftries);

        Intent intent = getIntent();

        boolean win = intent.getBooleanExtra("win", false);
        if(win){
            answer.setText(String.format(getResources().getString(R.string.answer_winlose), intent.getIntExtra("randomNumber", randomNumber)));
            numberoftries.setText(String.format(getResources().getString(R.string.answer_numberoftries_format), intent.getIntExtra("currentTurn", currentTurn)));
            image.setImageDrawable(getResources().getDrawable(R.drawable.winnerimage));
            result = "Won";
        }
        else{
            answer.setText(String.format(getResources().getString(R.string.answer_winlose), intent.getIntExtra("randomNumber", randomNumber)));
            numberoftries.setText(String.format(getResources().getString(R.string.outofturns), intent.getIntExtra("currentTurn", currentTurn)));
            image.setImageDrawable(getResources().getDrawable(R.drawable.loseimage));
            result = "Lost";
        }

        String playerName = "";
        int difficulty = 0;
        String level = "";
        int difflevel = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE).getInt("difficulty", difficulty);
        String firstname = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE).getString("playerName", playerName);
        if (difflevel == 0){
            level = "Easy";
        }
        else if (difflevel == 1){
            level = "Medium";
        }
        else if (difflevel == 2){
            level = "Hard";
        }
        else if (difflevel == 3){
            level = "Impossible";
        }

        User user = new User();
        user.setFirstName(firstname);
        user.setDifficulty(level);
        user.setNumber(intent.getIntExtra("randomNumber", randomNumber));
        user.setResult(result);

        AppDatabase.getDatabase(getApplicationContext()).userDao().insertAll(user);
    }
}
