package com.ocr.escapegame;

import java.util.Scanner;

public class ChallengerMode {
    Scanner sc = new Scanner(System.in);

    /**
     * Déroulement du mode Challenger
     */
    public void runChallengerMode() {
        System.out.println("Vous avez choisi comme mode de jeu : Challenger");
        System.out.println("");
        System.out.println("Principe du jeu en mode Challenger :");
        System.out.println("Vous devez trouver la combinaison à X chiffres choisis par l'intelligence artificielle.");
        System.out.println("A chaque proposition,");
        System.out.println("\t si votre chiffre est supérieur au chiffre à trouver alors '-' apparaîtra,");
        System.out.println("\t si votre chiffre est inférieur au chiffre à trouver alors '+' apparaîtra,");
        System.out.println("\t si votre chiffre est égale au chiffre à trouver alors '=' apparaîtra.");
        System.out.println("Vous avez 4 chances ! Bon courage !!");

        // L'IA définit sa combinaison aléatoirement
        System.out.println("");
        System.out.println("L'Intelligence artificielle définit aléatoirement une combinaison...");
        int[] IaCombinaison = new int[5];
        for (int i = 0; i < 4; i++) {
            int numCombi = (int) (Math.random() * 10);
            IaCombinaison[i] = numCombi;
        }
        for (int j =0;j<4;j++){
            System.out.print(IaCombinaison[j]);
        }
    }
}
