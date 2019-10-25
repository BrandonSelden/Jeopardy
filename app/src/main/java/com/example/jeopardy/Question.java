package com.example.jeopardy;

public class Question {
    private String question, answer;
    public Question (String question, String answer){
        this.question = question;
        this.answer = answer;
    }
    public String getAnswer(){
        return answer;
    }
    public String getQuestion(){
        return question;
    }
}
