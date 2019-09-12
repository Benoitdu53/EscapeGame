package com.ocr.escapegame;

public class GameMode {

    public void displayGameMode (){
        System.out.println("Choix du mode de jeu");
        System.out.println("1 - Mode Challenger");
        System.out.println("2 - Mode Défenseur ");
        System.out.println("3 - Mode Duel");
        System.out.println("Quel mode de jeu choisissez-vous ?");
    }

    public void displaySelectedMode(int nbMode) {
        if (nbMode == 1){
            System.out.println("Vous avez choisi comme mode de jeu : Challenger");
        }
        else if (nbMode == 2){
            System.out.println("Vous avez choisi comme mode de jeu : Défensseur");
        }
        else if (nbMode == 3){
            System.out.println("Vous avez choisi comme mode de jeu : Duel");
        }
        else {
            System.out.println("Vous n'avez pas choisi parmi les mode de jeu proposés.");
        }
    }
}
