package com.ocr.escapegame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Lancer le processus de demande d'un mode de jeu
 */
public class GameMode {

    private static final Logger logger = LogManager.getLogger(GameMode.class);

    private IfAttaquant attaquant;
    private IfAttaquant attaquant1;     // Human Attaquant
    private IfAttaquant attaquant2;     // Ia Attaquant

    private IfDefenseur defenseur;
    private IfDefenseur defenseur1;     // Humaine défenseur
    private IfDefenseur defenseur2;     // Ia défenseur

    private int nbMode = 0;             // Choix du mode de jeu
    private int askMode = 0;            // Choix du mode jeu en fin de partie

    private String att;
    private String def;

    private boolean modeDev =false;     // Mode dev

    Scanner sc = new Scanner(System.in);
    private boolean reponseIsGood;

    public void runGameMode() {
        if (modeDev){
            System.out.println("Mode développeur");
        }


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

                    // Saisie du mode de jeu
                    do {
                            try {
                                nbMode = sc.nextInt();
                                reponseIsGood = true;
                                sc.nextLine();
                            } catch (InputMismatchException e) {
                                sc.next();
                                System.out.println("Veuillez saisir un chiffre correspondant au mode de jeu souhaité");
                                logger.info("L'utilisateur n'a pas saisie un chiffre.");
                                reponseIsGood = false;
                            }
                    } while (!reponseIsGood);
                }

                switch (nbMode) {

                    case 1: // Mode Challenger
                        CommonMethods.afficheRulesChallengerMode();     // Affiche les règles du mode Challengeur
                        attaquant = new HumanMethods();
                        defenseur = new IaMethods();
                        att = "L'utilisateur";                          // L'attaquant est l'utilisateur
                        def = "L'intelligence artificielle";            // Le défenseur est l'ia
                        Game();
                        break;

                    case 2: // Mode Défenseur
                        CommonMethods.afficheRulesDefenseurMode();      // Affiche les règles du mode défenseur
                        attaquant = new IaMethods();
                        defenseur = new HumanMethods();
                        def = "L'utilisateur";                          // L'attaquant est l'ia
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
        int[] proposition = new int [GameProperties.NOMBRE_CHIFFRES];                            // Tableau de la proposition faite par l'attaquant
        int nombresEgale;
        int nombreProposition = nP+1;

        if (GameProperties.MODE_DEV){                               // On active le mode dev si modeDev = true
            System.out.println(" Mode développeur activé ");        // Du coup on affiche la combinaison du défenseur
            System.out.println("Combinaison à trouver : " +Arrays.toString(combinaison)+ ".");
            System.out.println("");
        }

        do {
            nombresEgale =0;

                if (nombreProposition == GameProperties.NOMBRE_ESSAIE){
                      System.out.println("!! Dernière proposition !! ");
                }

            attaquant.recupererReponse(combinaison);                            // Récupérer la combinaison générer par le défenseur
            attaquant.recupererResults(proposition);                            // Récupérer la dernière proposition donner par l'attanquant
            proposition = attaquant.propositionCombinaison(nP);                 // Nouvelle proposition avec le nombre de proposition en paramètre
            String res = CommonMethods.compare(combinaison, proposition);       // Comparer les 2 combinaisons
            System.out.println(" Proposition numéro : "+nombreProposition);


                System.out.print("Proposition :" );

            for (int a=0; a <GameProperties.NOMBRE_CHIFFRES; a++) {
                System.out.print(proposition[a]);
            }

            System.out.println(" -> Résultat : " + res);
            System.out.println("");

            nP++;

            for (int e = 0; e < GameProperties.NOMBRE_CHIFFRES; e++){
                if (res.charAt(e) == '=') {
                    nombresEgale++;
                }
            }

            if (nombresEgale == GameProperties.NOMBRE_CHIFFRES) {
                winner = true;
            }
        }while (!winner && nP < GameProperties.NOMBRE_ESSAIE);

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
        boolean humanWinner = false;                   // Si l'utilisateur à trouvé la combinaison
        boolean iaWinner = false;                      // Si l'ia à trouvée la combinaison
        int nP = 0;                                    // Nombre de propositions
        int[] proposition1 = new int [4];              // Tableau de proposition de l'utilisateur
        int[] proposition2 = new int [4];              // Tableau de proposition de l'ia
        int nombresEgale1;
        int nombresEgale2;
        int nombreProposition = nP+1;

        System.out.println("");
        System.out.println("Veuillez générez une combinaison à "+GameProperties.NOMBRE_CHIFFRES+" chiffres ");
        int[] combinaison1 = defenseur1.generateCombinaison();                      // Récupération de la combinaison générée par l'utilisateur
        int[] combinaison2 = defenseur2.generateCombinaison();                      // Récupération de la combinaison générée par l'ia

        if (GameProperties.MODE_DEV) {                                      // On active le mode dev si modeDev =true
            System.out.println(" Mode développeur activé ");                // Du coup on affiche la combinaison des défenseurs
            System.out.println("Votre combinaison à trouver est : "+Arrays.toString(combinaison1)+".");
            System.out.println("La combinaison de l'ia à trouver est : "+Arrays.toString(combinaison2)+".");
            System.out.println("");
        }

        do {
            attaquant1.recupererReponse(combinaison2);          // On récupère la combinaison générée de l'ia
            attaquant1.recupererResults(proposition1);          // On récupère la propostion de l'utilisateur
            attaquant2.recupererReponse(combinaison1);          // On récupère la combinaison générée de l'utilisateur
            attaquant2.recupererResults(proposition2);          // On récupère la proposition de l'ia
            nombresEgale1 =0;

            System.out.println();

            proposition1 = attaquant1.propositionCombinaison(nP);                   // Proposition Utilisateur
            String res1 = CommonMethods.compare(combinaison2, proposition1);        // Comparaison des 2 combinaisons

            System.out.print("Votre proposition numéro "+nombreProposition+" : " );
                for (int a=0; a <GameProperties.NOMBRE_CHIFFRES; a++) {
                    System.out.print(proposition1[a]);
                }
            System.out.println(" -> Résultat : " + res1);
            System.out.println("");

            // Si l'utilisateur trouve la réponse alors il gagne sinon l'ia propose une combinaison

            for (int g = 0; g < GameProperties.NOMBRE_CHIFFRES; g++){
                if (res1.charAt(g) == '=') {
                    nombresEgale1++;
                }
            }
                if (nombresEgale1 == GameProperties.NOMBRE_CHIFFRES) {
                    humanWinner = true;             // L'utilisateur gagne
                    }else {

                    proposition2 = attaquant2.propositionCombinaison(nP);                   // Proposition de l'ia
                    String res2 = CommonMethods.compare(combinaison1, proposition2);        // Comparaison des 2 combinaisons
                    System.out.print("Proposition Ia numéro "+nombreProposition+": ");
                    nombresEgale2 =0;

                        for (int a = 0; a < GameProperties.NOMBRE_CHIFFRES; a++) {
                            System.out.print(proposition2[a]);
                        }
                    System.out.println(" -> Résultat : " + res2);
                    System.out.println("");

                        for (int f = 0; f < GameProperties.NOMBRE_CHIFFRES; f++) {
                            if (res2.charAt(f) == '=') {
                                nombresEgale2++;
                            }
                        }
                            if (nombresEgale2 == GameProperties.NOMBRE_CHIFFRES) {
                                iaWinner = true;            // L'ia gagne
                            }
                    nP++;
                    nombreProposition++;
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
                    System.out.println("L'intelligence artificielle a trouvée votre combinaison.'");
                    System.out.println( "Dommage !");
                    System.out.println("");
            }

        askMode = CommonMethods.AskGame();      // Poser la question sur une nouvelle partie
        runGameMode();                          // Retourner au menu
    }

}