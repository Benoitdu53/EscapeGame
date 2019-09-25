package com.ocr.escapegame;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ChallengerMode {

    /**
     * Déroulement du mode Challenger
     */
    public void runChallengerMode() {
        int nP = 0;
        boolean Winner;
        int nProposition = nP + 1;
        char modeDev;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("Voulez-vous activer le mode développeur? (O/N)");
            modeDev = sc.nextLine().charAt(0);
        }while(modeDev != 'O' && modeDev != 'N');

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
        // Si le mode développeur est activé on affiche la combinaison de l'Ia
        if (modeDev =='O') {
            System.out.println("!! Mode développeur activé !!");
            System.out.println("La combinaison secrète de l'intelligence artificielle est :");
            for (int j = 0; j < 4; j++) {
                System.out.print(IaCombinaison[j]);
            }
        }
        System.out.println("");
        System.out.println("");


        // Proposition de l'utilisateur

        do {
            if (nP == 0) {
                System.out.println("Donner votre première proposition ");
            } else if (nP == 1) {
                System.out.println("");
                System.out.println("Donner votre deuxième proposition ");
            } else if (nP == 2) {
                System.out.println("");
                System.out.println("Donner votre troisième proposition ");
            } else if (nP == 3) {
                System.out.println("");
                System.out.println("Donner votre quatrième et dernière proposition ");
            }

            // Saisie de l'utilisateur et la récupérer
            int[] userCombinaison = MethodGame.saisirCombinaison();

            // Faire la comparaison des deux combinaisons et retourner le résultat.
            int[] Combinaison2 = userCombinaison;
            int[] Combinaison1 = IaCombinaison;

            String[] ReponseFinal = MethodGame.comparerCombinaison(Combinaison1, Combinaison2);

            // On affiche le résultat
            System.out.println("");
            System.out.println("Proposition : " + nProposition);
            for (int a = 0; a <= 3; a++) {
                System.out.print(userCombinaison[a]);
            }
            System.out.println("");
            System.out.println("Résultat:");
            for (int b = 0; b <= 3; b++) {
                System.out.print(ReponseFinal[b] + " ");
            }
            System.out.println("");

            // Si toutes les combinaisons sont égales à "=" alors la partie est gagné sinon retenté sa chance
            if (ReponseFinal[0].equals("=") && ReponseFinal[1].equals("=") && ReponseFinal[2].equals("=") && ReponseFinal[3].equals("=")) {
                System.out.println("");
                Winner = true;
            } else {
                nP++;
                Winner = false;
            }
        } while (!Winner && nP <= 3);
        if (Winner) {
            System.out.println(" Félicitation, vous avez trouvé la combinaison !!!");
        } else {
            System.out.println(" Dommage... Vous n'avez pas réussi à trouver la combinaison.");
        }

            System.out.println("");
            /**
             * Demander à l'utilisateur si il veut rejouer.
             */
            boolean reponseIsGood;
            int choixMode = 0;
            do{
                System.out.println("Séléctionnez votre choix :");
                System.out.println("1 - Rejouez au même mode");
                System.out.println("2 - Changez de mode");
                System.out.println("3 - Quitter le jeu");
                do {
                    try {
                        choixMode = sc.nextInt();
                        reponseIsGood = true;
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        sc.next();
                        System.out.println("Veuillez saisir un chiffre correspondant au choix souhaité");
                        reponseIsGood = false;
                    }
                } while (!reponseIsGood);
            } while (choixMode < 1 || choixMode > 3);
            System.out.println("");
            switch (choixMode) {
                case 1:
                    this.runChallengerMode();
                    break;
                case 2:
                    GameMode.runGameMode();
                    break;
                case 3:
                    System.out.println(" Vous avez choisi de quitter l'application.");
                    System.out.println("");
                    MethodGame.disconnect();
                    break;
            }
    }
}