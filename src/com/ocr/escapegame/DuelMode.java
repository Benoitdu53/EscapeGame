package com.ocr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DuelMode {

    public void runDuelMode(){
        /*
        Déroulement du mode Duel
         */
        Scanner sc = new Scanner(System.in);
        char modeDev;
        Scanner waitForKeypress = new Scanner ( System.in );

        // On demande si on active le mode développeur
        do{
            System.out.println("Voulez-vous activer le mode développeur? (O/N)");
            modeDev = sc.nextLine().charAt(0);
        }while(modeDev != 'O' && modeDev != 'N');

        System.out.println("Vous avez choisi comme mode de jeu : Duel");
        System.out.println("");
        System.out.println("Choississez une combinaison secrète et l'intelligence artificielle en fait de même.");
        System.out.println("");
        System.out.println("Principe du jeu en mode Duel :");
        System.out.println("A tour de rôle, vous essayer de trouver la combinaison de l'Intelligence artificielle essaye à son tour.");
        System.out.println("A chaque proposition,");
        System.out.println("\t si votre chiffre est supérieur au chiffre à trouver alors '-' apparaîtra,");
        System.out.println("\t si votre chiffre est inférieur au chiffre à trouver alors '+' apparaîtra,");
        System.out.println("\t si votre chiffre est égale au chiffre à trouver alors '=' apparaîtra.");
        System.out.println("Le premier à trouver la combinaison de l'autre à gagné !");
        System.out.println("Bonne chance !");
        System.out.println("");


        // L'utilisateur saisie sa combinaison secrète
        System.out.println("Saisissez votre combinaison secrète à 4 chiffres. Exemple : 1234");
        int[] userCombinaison = MethodGame.saisirCombinaison();
        System.out.println("Votre combinaison secrète est :");
        for (int a = 0; a <= 3; a++) {
            System.out.print(userCombinaison[a]);
        }
        System.out.println("");

        // L'intelligence artificielle saisie sa combinaison
        System.out.println("");
        System.out.println("L'Intelligence artificielle définit aléatoirement une combinaison...");
        int[] IaCombinaison = new int[4];
        for (int i = 0; i < 4; i++) {
            int numCombi = (int) (Math.random() * 10);
            IaCombinaison[i] = numCombi;
        }

        // Si on active le mode développeur, on affiche la combinaison de l'Ia
        if (modeDev =='O') {
            System.out.println("!! Mode développeur activé !!");
            System.out.println("La combinaison secrète de l'intelligence artificielle est :");
            for (int j = 0; j < 4; j++) {
                System.out.print(IaCombinaison[j]);
            }
        }
        System.out.println("");
        System.out.println("");

        int [] newSaisieIa = new int[4];
        boolean userWinner;
        boolean IaWinner = false;
        int numProposition = 0;

        do {
            numProposition++;
            // L'utilisateur saisie une combinaison pour essayer de trouver la combinaison de l'Ia
            System.out.println("Donner votre proposition n° : " + numProposition);
            int[] propositionUser = MethodGame.saisirCombinaison();
            int[] Combinaison1 = IaCombinaison;
            int[] Combinaison2 = propositionUser;
            String[] ReponseFinal = MethodGame.comparerCombinaison(Combinaison1, Combinaison2);

            // On affiche le résultat de la comparaison
            System.out.println("");
            System.out.println("Proposition : " + numProposition);
            for (int a = 0; a <= 3; a++) {
                System.out.print(propositionUser[a]);
            }
            System.out.println("");
            System.out.println("Résultat:");
            for (int b = 0; b <= 3; b++) {
                System.out.print(ReponseFinal[b] + " ");
            }
            System.out.println("");
            // Si l'utilisateur a trouvé la combinaison de l'Ia alors il a gagné
            if (ReponseFinal[0].equals("=") && ReponseFinal[1].equals("=") && ReponseFinal[2].equals("=") && ReponseFinal[3].equals("=")){
                userWinner=true;
                System.out.println("");

            // Sinon l'Ia fait une proposition
            }else{
                System.out . print (" Appuyez sur la touche Entrée pour continuer " );
                waitForKeypress.nextLine ();
                userWinner=false;
                Combinaison1 = userCombinaison;

                // Si c'est la première proposition de l'Ia
                    if(numProposition==1) {
                        for (int i = 0; i < 4; i++) {
                            Combinaison2[i] = 0;
                        }
                    }else {
                        Combinaison2 = newSaisieIa;
                    }

                    newSaisieIa = MethodGame.newSaisieIa(Combinaison1, Combinaison2);
                    Combinaison2 = newSaisieIa;
                    String[] ReponseNewSaisie = MethodGame.comparerCombinaison(Combinaison1, Combinaison2);

                    // On affiche le résultat
                    System.out.println("");
                    System.out.println("Proposition N° : "+numProposition+" de l'Ia");
                    for (int a = 0; a <= 3; a++) {
                        System.out.print(newSaisieIa[a]);
                    }
                    System.out.println("");
                    System.out.println("Résultat :");
                    for (int b = 0; b <= 3; b++) {
                        System.out.print(ReponseNewSaisie[b] + " ");
                    }
                    System.out.println("");

                    if (ReponseNewSaisie[0].equals("=") && ReponseNewSaisie[1].equals("=") && ReponseNewSaisie[2].equals("=") && ReponseNewSaisie[3].equals("=")) {
                        IaWinner = true;
                    } else {
                        IaWinner = false;
                    }
                }
        }while (!IaWinner && !userWinner);
        if (IaWinner){
            System.out.println("Vous avez perdu !");
            System.out.println("L'intelligence artificielle a trouvée votre combinaison au bout de "+numProposition+" proposition(s).");
        }else if (userWinner){
            System.out.println("Bravo vous avez trouvé la combinaison au bout de "+numProposition+" proposition(s) !" );
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
                this.runDuelMode();
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
