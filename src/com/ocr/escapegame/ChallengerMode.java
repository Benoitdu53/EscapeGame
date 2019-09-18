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
        int nP = 0;

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
        boolean Winner = false;
        do {
            if (nP == 0) {
                System.out.println("Donner votre première proposition ");
            }
            else if (nP == 1) {
                System.out.println("Donner votre deuxième proposition ");
            }
            else if (nP == 2) {
                System.out.println("Donner votre troisième proposition ");
            }
            else if (nP == 3) {
                System.out.println("Donner votre quatrième et dernière proposition ");
            }

            // Confirmer la bonne saisie de l'utilisateur et la récupérer
            MethodGame.saisirCombinaison();
            int[] userCombinaison = MethodGame.saisirCombinaison();

            // Faire la comparaison des deux combinaisons et retourner le résultat.
                String[] ReponseFinal = MethodGame.comparerCombinaison(userCombinaison,IaCombinaison);
                System.out.println("");
                System.out.println("La réponse pour le 1er chiffre est "+ReponseFinal[0]);
                System.out.println("La réponse pour le 2ème chiffre est "+ReponseFinal[1]);
                System.out.println("La réponse pour le 3ème chiffre est "+ReponseFinal[2]);
                System.out.println("La réponse pour le 4ème chiffre est "+ReponseFinal[3]);
                System.out.println("");

            // Si toutes les combinaisons sont égales à "=" alors la partie est gagné sinon retenté sa chance
            if (ReponseFinal[0] == "=" && ReponseFinal[1] == "=" && ReponseFinal[2] == "=" && ReponseFinal[3] == "="){
                Winner = true;
                System.out.println("Bien joué");
            }else {
                nP++;
            }
        } while (nP <= 3 || Winner == true);// nP n'est pas égale à 4 ou que la réponse à été trouvée
            if (Winner){
                System.out.println(" Félicitation, vous avez trouvé la combinaison !!!");
            }else {
                System.out.println(" Dommage... Vous n'avez pas réussi à trouver la combinaison.");
            }
    }
}