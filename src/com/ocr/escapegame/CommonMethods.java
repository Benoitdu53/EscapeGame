package com.ocr.escapegame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        System.out.println("Vous avez 4 chances ! Bon courage !!");
    }
    //Affiche les règles du mode Défenseur
    public static void afficheRulesDefenseurMode(){
        System.out.println("Vous avez choisi comme mode de jeu : Défenseur");
        System.out.println("");
        System.out.println("Principe du jeu en mode Défenseur :");
        System.out.println("Vous devez choisir la combinaison à X chiffres et l'intelligence artificielle à 4 chances de la trouvée.");
        System.out.println("A chaque proposition,");
        System.out.println("\t si son chiffre est supérieur au chiffre à trouver alors '-' apparaîtra,");
        System.out.println("\t si son chiffre est inférieur au chiffre à trouver alors '+' apparaîtra,");
        System.out.println("\t si son chiffre est égale au chiffre à trouver alors '=' apparaîtra.");
        System.out.println("Amusez vous bien !!");
        System.out.println("");
        System.out.println("");
        System.out.println("Veuillez générez une combinaison à 4 chiffres ");
    }
    //Affiche les règles du mode Duel
    public static void afficheRulesDuelMode(){
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
    }

    // Comparer les 2 combinaisons
    public static String compare(int[] generateCombi, int[] propositionCombi) {
        char [] Reponse = new char[4];
        String Results ="";

        for ( int m =0; m <4 ; m++){
            if (generateCombi[m] == propositionCombi[m]){
                Results += '=';
            } else if (generateCombi[m] < propositionCombi[m]){
                Results += '-';
            }
            else if (generateCombi[m] > propositionCombi[m]){
                Results += '+';
            }
        }

        return Results;
    }

    //Afficher le choix du joueur après la partie

    // Quitte l'application
    public static void disconnect() {
        System.out.println(" Au revoir et à bientôt !");
        System.exit(0);
    }
}
