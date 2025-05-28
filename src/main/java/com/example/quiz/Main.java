package com.example.quiz;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean playAgain;
        do {
            try {
                Quiz quiz = new Quiz("/questions.json");
                quiz.start();
            } catch (Exception e) {
                System.err.println("Erreur au démarrage du quiz : " + e.getMessage());
                e.printStackTrace();
                return;
            }
            // à la fin, proposer de rejouer
            System.out.print("Voulez-vous rejouer ? (o = oui, n = non) : ");
            String answer = sc.nextLine().trim().toLowerCase();
            playAgain = answer.equals("o") || answer.equals("oui");
            System.out.println();
        } while (playAgain);
        System.out.println("Merci d’avoir joué ! À bientôt.");
        sc.close();
    }
}