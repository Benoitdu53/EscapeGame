package com.ocr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameMode {
    Scanner sc = new Scanner(System.in);
    /**
     * Lancer le processus de demande d'un mode de jeu
     */
    public void runGameMode(){
        int nbMode = 0;
        boolean reponseIsGood;
        do {
            this.displayGameMode();
           do {
               try {
                   nbMode = sc.nextInt();
                   reponseIsGood = true;
               } catch (InputMismatchException e){
                   sc.next();
                   System.out.println("Veuillez saisir un chiffre correspondant au mode de jeu souhaité");
                   reponseIsGood = false;
               }
           } while (!reponseIsGood);
            switch (nbMode){
                case 1 : ChallengerMode challengerMode = new ChallengerMode();
                    challengerMode.runChallengerMode();
                    break;
                case 2 : System.out.println("Vous avez choisi comme mode de jeu : Défenseur");
                    break;
                case 3 : System.out.println("Vous avez choisi comme mode de jeu : Duel");
                    break;
                case 4 : System.out.println("Vous avez choisi de quitter l'application");
                    break;
                default: System.out.println("Veuillez choisir parmi les propositions !");
                break;
            }
        } while (nbMode < 1 || nbMode > 4);
    }

    /**
     * Afficher les modes de jeu
     */
    public void displayGameMode (){
        System.out.println("Bienvenue dans ESCAPE GAME ONLINE");
        System.out.println("Choix du mode de jeu");
        System.out.println("1 - Mode Challenger");
        System.out.println("2 - Mode Défenseur ");
        System.out.println("3 - Mode Duel");
        System.out.println("4 - Quitter");
        System.out.println("Quel mode de jeu choisissez-vous ?");
    }
}
