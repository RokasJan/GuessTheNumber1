package com.example.guessthenumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private static final String PREFERENCES_FILE_NAME = "SettingsPref";

    private int minNumber = 1;
    private int maxNumber = 100;

    private int randomNumber;

    private int maxTurns = 7;
    private int currentTurn = 0;

    private int result = 0;
    int difficultyint = 0;

    private TextView numberRangeText;
    private TextView resultText;
    private TextView turnsText;

    private EditText numberField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        numberRangeText = findViewById(R.id.number_range);
        resultText = findViewById(R.id.result_text);
        turnsText = findViewById(R.id.turns_text);
        numberField = findViewById(R.id.player_answer);
        numberField.setHint("Enter the number");

        int difficulty = 0;
        difficultyint = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE).getInt("difficulty", difficulty);
        if(difficultyint == 0){
            maxNumber = 10;
        }
        else if(difficultyint == 1){
            maxNumber = 50;
        }
        else if(difficultyint == 2){
            maxNumber = 100;
        }
        else if(difficultyint == 3){
            maxNumber = 1000;
        }

        updateTexts(0);

        Random random = new Random();
        randomNumber = random.nextInt(maxNumber - minNumber) + minNumber;
    }

    private void updateTexts(int guessedNumber){
        numberRangeText.setText(String.format(getResources().getString(R.string.number_range_format), minNumber, maxNumber));
        if (result > 0){
            resultText.setText(String.format(getResources().getString(R.string.result_format), guessedNumber, getResources().getString(R.string.result_high)));
        }
        else if (result < 0){
            resultText.setText(String.format(getResources().getString(R.string.result_format), guessedNumber, getResources().getString(R.string.result_low)));
        }
        turnsText.setText(String.format(getResources().getString(R.string.turns_format), currentTurn, maxTurns));
    }

    public void guessClick(View view){
        currentTurn++;
        int guessedNumber = Integer.parseInt(numberField.getText().toString());

        result = 0;

        if(randomNumber > guessedNumber){
            result = -1;
        }
        else if (randomNumber < guessedNumber){
            result = 1;
        }

        if(currentTurn >= maxTurns && result != 0){
            //Lose
            Intent intent = new Intent(this, GameOverActivity.class);
            intent.putExtra("guessedNumber", guessedNumber);
            intent.putExtra("randomNumber", randomNumber);
            intent.putExtra("currentTurn", currentTurn);
            intent.putExtra("win", false);
            startActivity(intent);
            finish();
        }
        else if(result == 0){
            //Win
            Intent intent = new Intent(this, GameOverActivity.class);
            intent.putExtra("guessedNumber", guessedNumber);
            intent.putExtra("randomNumber", randomNumber);
            intent.putExtra("currentTurn", currentTurn);
            intent.putExtra("win", true);
            startActivity(intent);
            finish();
        }

        updateTexts(guessedNumber);
    }

}
