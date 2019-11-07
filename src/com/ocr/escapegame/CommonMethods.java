package com.ocr.escapegame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CommonMethods {
    private static final Logger logger = LogManager.getLogger(CommonMethods.class);

    //Affiche les règles du mode Challenger
    public static void afficheRulesChallengerMode(){
        System.out.println("Vous avez choisi comme mode de jeu : Challenger");
        System.out.println("");
        System.out.println("Principe du jeu en mode Challenger :");
        System.out.println("Vous devez trouver la combinaison à X chiffres choisis par l'intelligence artificielle.");
        System.out.println("A chaque proposition,");
        System.out.println("\t si votre chiffre est supérieur au chiffre à trouver alors '-' apparaîtra,");
        System.out.println("\t si votre chiffre est inférieur au chiffre à trouver alors '+' apparaîtra,");
        System.out.println("\t si votre chiffre est égale au chiffre à trouver alors '=' apparaîtra.");
        System.out.println("Nombre de chiffres dans la combinaison : "+GameProperties.NOMBRE_CHIFFRES);
        System.out.println("Nombre d'essais : "+GameProperties.NOMBRE_ESSAIE);
        System.out.println("Vous avez "+GameProperties.NOMBRE_ESSAIE+" chances ! Bon courage !!");
        System.out.println("");
        System.out.println("L'Intelligence artificielle définit aléatoirement une combinaison...");
    }
    //Affiche les règles du mode Défenseur
    public static void afficheRulesDefenseurMode(){
        System.out.println("Vous avez choisi comme mode de jeu : Défenseur");
        System.out.println("");
        System.out.println("Principe du jeu en mode Défenseur :");
        System.out.println("Vous devez choisir la combinaison à X chiffres et l'intelligence artificielle à "+GameProperties.NOMBRE_ESSAIE+" chances de la trouvée.");
        System.out.println("A chaque proposition,");
        System.out.println("\t si son chiffre est supérieur au chiffre à trouver alors '-' apparaîtra,");
        System.out.println("\t si son chiffre est inférieur au chiffre à trouver alors '+' apparaîtra,");
        System.out.println("\t si son chiffre est égale au chiffre à trouver alors '=' apparaîtra.");
        System.out.println("Nombre de chiffres dans la combinaison : "+GameProperties.NOMBRE_CHIFFRES);
        System.out.println("Nombre d'essais : "+GameProperties.NOMBRE_ESSAIE);
        System.out.println("Amusez vous bien !!");
        System.out.println("");
        System.out.println("Veuillez générez une combinaison à "+GameProperties.NOMBRE_CHIFFRES+" chiffres ");
    }
    //Affiche les règles du mode Duel
    public static void afficheRulesDuelMode(){
        System.out.println("Vous avez choisi comme mode de jeu : Duel");
        System.out.println("");
        System.out.println("Choississez une combinaison secrète et l'intelligence artificielle en fait de même.");
        System.out.println("");
        System.out.println("Principe du jeu en mode Duel :");
        System.out.println("A tour de rôle, vous essayez de trouver la combinaison de l'Intelligence artificielle et l'ia essaye à son tour.");
        System.out.println("A chaque proposition,");
        System.out.println("\t si votre chiffre est supérieur au chiffre à trouver alors '-' apparaîtra,");
        System.out.println("\t si votre chiffre est inférieur au chiffre à trouver alors '+' apparaîtra,");
        System.out.println("\t si votre chiffre est égale au chiffre à trouver alors '=' apparaîtra.");
        System.out.println("Nombre de chiffres dans la combinaison : "+GameProperties.NOMBRE_CHIFFRES);
        System.out.println("Le premier à trouver la combinaison de l'autre à gagné !");
        System.out.println("Bonne chance !");
    }

    /**
     * Comparer les 2 combinaisons
     * @param generateCombi     Combinaison du défenseur
     * @param propositionCombi  Combinaison de l'attaquant
     * @return  Le résultat en String de la comparaison
     */
    public static String compare(int[] generateCombi, int[] propositionCombi) {
        StringBuilder Results = new StringBuilder();

        for ( int m =0; m <GameProperties.NOMBRE_CHIFFRES  ; m++){
            if (generateCombi[m] == propositionCombi[m]){
                Results.append('=');
            } else if (generateCombi[m] < propositionCombi[m]){
                Results.append('-');
            }
            else if (generateCombi[m] > propositionCombi[m]){
                Results.append('+');
            }
        }

        return Results.toString();
    }

    /**
     * Afficher le choix du joueur après la partie
     * @return Choix du mode de jeu
     */
    public static int AskGame(){
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
                    logger.info("L'utilisateur n'a pas saisie un chiffre.");
                    reponseIsGood = false;
                }
            } while (!reponseIsGood);
            System.out.println("");
        } while (choixMode < 1 || choixMode > 3);
            return choixMode; // On retourne le choix de l'utilisateur
    }

    /**
     *  Quitte l'application
     */
    public static void disconnect() {
        System.out.println(" Au revoir et à bientôt !");
        System.out.println("");
        System.exit(0);
    }
}
