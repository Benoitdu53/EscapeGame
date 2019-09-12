package com.ocr.escapegame;

import java.util.Scanner;

public class GameMode {

    /**
     * Afficher les modes de jeu
     */
    public void displayGameMode (){
        System.out.println("Choix du mode de jeu");
        System.out.println("1 - Mode Challenger");
        System.out.println("2 - Mode Défenseur ");
        System.out.println("3 - Mode Duel");
        System.out.println("Quel mode de jeu choisissez-vous ?");
    }

    /**
     * Afficher le mode séléctionné
     * @param nbMode Selectionne un  mode de jeu
     */
    public void displaySelectedMode(int nbMode) {
        if (nbMode == 1){
            System.out.println("Vous avez choisi comme mode de jeu : Challenger");
        }
        else if (nbMode == 2){
            System.out.println("Vous avez choisi comme mode de jeu : Défenseur");
        }
        else if (nbMode == 3){
            System.out.println("Vous avez choisi comme mode de jeu : Duel");
        }
        else {
            System.out.println("Vous n'avez pas choisi parmi les mode de jeu proposés.");
        }
    }

    /**
     * Lancer le processus de demande d'un mode de jeu
     */
    public void runGameMode(){
        this.displayGameMode();
        Scanner sc = new Scanner(System.in);
        int nbMode = sc.nextInt();
        this.displaySelectedMode(nbMode);
    }
}
