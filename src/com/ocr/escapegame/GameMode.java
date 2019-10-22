package com.ocr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameMode {
    /**
     * Lancer le processus de demande d'un mode de jeu
     */
    private IfAttaquant attaquant;
    private IfDefenseur defenseur;
    private int nbMode = 0;
    private String att;
    private String def;
    public int numProposition;

    public void runGameMode() {
        Scanner sc = new Scanner(System.in);
        boolean reponseIsGood;

        do {
            System.out.println("");
            System.out.println("Bienvenue dans ESCAPE GAME ONLINE");
            System.out.println("");
            System.out.println("Choix du mode de jeu");
            System.out.println("1 - Mode Challenger");
            System.out.println("2 - Mode Défenseur ");
            System.out.println("3 - Mode Duel");
            System.out.println("4 - Quitter");
            System.out.println("Quel mode de jeu choisissez-vous ?");
            do {
                try {
                    nbMode = sc.nextInt();
                    reponseIsGood = true;
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    sc.next();
                    System.out.println("Veuillez saisir un chiffre correspondant au mode de jeu souhaité");
                    reponseIsGood = false;
                }
            } while (!reponseIsGood);

            switch (nbMode) {

                case 1:
                    CommonMethods.afficheRulesChallengerMode();
                    attaquant = new HumanMethods();
                    defenseur = new IaMethods();
                    game();
                    att = "Vous";
                    def = "L'intelligence artificielle";
                    break;

                case 2:
                    CommonMethods.afficheRulesDefenseurMode();
                    attaquant = new IaMethods();
                    defenseur = new HumanMethods();
                    def = "Vous";
                    att = "L'intelligence artificielle";
                    numProposition = 0 ;
                    game();
                    break;
                case 3:
                    CommonMethods.afficheRulesDuelMode();
                    break;
                // Quitter l'application
                case 4:
                    CommonMethods.disconnect();
                    break;
            }
        } while (nbMode < 1 || nbMode > 4);
    }

    public void game() {
        int[] combinaison = defenseur.generateCombinaison();
        boolean winner = false;
        int nP = 0;
        int prop =0;
        do {
            prop++;
            System.out.println("Proposition numéro : " +prop);
            int[] proposition = attaquant.propositionCombinaison();
            String res = CommonMethods.compare(combinaison, proposition);
            System.out.print("Proposition :" );
            for (int a=0; a <=3; a++) {
                System.out.print(proposition[a]);
            }
            System.out.println(" -> Résultat : " + res);
            System.out.println("");
            nP++;
            if ("====".equals(res)) {
                winner = true;
            }
        }while (!winner && nP <= 3);

        if (winner) {
            System.out.println("La combinaison a été trouvée.");
            System.out.println( att+" est le vainqueur.");
        }else {
            System.out.println("La combinaison n'a pas été trouvée.");
            System.out.println( def+" est le vainqueur.");
        }
    }
}