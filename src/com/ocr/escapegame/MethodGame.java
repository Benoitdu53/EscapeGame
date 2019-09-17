package com.ocr.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MethodGame {
    /**
     * Saisie d'un nombre obligatoire
     */
    // TODO Erreur, à voir, il demande 2 fois minimum la saisie de la combinaison par l'utilisateur
        public static int saisirCombinaison() {
            Scanner sc = new Scanner(System.in);
            int userCombinaison = 0;
            boolean Ucombi;
            do {
                System.out.println("DOoDOo");
                try {
                    //Saisie de l'utilisateur
                    int pU = sc.nextInt();
                    if (pU < 999 || pU > 10000) {
                        System.out.println("Vous n'avez pas saisie une combinaison à 4 chiffres");
                        Ucombi = false;
                    } else {
                        System.out.println("Vous avez bien saisie une combinaison à 4 chiffres");
                        Ucombi = true;
                        userCombinaison = pU;
                    }
                }catch (InputMismatchException e){
                    System.out.println("Vous n'avez pas saisie une combinaison à 4 chiffres");
                    sc.next();
                    Ucombi =false;
                }
                System.out.println(Ucombi);
            }while (!Ucombi);
            return userCombinaison;
        }
}
