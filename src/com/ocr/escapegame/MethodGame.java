package com.ocr.escapegame;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MethodGame {
    /**
     * Saisie d'un nombre obligatoire
     * @return
     */
    // TODO Erreur, à voir, il demande 2 fois minimum la saisie de la combinaison par l'utilisateur
    public static int[] saisirCombinaison() {
        Scanner sc = new Scanner(System.in);
        boolean Ucombi = false;
        int [] userCombinaison = new int [4];
        int p1,p2,p3,p4,pt,pU = 0;
        String Verify = null;
        do {
                try {
                    //Saisie de l'utilisateur
                    pU = sc.nextInt();

                Verify = String.valueOf(pU);

            if (Verify.length() >= 5 || Verify.length() <=3){
                System.out.println("Vous n'avez pas saisie une combinaison à 4 chiffres");
                Ucombi =false;
            }else {

                p1 = pU / 1000;
                pt = pU - (p1 * 1000);
                p2 = pt / 100;
                pt = pt - (p2 * 100);
                p3 = pt / 10;
                p4 = pt - (p3 * 10);

                userCombinaison[0] = p1;
                userCombinaison[1] = p2;
                userCombinaison[2] = p3;
                userCombinaison[3] = p4;

                Ucombi = true;
            }
                } catch (InputMismatchException e) {
                        System.out.println("Vous n'avez pas saisie une combinaison à 4 chiffres");
                        sc.next();
                        Ucombi = false;
                    }
            System.out.println(Ucombi);
        }while (!Ucombi);
        return userCombinaison;
    }
    /**
     * Comparaison des deux combinaisons
     * @param userCombinaison combinaison de l'utilisateur
     * @param iaCombinaison combinaison de l'ia
     * @return la réponse à l'utilisateur "-=-+"
     */
        public static String[] comparerCombinaison(int[] userCombinaison, int[] iaCombinaison){
                String combinaisonString = String.valueOf(userCombinaison);
                //char [] chCombinaison = new char[4];
                char [] userReponse = new char[4];
                String [] reponse = new String[4];
                    for ( int m =0; m <4 ; m++){
                        //chCombinaison[m] = combinaisonString.charAt(m);
                        //int ChiffreComparaison = Integer.parseInt(String.valueOf(chCombinaison[m]));
                        if (iaCombinaison[m] == userCombinaison[m]) {
                            userReponse[m] = '=';
                        } else if (iaCombinaison[m] < userCombinaison[m]){
                            userReponse [m] = '-';
                        }
                        else if (iaCombinaison[m] > userCombinaison[m]){
                            userReponse [m] = '+';
                        }
                        reponse [m] = Character.toString(userReponse[m]);
                    }
            return reponse;
        }
}
