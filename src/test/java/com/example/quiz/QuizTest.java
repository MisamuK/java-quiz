package com.example.quiz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuizTest {
    @Test
    public void loadQuestions_and_initialScore() throws Exception {
        Quiz quiz = new Quiz("/questions.json");
        assertEquals(0, quiz.getScore(), "Score initial doit être 0");
        assertTrue(quiz.getTotalQuestions() >= 1, "Doit charger au moins une question");
    }
}

