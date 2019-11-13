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

    private IfAttaquant attaquant;      // Variable qui indique qui est l'attaquant ( Mode Challenger & Mode Défenseur )
    private IfAttaquant attaquant1;     // Variable qui indique que c'est l'Humain l'Attaquant (Mode Duel)
    private IfAttaquant attaquant2;     // Variable qui indique due c'est l'IA l'Attaquant (Mode Duel)

    private IfDefenseur defenseur;      // Variable qui indique qui est le défenseur ( Mode Challenger & Mode Défenseur )
    private IfDefenseur defenseur1;     // Variable qui indique que c'est l'Humain le défenseur (Mode Duel)
    private IfDefenseur defenseur2;     // Variable qui indique que c'est l'IA le défenseur (Mode Duel)

    private int nbMode = 0;             // Choix du mode de jeu
    private int askMode = 0;            // Choix du mode jeu en fin de partie

    private String att;                 // Variable qui indique qui est l'attaquant
    private String def;                 // Variable qui indique qui est le défenseur

    private boolean modeDev;     // Variable qui indique sir le Mode dev est activé
    private boolean reponseIsGood;      // Variable qui indique qui si la l'utilisateur à bien saisi le mode de jeu

    Scanner sc = new Scanner(System.in);





    /**
     * Lancement de l'application
     * @param modeDev Si le mode développeur est activé
     */
    public void runGameMode(boolean modeDev) {

        if (GameProperties.MODE_DEV || modeDev){
            System.out.println("----- MODE DEVELOPPEUR -----");
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
                        CommonMethods.afficheRulesChallengerMode();          // Affiche les règles du mode Challengeur
                        attaquant = new HumanMethods();
                        defenseur = new IaMethods();
                        att = "L'utilisateur";                          // L'attaquant est l'utilisateur
                        def = "L'intelligence artificielle";            // Le défenseur est l'ia
                        Game();
                        break;

                    case 2: // Mode Défenseur
                        CommonMethods.afficheRulesDefenseurMode();          // Affiche les règles du mode défenseur
                        attaquant = new IaMethods();
                        defenseur = new HumanMethods();
                        def = "L'utilisateur";                          // L'attaquant est l'ia
                        att = "L'intelligence artificielle";            // Le défenseur est l'utilisateur
                        Game();
                        break;

                    case 3: // Mode Duel
                        CommonMethods.afficheRulesDuelMode();               // Affiche les règles du mode duel
                        attaquant1 = new HumanMethods();                // Humain Attaquant
                        defenseur1 = new HumanMethods();                // Humain Défenseur
                        attaquant2 = new IaMethods();                   // IA Attaquant
                        defenseur2 = new IaMethods();                   // IA Défenseur
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
        int[] combinaison = defenseur.generateCombinaison();                // Tableau de la combinaison à prendre
        boolean winner = false;                                             // Variable qui indique que la combinaison trouvée
        int nP = 0;                                                         // Variable qui indique à combien de proposition nous sommes
        int[] proposition = new int [GameProperties.NOMBRE_CHIFFRES];       // Tableau de la proposition faite par l'attaquant
        int nombresEgale;                               // Variable qui test si la comparaison est positif
        int nombreProposition = nP+1;                   // Variable qui indique le nombre d'essais

        // Si le modeDev est activé, on affiche la combinaison du défenseur
        if (GameProperties.MODE_DEV){
            System.out.println("\t\tMode développeur activé ");
            System.out.println("\tCombinaison à trouver : " +Arrays.toString(combinaison)+ ".");
            System.out.println("");
        }

        do {
            nombresEgale =0;

                if (nombreProposition == GameProperties.NOMBRE_ESSAIE){         // Si c'est la dernière proposition on affiche
                      System.out.println("!! Dernière proposition !! ");        // que c'est le dernière essai
                }

            System.out.println("Essai numéro : "+nombreProposition);

            attaquant.recupererReponse(combinaison);                            // Récupérer la combinaison générer par le défenseur
            attaquant.recupererResults(proposition);                            // Récupérer la dernière proposition donner par l'attanquant
            proposition = attaquant.propositionCombinaison(nP);                 // Nouvelle proposition avec le nombre de proposition en paramètre
            String res = CommonMethods.compare(combinaison, proposition);       // Comparer les 2 combinaisons

                System.out.print("Proposition : " );

            for (int a=0; a <GameProperties.NOMBRE_CHIFFRES; a++) {
                System.out.print(proposition[a]);
            }

            System.out.println(" -> Résultat : " + res);
            System.out.println("");

            nP++;
            nombreProposition++;

            for (int e = 0; e < GameProperties.NOMBRE_CHIFFRES; e++){           // On incrémente de 1 nombresEgale à chaque fois que res(e) est égale à '='
                if (res.charAt(e) == '=') {
                    nombresEgale++;
                }
            }

            if (nombresEgale == GameProperties.NOMBRE_CHIFFRES) {               // Si le nombresEgale est égale aux nombres de chiffres dans la combinaison
                winner = true;                                                  // L'attaquant a gagné
            }


        }while (!winner && nP < GameProperties.NOMBRE_ESSAIE);    // Tant que l'attaquant n'a pas trouvé et que le nombre d'essais Max n'est pas atteint

          //Partie terminée, on affiche si la combinaison à été trouvé et qui est le vainqueur.
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
        runGameMode(modeDev);
    }





    /**
     * Mode Duel
     */
    private void GameDuel() {
        boolean humanWinner = false;                   // Variable qui indique si l'utilisateur à trouvé la combinaison
        boolean iaWinner = false;                      // Variable qui indique si l'ia à trouvée la combinaison
        int nP = 0;                                    // Variable qui indique à combien de proposition nous sommes
        int[] proposition1 = new int [4];              // Tableau de proposition de l'utilisateur
        int[] proposition2 = new int [4];              // Tableau de proposition de l'ia
        int nombresEgale1;                             // Variable qui test si la comparaison est positif ( Human -> Ia )
        int nombresEgale2;                             // Variable qui test si la comparaison est positif ( Ia -> Human )
        int nombreProposition = nP+1;                  // Variable qui indique le nombre d'essais

        System.out.println("");
        System.out.println("Veuillez générez une combinaison à "+GameProperties.NOMBRE_CHIFFRES+" chiffres ");
        int[] combinaison1 = defenseur1.generateCombinaison();                      // Récupération de la combinaison générée par l'utilisateur
        System.out.println("L'Intelligence artificielle définit aléatoirement une combinaison...");
        int[] combinaison2 = defenseur2.generateCombinaison();                      // Récupération de la combinaison générée par l'ia

        if (GameProperties.MODE_DEV) {                                         // On active le mode dev si modeDev =true
            System.out.println("\t\tMode développeur activé ");                // Du coup on affiche la combinaison des défenseurs
            System.out.println("\tVotre combinaison à trouver est : "+Arrays.toString(combinaison1)+".");
            System.out.println("\tLa combinaison de l'ia à trouver est : "+Arrays.toString(combinaison2)+".");
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

            System.out.print("Votre proposition numéro "+nombreProposition+" : " );     // On affiche la proposition
                for (int a=0; a <GameProperties.NOMBRE_CHIFFRES; a++) {
                    System.out.print(proposition1[a]);
                }
            System.out.println(" -> Résultat : " + res1);
            System.out.println("");

            // Si l'utilisateur trouve la réponse alors il gagne sinon l'ia propose une combinaison

            for (int g = 0; g < GameProperties.NOMBRE_CHIFFRES; g++){           // Condition qui teste si la combinaison est trouvée
                if (res1.charAt(g) == '=') {
                    nombresEgale1++;
                }
            }
                if (nombresEgale1 == GameProperties.NOMBRE_CHIFFRES) {
                    humanWinner = true;             // L'utilisateur gagne
                    }else {

                    nombresEgale2 =0;               // On réinitialise la variable qui teste si la comparaison est positif

                    proposition2 = attaquant2.propositionCombinaison(nP);                   // Proposition de l'ia
                    String res2 = CommonMethods.compare(combinaison1, proposition2);        // Comparaison des 2 combinaisons
                    System.out.print("Proposition Ia numéro "+nombreProposition+": ");

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





          // Partie terminée, on affiche si la combinaison à été trouvé et qui est le vainqueur.
            if (humanWinner) {
                System.out.println("Vous avez trouvé la combinaison de l'intelligence artificielle");
                System.out.println("Félicitation !");
                System.out.println("");
                }else {
                    System.out.println("L'intelligence artificielle a trouvée votre combinaison.'");
                    System.out.println( "Dommage !");
                    System.out.println("");
            }

        askMode = CommonMethods.AskGame();      // Poser la question sur une nouvelle partie
        runGameMode(modeDev);                   // Retourner au menu
    }

}