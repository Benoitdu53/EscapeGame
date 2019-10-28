package com.ocr.escapegame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Lancer le processus de demande d'un mode de jeu
 */
public class GameMode {

    private static final Logger logger = LogManager.getLogger(GameMode.class);

    private IfAttaquant attaquant;
    private IfAttaquant attaquant1;
    private IfAttaquant attaquant2;

    private IfDefenseur defenseur;
    private IfDefenseur defenseur1;
    private IfDefenseur defenseur2;

    private int nbMode = 0;             // Choix du mode de jeu
    private int askMode = 0;            // Choix du mode jeu en fin de partie

    private String att;
    private String def;

    public void runGameMode() {
        Scanner sc = new Scanner(System.in);
        boolean reponseIsGood;

        do {
            if (askMode==1){
                System.out.println("");
                System.out.println("Vous rejouez au même mode ");
                System.out.println("");
            }else if (askMode==3){
                nbMode = 4;
            }else if(askMode==2 || askMode==0) {
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
                }

                switch (nbMode) {

                    case 1: // Mode Challenger                          // Affiche les règles du mode Challengeur
                        CommonMethods.afficheRulesChallengerMode();
                        attaquant = new HumanMethods();
                        defenseur = new IaMethods();
                        att = "L'utilisateur";                          // L'attanquant est l'utilisateur
                        def = "L'intelligence artificielle";            // Le défenseur est l'ia
                        Game();
                        break;

                    case 2: // Mode Défenseur
                        CommonMethods.afficheRulesDefenseurMode();      // Affiche les règles du mode défenseur
                        attaquant = new IaMethods();
                        defenseur = new HumanMethods();
                        def = "L'utilisateur";                          // L'attanquant est l'ia
                        att = "L'intelligence artificielle";            // Le défenseur est l'utilisateur
                        Game();
                        break;

                    case 3: // Mode Duel
                        CommonMethods.afficheRulesDuelMode();           // Affiche les règles du mode duel
                        attaquant1 = new HumanMethods();
                        defenseur1 = new HumanMethods();
                        attaquant2 = new IaMethods();
                        defenseur2 = new IaMethods();
                        GameDuel();
                        break;

                    case 4: // Quitter l'application
                        CommonMethods.disconnect();
                        break;
                }
            } while (nbMode < 1 || nbMode > 4);
    }


    /**
     * Mode Challenger et Défenseur
     */
    public void Game() {
        int[] combinaison = defenseur.generateCombinaison();        // On récupère la combinaison généré par le défenseur
        boolean winner = false;                                     // Combinaison trouvée
        int nP = 0;                                                 // Nombre de proposition
        int[] proposition = new int [4];                            // Tableau de la proposition faite par l'attaquant

        do {
            System.out.println();
            attaquant.recupererReponse(combinaison);                            // Récupérer la combinaison générer par le défenseur
            attaquant.recupererResults(proposition);                            // Récupérer la dernière proposition donner par l'attanquant
            proposition = attaquant.propositionCombinaison(nP);                 // Nouvelle proposition avec le nombre de proposition en paramètre
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

        /**
         * Partie terminée, on affiche si la combinaison à été trouvé et qui est le vainqueur.
         */
            if (winner) {
                System.out.println("La combinaison a été trouvée.");
                System.out.println( att+" est le vainqueur.");
                System.out.println("");
                }else {
                    System.out.println("La combinaison n'a pas été trouvée.");
                    System.out.println( def+" est le vainqueur.");
                    System.out.println("");
            }

        askMode = CommonMethods.AskGame();                  // On pose la question pour une nouvelle partie
        runGameMode();
    }


    /**
     * Mode Duel
     */
    private void GameDuel() {
        System.out.println("");
        System.out.println("Veuillez générez une combinaison à 4 chiffres ");
        int[] combinaison1 = defenseur1.generateCombinaison();                      // Récupération de la combinaison générée par l'utilisateur
        int[] combinaison2 = defenseur2.generateCombinaison();                      // Récupération de la combinaison générée par l'ia
        boolean humanWinner = false;                   // Si l'utilisateur à trouvé la combinaison
        boolean iaWinner = false;                      // Si l'ia à trouvée la combinaison
        int nP = 0;                                    // Nombre de propositions
        int[] proposition1 = new int [4];              // Tableau de proposition de l'utilisateur
        int[] proposition2 = new int [4];              // Tableau de proposition de l'ia

        do {
            nP++;
            attaquant1.recupererReponse(combinaison2);          // On récupère la combinaison générée de l'ia
            attaquant1.recupererResults(proposition1);          // On récupère la propostion de l'utilisateur
            attaquant2.recupererReponse(combinaison1);          // On récupère la combinaison générée de l'utilisateur
            attaquant2.recupererResults(proposition2);          // On récupère la proposition de l'ia

            System.out.println();

            proposition1 = attaquant1.propositionCombinaison(nP);                   // Proposition Utilisateur
            String res1 = CommonMethods.compare(combinaison2, proposition1);        // Comparaison des 2 combinaisons

            System.out.print("Proposition Humain : " );
                for (int a=0; a <=3; a++) {
                    System.out.print(proposition1[a]);
                }
            System.out.println(" -> Résultat : " + res1);
            System.out.println("");

            // Si l'utilisateur trouve la réponse alors il gagne sinon l'ia propose une combinaison
                if ("====".equals(res1)) {
                    humanWinner = true;             // L'utilisateur gagne
                    }else {
                        proposition2 = attaquant2.propositionCombinaison(nP);                   // Proposition de l'ia
                        String res2 = CommonMethods.compare(combinaison1, proposition2);        // Comparaison des 2 combinaisons
                        System.out.print("Proposition Ia : ");
                        for (int a = 0; a <= 3; a++) {
                            System.out.print(proposition2[a]);
                        }
                        System.out.println(" -> Résultat : " + res2);
                        System.out.println("");
                            if ("====".equals(res2)) {
                                iaWinner = true;            // L'ia gagne
                            }
                    }
        }while (!humanWinner && !iaWinner); // Tant que l'utilisateur ou l'ia ne gagne pas, continuer.

        /**
         * Partie terminée, on affiche si la combinaison à été trouvé et qui est le vainqueur.
         */
            if (humanWinner) {
                System.out.println("Vous avez trouvé la combinaison de l'intelligence artificielle");
                System.out.println("Félicitation !");
                System.out.println("");
                }else if (iaWinner){
                    System.out.println("L'ia à trouvée votre combinaison.'");
                    System.out.println( "Dommage !");
                    System.out.println("");
            }

        askMode = CommonMethods.AskGame();      // Poser la question sur une nouvelle partie
        runGameMode();                          // Retourner au menu
    }

}