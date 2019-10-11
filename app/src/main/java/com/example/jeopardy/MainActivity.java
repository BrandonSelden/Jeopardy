package com.example.jeopardy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private Button butTrue, butFal;
    private TextView question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputStream JSONFileInputStream = getResources().openRawResource(R.raw.questions);
        wireWidgets();
        question.setText(readTextFile(JSONFileInputStream));

        setListeners();
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
    }
    public void setListeners(){

    }

}
