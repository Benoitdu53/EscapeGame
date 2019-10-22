package com.ocr.escapegame;

import java.util.Scanner;

public class HumanMethods implements IfDefenseur, IfAttaquant {
    Scanner sc = new Scanner(System.in);

    // L'humain génère une combinaison en tant que défenseur
    @Override
    public int[] generateCombinaison() {
        int p1, p2, p3, p4, pt;
        String pU;
        boolean Ucombi;
        int[] userCombinaison = new int[4];

        do {
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
        return userCombinaison;
    }

    // Proposition de l'humain en tant qu'attanquant
    @Override
    public int[] propositionCombinaison() {
        int[] userProposition;

        System.out.println("Veuillez saisir une combinaison à 4 chiffres ");
        userProposition = this.generateCombinaison();

        return userProposition;
    }

    @Override
    public void AffResults(int nbMode) {
        System.out.println("Bravo");
    }

}
