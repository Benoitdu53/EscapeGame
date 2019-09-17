package com.ocr.escapegame;

import java.util.Collection;
import java.util.Scanner;

public class ChallengerMode {
    Scanner sc = new Scanner(System.in);
    MethodGame methodGame = new MethodGame();

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


         // L'Ia définit sa combinaison

        System.out.println("");
        System.out.println("L'Intelligence artificielle définit aléatoirement une combinaison...");
        int[] IaCombinaison = new int[4];
        for (int i = 0; i < 4; i++) {
            int numCombi = (int) (Math.random() * 10);
            IaCombinaison[i] = numCombi;
        }
        for (int j = 0; j < 4; j++) {
            System.out.print(IaCombinaison[j]);
        }
        System.out.println("");
        System.out.println("");


         // Proposition de l'utilisateur
        int nP = 0;
        do {
            if (nP == 0) {
                System.out.println("Donner votre première proposition ");
                System.out.println("");
            }
            else if (nP == 1) {
                System.out.println("Donner votre deuxième proposition ");
                System.out.println("");
            }
            else if (nP == 2) {
                System.out.println("Donner votre deuxième proposition ");
                System.out.println("");
            }
            else if (nP == 3) {
                System.out.println("Donner votre troisième proposition ");
                System.out.println("");
            }
            else if (nP == 4) {
                System.out.println("Donner votre quatrième et dernière proposition ");
                System.out.println("");
            }

            MethodGame.saisirCombinaison();

            int userCombinaison = MethodGame.saisirCombinaison();
                MethodGame.comparerCombinaison(userCombinaison);
                nP++;
            // TODO Comparaison du chiffre avec celui de l'Ia
        } while (nP <= 4);// nP n'est pas égale à 4 ou que la réponse à été trouvée
    }
}