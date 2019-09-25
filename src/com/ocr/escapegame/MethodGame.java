package com.ocr.escapegame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
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
                logger.error("Erreur de saisie : ");
                System.out.println("Vous n'avez pas saisie un nombres à 4 chiffres");
                Ucombi = false;
            }

        }while (!Ucombi);

        logger.trace("Sortie méthode");
        return userCombinaison;
    }

    /**
     * Comparaison des deux combinaisons
     * @param Combinaison1 combinaison
     * @param Combinaison2 combinaison
     * @return la réponse à l'utilisateur "-=-+"
     */
        public static String[] comparerCombinaison(int[] Combinaison1, int[] Combinaison2){
                char [] userReponse = new char[4];
                String [] reponse = new String[4];

                    for ( int m =0; m <4 ; m++){

                        if (Combinaison1[m] == Combinaison2[m]) {
                            userReponse[m] = '=';
                        } else if (Combinaison1[m] < Combinaison2[m]){
                            userReponse [m] = '-';
                        }
                        else if (Combinaison1[m] > Combinaison2[m]){
                            userReponse [m] = '+';
                        }
                        reponse [m] = Character.toString(userReponse[m]);
                    }
            return reponse;
        }

    /**
     * Nouvelle saisie de l'intelligence artificielle selon le résultat donnée d'une précédente comparaison
     * @param Combinaison1 Saisie User
     * @param Combinaison2 Saisie Ia
     */

    public static int[] newSaisieIa(int[] Combinaison1, int[] Combinaison2){
        int[] newSaisieIa = new int[4];
        Random random =new Random();

        // Boucle pour générer un nouveau tableau de saisie Ia en tenant compte des résultats précédent
        for (int b=0 ; b<4; b++){
            if (Combinaison2[b] < Combinaison1[b]){
                newSaisieIa[b] = Combinaison2[b]+random.nextInt(10 - Combinaison2[b]);
            } else if (Combinaison2[b] > Combinaison1[b]){
                newSaisieIa[b] = 0+random.nextInt(Combinaison2[b] - 0);
            }else if (Combinaison2[b] == Combinaison1[b]){
                newSaisieIa[b] = Combinaison2[b];
            }
        }
        return newSaisieIa;
    }

    /**
     * Quitter l'application
     */
    public static void disconnect() {
        System.out.println(" Au revoir et à bientôt !");
        System.exit(0);
    }
}