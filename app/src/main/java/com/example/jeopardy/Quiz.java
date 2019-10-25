package com.example.jeopardy;

import java.util.List;

public class Quiz {
    private int score, questionNumber;
    private List<Question> questionList;

    public Quiz(List<Question> questionList) {
        this.questionList = questionList;
    }

    public int getScore() {
        return score;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public boolean hasMoreQuestions(){
        return questionNumber == questionList.size();
    }
}
