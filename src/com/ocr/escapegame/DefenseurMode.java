package com.ocr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DefenseurMode {

    public void runDefenseurMode () {
        /*
          Déroulement du mode Défenseur
         */
        boolean Combi;
        int nP=0;
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


        // L'utilisateur choisi une combinaison
        int[] userCombinaison = MethodGame.saisirCombinaison();
        System.out.print("Vous avez saisie la combinaison  : ");
        for (int i = 0; i < 4; i++) {
            System.out.print(userCombinaison[i]);
        }
        System.out.println("");

        // L'intelligence Artificielle saisie une combinaison
        int[] IaCombinaison = new int[4];
        for (int i = 0; i < 4; i++) {
            int numCombi = (int) (Math.random() * 10);
            IaCombinaison[i] = numCombi;
        }

        // On compare les saisies des 2 combinaisons
        int[] Combinaison2 = IaCombinaison;
        int[] Combinaison1 = userCombinaison;
        String[] ReponseFinal = MethodGame.comparerCombinaison(Combinaison1, Combinaison2);

        // On affiche le résultat de la première comparaison
        System.out.println("");
        System.out.println("Proposition N° : 1");
        for (int a = 0; a <= 3; a++) {
            System.out.print(Combinaison2[a]);
        }
        System.out.println("");
        System.out.println("Résultat:");
        for (int b = 0; b <= 3; b++) {
            System.out.print(ReponseFinal[b] + " ");
        }
        System.out.println("");
        nP++;

        // Si dès la première comparaison les 2 combinaisons sont égales Combi=true
        if (ReponseFinal[0].equals("=") && ReponseFinal[1].equals("=") && ReponseFinal[2].equals("=") && ReponseFinal[3].equals("=")) {
            Combi = true;
        } else
            Combi = false;

        System.out . print (" Appuyez sur la touche Entrée pour continuer " ) ;
        waitForKeypress.nextLine ();

        //Chercher une nouvelle saisie selon le retour de la comparaison précédente
        // Tant que la combinaison ou que l'intelligence artificielle à utiliser ses 4 chances
        while (!Combi && nP<=3) {
            int nProposition =nP+1;
            int[] newSaisieIa = MethodGame.newSaisieIa(Combinaison1, Combinaison2);
            Combinaison2 = newSaisieIa;
            String[] ReponseNewSaisie = MethodGame.comparerCombinaison(Combinaison1, Combinaison2);

            // On affiche le résultat
            System.out.println("");
            System.out.println("Proposition N° : "+nProposition);
            for (int a = 0; a <= 3; a++) {
                System.out.print(newSaisieIa[a]);
            }
            System.out.println("");
            System.out.println("Résultat :");
            for (int b = 0; b <= 3; b++) {
                System.out.print(ReponseNewSaisie[b] + " ");
            }
            System.out.println("");
            nP++;

            System.out . print (" Appuyez sur la touche Entrée pour continuer " );
            waitForKeypress.nextLine ();

            if (ReponseNewSaisie[0].equals("=") && ReponseNewSaisie[1].equals("=") && ReponseNewSaisie[2].equals("=") && ReponseNewSaisie[3].equals("=")) {
                Combi = true;
            } else
                Combi = false;
        }
        if (Combi){
            System.out.println("");
            System.out.println(" Dommage, l'intelligence artificielle à trouve votre combinaison");
        }else
            System.out.println("");
            System.out.println(" Félicitation, l'intelligence artificielle n'a pas trouvée votre combinaison !!!");

        System.out.println("");
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
