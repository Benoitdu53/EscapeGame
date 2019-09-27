package com.ocr.escapegame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class MethodGame {
    private static final Logger logger = LogManager.getLogger(MethodGame.class);
    /**
     * Saisie d'un nombre obligatoire
     * @return
     */

    public static int[] saisirCombinaison() {
        Scanner sc = new Scanner(System.in);
        int [] userCombinaison = new int[4];
        String pU;
        int p1,p2,p3,p4,pt;
        boolean Ucombi;

        do {
            // On récupère la saisie Utilisateur
            try {
                pU = sc.nextLine();
                // On la convertie en int
                int userCombi = Integer.parseInt(pU);
                String testChiffres = pU + " ";
                // J'ajoute un " " pour pouvoir récupérer la place du dernière élément et ainsi m'assure du nombre de charactère saisie par l'utilisateur
                int lastChiffres = testChiffres.lastIndexOf(" ");

                if (lastChiffres == 4) {
                    //On récupère les chiffres de la saisie et on les positionne dans le tableau
                    p1 = userCombi / 1000;
                    pt = userCombi - (p1 * 1000);
                    p2 = pt / 100;
                    pt = pt - (p2 * 100);
                    p3 = pt / 10;
                    p4 = pt - (p3 * 10);

                    userCombinaison[0] = p1;
                    userCombinaison[1] = p2;
                    userCombinaison[2] = p3;
                    userCombinaison[3] = p4;

                    Ucombi = true;
                } else {
                    System.out.println("Vous n'avez pas saisie un nombres à 4 chiffres");
                    Ucombi = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Vous n'avez pas saisie un nombres à 4 chiffres");
                Ucombi = false;
            }

        }while (!Ucombi);

        logger.trace("Sortie méthode");
        return userCombinaison;
    }

    /**
     * Comparaison des deux combinaisons
     * @param IaCombinaison combinaison Ia
     * @param propositionUser combinaison User
     * @return la réponse à l'utilisateur "-=-+"
     */
        public static String[] comparerCombinaisonIaUser(int[] propositionUser, int[] IaCombinaison){
                char [] userReponse = new char[4];
                String [] reponse = new String[4];

                    for ( int m =0; m <4 ; m++){

                        if (IaCombinaison[m] == propositionUser[m]) {
                            userReponse[m] = '=';
                        } else if (IaCombinaison[m] < propositionUser[m]){
                            userReponse [m] = '-';
                        }
                        else if (IaCombinaison[m] > propositionUser[m]){
                            userReponse [m] = '+';
                        }
                        reponse [m] = Character.toString(userReponse[m]);
                    }
            return reponse;
        }

    /**
     * Comparaison de l'Ia sur la combinaison de l'user
     *      * @param userCombinaison combinaison de l'user
     *      * @param newSaisie combinaison Ia
     *      * @return la réponse à l'utilisateur "-=-+"
     */
    public static String[] comparerCombinaisonUserIa(int[] userCombinaison, int[] newSaisieIa){
        char [] userReponse = new char[4];
        String [] reponse = new String[4];

        for ( int m =0; m <4 ; m++){
            if (userCombinaison[m] == newSaisieIa[m]) {
                userReponse[m] = '=';
            } else if (userCombinaison[m] < newSaisieIa[m]){
                userReponse [m] = '-';
            }
            else if (userCombinaison[m] > newSaisieIa[m]){
                userReponse [m] = '+';
            }
            reponse [m] = Character.toString(userReponse[m]);
        }
        return reponse;
    }

    /**
     * Quitter l'application
     */
    public static void disconnect() {
        System.out.println(" Au revoir et à bientôt !");
        System.exit(0);
    }
}