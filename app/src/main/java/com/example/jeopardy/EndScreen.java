package com.example.jeopardy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EndScreen extends AppCompatActivity {
    private TextView score;
    private String pts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
        wireWidgets();
        Intent lastIntent = getIntent();
        pts = lastIntent.getStringExtra(MainActivity.POINTS);
        showScore();
    }
    private void wireWidgets(){
        score = findViewById(R.id.textView_main_score);
    }
    private void showScore(){
        score.setText(pts);
    }
}
