package com.example.jeopardy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndScreen extends AppCompatActivity {
    private TextView score;
    private String printedScore;
    private Button restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
        wireWidgets();
        setListeners();
        Intent lastIntent = getIntent();
        String pts = lastIntent.getStringExtra(MainActivity.POINTS);
        printedScore = getString(R.string.you_got) + " " + pts + " " + getString(R.string.score);
        showScore();
    }
    private void wireWidgets(){
        score = findViewById(R.id.textView_main_score);
        restart = findViewById(R.id.button_endscreen_restart);
    }
    private void showScore(){
        score.setText(printedScore);
    }
    public void setListeners(){
        restart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.exit(0);
            }
        });
    }
}
