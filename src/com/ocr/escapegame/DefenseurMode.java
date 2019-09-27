package com.ocr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DefenseurMode {

    public void runDefenseurMode () {
        /*
          Déroulement du mode Défenseur
         */
        boolean IaWinner;
        int numProposition=0;
        int[] a = new int [4];
        int[] b = new int [4];
        int [] newSaisieIa = new int[4];

        Scanner waitForKeypress = new Scanner ( System.in );
        System.out.println("Vous avez choisi comme mode de jeu : Défenseur");
        System.out.println("");
        System.out.println("Principe du jeu en mode Défenseur :");
        System.out.println("Vous devez choisir la combinaison à X chiffres et l'intelligence artificielle à 4 chances de la trouvée.");
        System.out.println("A chaque proposition,");
        System.out.println("\t si son chiffre est supérieur au chiffre à trouver alors '-' apparaîtra,");
        System.out.println("\t si son chiffre est inférieur au chiffre à trouver alors '+' apparaîtra,");
        System.out.println("\t si son chiffre est égale au chiffre à trouver alors '=' apparaîtra.");
        System.out.println("Amusez vous bien !!");


        // L'utilisateur choisit une combinaison
        System.out.println("Saisissez votre combinaison secrète à 4 chiffres. Exemple : 1234");
        int[] userCombinaison = MethodGame.saisirCombinaison();
        System.out.print("Vous avez saisie la combinaison  : ");
        for (int i = 0; i < 4; i++) {
            System.out.print(userCombinaison[i]);
        }
        System.out.println("");

        do {
            numProposition++;
            // Si c'est la première proposition de l'Ia
            if (numProposition == 1) {
                for (int i = 0; i < 4; i++) {
                    a[i] = 0;
                    b[i] = 10;
                }
            }

            // Nouvelle saisie de l'Ia
            for (int c=0 ; c<4; c++){
                if (newSaisieIa[c] < userCombinaison[c]){
                    a[c] = newSaisieIa[c];
                    newSaisieIa [c] = (a[c] + b[c])/2;
                } else if (newSaisieIa[c] > userCombinaison[c]){
                    b[c] = newSaisieIa[c];
                    newSaisieIa [c] = (a[c] + b[c])/2;
                }
            }

            String[] ReponseNewSaisie = MethodGame.comparerCombinaisonUserIa(userCombinaison, newSaisieIa);

            // On affiche le résultat
            System.out.println("");
            System.out.println("Proposition N° : "+numProposition+" de l'Ia");
            for (int f = 0; f <= 3; f++) {
                System.out.print(newSaisieIa[f]);
            }
            System.out.println("");
            System.out.println("Résultat :");
            for (int g = 0; g <= 3; g++) {
                System.out.print(ReponseNewSaisie[g] + " ");
            }
            System.out.println("");

            if (ReponseNewSaisie[0].equals("=") && ReponseNewSaisie[1].equals("=") && ReponseNewSaisie[2].equals("=") && ReponseNewSaisie[3].equals("=")) {
                IaWinner = true;
            } else {
                IaWinner = false;
            }

            // L'utilisateur appuie sur Entrer pour continuer
            System.out . print (" Appuyez sur la touche Entrée pour continuer " ) ;
            waitForKeypress.nextLine ();

        }while(!IaWinner && numProposition<=3);
        System.out.println("Séléctionnez votre choix :");
        if (IaWinner){
            System.out.println("Dommage, l'intelligence artificielle a trouvée votre combinaison au bout de "+numProposition+" proposition(s).");
        } else {
            System.out.println(" Bravo l'intelligence artificielle n'a pas trouvée votre combinaison !");
        }

        /**
         * Demander à l'utilisateur si il veut rejouer.
         */
        Scanner sc = new Scanner(System.in);
        boolean reponseIsGood;
        int choixMode =0;
        do {
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
            System.out.println("");
        } while (choixMode < 1 || choixMode > 3);
        switch (choixMode){
            case 1 : this.runDefenseurMode();
                break;
            case 2 : GameMode.runGameMode();
                break;
            case 3 : System.out.println(" Vous avez choisi de quitter l'application.");
                System.out.println("");
                MethodGame.disconnect();
                break;
        }
    }
}
