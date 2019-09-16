package com.ocr.escapegame;

import java.util.Collection;
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

        /**
         * L'Ia définit sa combinaison
         */
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

        /**
         * Proposition de l'utilisateur
         */
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
            // Récupération des propositions de l'utilisateur en Strg
            String [] UCombinaison = new String[5];
            boolean[] testC = new boolean[5];
            boolean pVal = true;
            String pU;
            do {
                pU = sc.nextLine();
                for (int l = 0; l < 4; l++) {
                    int o = l+1;
                    String cU = pU.substring(l,o);
                    UCombinaison[l] = cU;
                    System.out.println(UCombinaison[l]+"    Test");
                    System.out.println(l);
                    if (cU == "0" || cU != "1" ||cU != "2" ||cU != "3" ||cU != "4" ||cU != "5" ||cU != "6" ||cU != "7" ||cU != "8" ||cU != "9" ){
                        testC [l] = true;
                    } else {
                        testC [l] = false;
                    }
                    System.out.println(testC[l]);
                }
                System.out.println("");
                if (testC[0] != true && testC[1] != true && testC[2] != true && testC[3] != true ){
                    pVal =false;
                    System.out.println("Erreur, donner un nombre de 4 chiffres !");
                }else{
                    pVal =true;
                }
                } while (!pVal) ;

                nP++;
            // TODO Comparaison du chiffre avec celui de l'Ia
        } while (nP < 4);// nP n'est pas égale à 4 ou que la réponse à été trouvée
    }
}