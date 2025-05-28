package com.example.quiz;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import java.util.Collections;



public class Quiz {
    private static final int TIME_LIMIT_SECONDS = 10;
    private final List<Question> questions;
    private int score = 0;

    public Quiz(String resourcePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream in = getClass().getResourceAsStream(resourcePath)) {
            if (in == null) throw new IllegalArgumentException("Ressource non trouvée: " + resourcePath);
            this.questions = mapper.readValue(in, new TypeReference<>() {});
        }
    }

    public void start() {
    Collections.shuffle(questions);
    ExecutorService exec = Executors.newSingleThreadExecutor();
    ScheduledExecutorService timerExec = Executors.newSingleThreadScheduledExecutor();

    for (Question q : questions) {
        Collections.shuffle(q.getChoices());
        System.out.println(q.getQuestion());
        List<String> choices = q.getChoices();
        for (int i = 0; i < choices.size(); i++) {
            System.out.printf("  %d) %s%n", i + 1, choices.get(i));
        }

        // compteur visible
        System.out.println();
        final int[] timeLeft = {TIME_LIMIT_SECONDS};
        Runnable tick = () -> {
            System.out.printf("\rTemps restant : %2d s  ", timeLeft[0]--);
            if (timeLeft[0] < 0) {
                // stop ticking une fois passé à -1
                throw new RuntimeException("stop");  
            }
        };
        ScheduledFuture<?> ticker = timerExec.scheduleAtFixedRate(tick, 0, 1, TimeUnit.SECONDS);

        // lecture bloquante avec timeout
        Callable<Integer> readAnswer = () -> {
            Scanner sc = new Scanner(System.in);
            int pick = sc.nextInt() - 1;
            return pick;
        };
        Future<Integer> future = exec.submit(readAnswer);
        int pick = -1;
        boolean timedOut = false;

        try {
            pick = future.get(TIME_LIMIT_SECONDS + 1, TimeUnit.SECONDS);
        } catch (TimeoutException te) {
            timedOut = true;
        } catch (Exception e) {
            // ignorer
        } finally {
            future.cancel(true);
            ticker.cancel(true);
            // effacer la ligne de timer
            System.out.print("\r" + " ".repeat(30) + "\r");
        }

        // évaluer
       if (timedOut) {
    // anciennement System.out.println("⏰ Temps écoulé !\n");
    System.out.printf("⏰ Temps écoulé !%nBonne réponse : %s%n%n",
                      choices.get(q.getAnswerIndex()));
        } else if (pick == q.getAnswerIndex()) {
            System.out.println("✅ Correct!\n");
            score++;
        } else {
            System.out.printf("❌ Faux. Bonne réponse : %s%n%n", choices.get(q.getAnswerIndex()));
        }
    }

    timerExec.shutdownNow();
    exec.shutdownNow();
    System.out.printf("Quiz terminé ! Score final : %d/%d%n", score, questions.size());
}

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }
}
