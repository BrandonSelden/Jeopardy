package com.example.jeopardy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button butTrue, butFal, start, next;
    private TextView question, answer;
    private String jsonFileText;
    private List<Question> questionList;
    private Quiz quiz;
    private int questionNumber, pts;
    public static final String POINTS = "points";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputStream JSONFileInputStream = getResources().openRawResource(R.raw.questions);
        wireWidgets();
        jsonFileText = readTextFile(JSONFileInputStream);
        createGson();
        setListeners();
        questionNumber = 0;
        pts = 0;
    }
    public void createGson(){
        Gson gson = new Gson();
        Question[] questions = gson.fromJson(jsonFileText, Question[].class);
        questionList = Arrays.asList(questions);
        Log.d("o yes", "onCreate: " + questionList.toString());
        quiz = new Quiz(questionList);
    }
    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }// reading text file from
    //https://stackoverflow.com/questions/15912825/how-to-read-file-from-res-raw-by-name

    public void wireWidgets(){
        butTrue = findViewById(R.id.button_main_true);
        butFal = findViewById(R.id.button_main_false);
        question = findViewById(R.id.textView_main_question);
        start = findViewById(R.id.button_main_start);
        answer = findViewById(R.id.textView_main_answer);
        next = findViewById(R.id.button_main_next);
    }

    public void setListeners(){
        start.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View view){
                start.setVisibility(View.INVISIBLE);
                question.setVisibility(View.VISIBLE);
                butFal.setVisibility(View.VISIBLE);
                butTrue.setVisibility(View.VISIBLE);
                question.setText(questionList.get(questionNumber).getQuestion());
            }
        }));
        butTrue.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View view){
                answer.setText(questionList.get(questionNumber).getAnswer());
                answer.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                if(questionList.get(questionNumber).getAnswer().equals("yes")){
                    pts++;
                }
            }
        }));
        next.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View view){
                question.setText(questionList.get(questionNumber).getQuestion());
                next.setVisibility(View.INVISIBLE);
                answer.setVisibility(View.INVISIBLE);
                if(questionNumber < 3) {
                    questionNumber++;
                }
                else{
                    Intent targetIntent = new Intent(MainActivity.this, EndScreen.class);
                    targetIntent.putExtra(POINTS, pts);
                    startActivity(targetIntent);
                }
            }

        }));
        butFal.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View view){
                answer.setText(questionList.get(questionNumber).getAnswer());
                answer.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                if(questionList.get(questionNumber).getAnswer().equals("no")){
                    pts++;
                }
            }
        }));
    }

}
