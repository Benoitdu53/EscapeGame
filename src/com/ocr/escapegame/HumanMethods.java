package com.ocr.escapegame;

import java.util.Scanner;

public class HumanMethods implements IfDefenseur, IfAttaquant {
    Scanner sc = new Scanner(System.in);
    private int[] derProposition = new int[4];          // Tableau de la dernière proposition
    int[] combinaison = new int[4];                     // Tableau de la combinaison générée par le défenseur

    /**
     * L'humain génère une combinaison en tant que défenseur
     * @return la combinaison générée
     */
    @Override
    public int[] generateCombinaison() {
        int p1, p2, p3, p4, pt;                         // Variables pour récupérer les chiffres 1 par 1
        String pU;                                      // Saisie utilisateur
        boolean Ucombi;                                 // Pour valider la bonne saisie de l'utilisateur
        int[] userCombinaison = new int[4];             // Tableau de la combinaison générée ou proposé

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

    /**
     * Proposition de l'humain en tant qu'attanquant
     * @param nP Nombres de proposition ( Pas utilisé )
     * @return la proposition de l'utilisateur
     */
    @Override
    public int[] propositionCombinaison(int nP) {
        int[] userProposition;

        System.out.println("Veuillez saisir une combinaison à 4 chiffres ");

        switch (nP){
            case 0 : System.out.println("Saississez votre première proposition");
                break;
            case 1 : System.out.println("Saississez votre deuxième proposition");
                break;
            case 2 : System.out.println("Saississez votre troisème proposition");
                break;
            case 3 : System.out.println("Saississez votre quatrième proposition");
                break;
        }
        userProposition = this.generateCombinaison();

        return userProposition;
    }

    /**
     * On récupère la dernière proposition de l'utilisateur
     * @param proposition Dernière proposition
     * @return la dernière proposition
     */
    @Override
    public int[] recupererResults(int[] proposition) {
        this.derProposition = proposition;
        return derProposition;
    }

    /**
     * On récupère la combinaison générer par l'ia pour proposer une combinaison
     * @param combinaison Combinaison générée du défenseur
     * @return Combinaison générée du défenseur
     */
    @Override
    public int[] recupererReponse(int[] combinaison) {
        this.combinaison = combinaison;
        return combinaison;
    }

}
