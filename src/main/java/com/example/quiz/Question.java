package com.example.quiz;

import java.util.List;

public class Question {
    private String question;
    private List<String> choices;
    private int answerIndex;

    // Constructeur pour Jackson
    public Question() {}

    public Question(String question, List<String> choices, int answerIndex) {
        this.question = question;
        this.choices = choices;
        this.answerIndex = answerIndex;
    }

    public String getQuestion() {
        return question;
    }
    public List<String> getChoices() {
        return choices;
    }
    public int getAnswerIndex() {
        return answerIndex;
    }
}
