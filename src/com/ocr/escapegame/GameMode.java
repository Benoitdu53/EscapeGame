package com.ocr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameMode {
    /**
     * Lancer le processus de demande d'un mode de jeu
     */
    public static void runGameMode(){
        Scanner sc = new Scanner(System.in);
        int nbMode = 0;
        boolean reponseIsGood;
        do {
            System.out.println("");
            System.out.println("Bienvenue dans ESCAPE GAME ONLINE");
            System.out.println("");
            System.out.println("Choix du mode de jeu");
            System.out.println("1 - Mode Challenger");
            System.out.println("2 - Mode Défenseur ");
            System.out.println("3 - Mode Duel");
            System.out.println("4 - Quitter");
            System.out.println("Quel mode de jeu choisissez-vous ?");
           do {
               try {
                   nbMode = sc.nextInt();
                   reponseIsGood = true;
                   sc.nextLine();
               } catch (InputMismatchException e){
                   sc.next();
                   System.out.println("Veuillez saisir un chiffre correspondant au mode de jeu souhaité");
                   reponseIsGood = false;
               }
           } while (!reponseIsGood);

            switch (nbMode){
                    // Challenger Mode
                case 1 : ChallengerMode challengerMode = new ChallengerMode();
                    challengerMode.runChallengerMode();
                    break;
                    // Défenseur Mode
                case 2 : DefenseurMode defenseurMode = new DefenseurMode();
                    defenseurMode.runDefenseurMode();
                    break;
                    // Duel Mode
                case 3 : System.out.println("Vous avez choisi comme mode de jeu : Duel");
                    break;
                    // Quitter l'application
                case 4 :
                        MethodGame.disconnect();
                    break;
            }
        } while (nbMode < 1 || nbMode > 4);
    }
}
