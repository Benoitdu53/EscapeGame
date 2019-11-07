package com.ocr.escapegame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class HumanMethods implements IfDefenseur, IfAttaquant {
    private static final Logger logger = LogManager.getLogger(HumanMethods.class);
    Scanner sc = new Scanner(System.in);

    private int[] derProposition = new int[GameProperties.NOMBRE_CHIFFRES];          // Tableau de la dernière proposition
    int[] combinaison = new int[GameProperties.NOMBRE_CHIFFRES];                     // Tableau de la combinaison générée par le défenseur

    /**
     * L'humain génère une combinaison en tant que défenseur
     * @return la combinaison générée
     */
    @Override
    public int[] generateCombinaison() {
        String pU;                                      // Saisie utilisateur
        boolean Ucombi;                                 // Pour valider la bonne saisie de l'utilisateur
        int temp [] = new int [GameProperties.NOMBRE_CHIFFRES];
        int[] userCombinaison = new int[GameProperties.NOMBRE_CHIFFRES];             // Tableau de la combinaison générée ou proposé
        int nombreChiffres = GameProperties.NOMBRE_CHIFFRES - 1;

        do {
            try {
                pU = sc.nextLine();
                // On la convertie en int
                int userCombi = Integer.parseInt(pU);
                String testChiffres = pU + " ";
                // On ajoute un " " pour pouvoir récupérer la place du dernière élément et ainsi s'assuré du nombre de charactère saisie par l'utilisateur
                int lastChiffres = testChiffres.lastIndexOf(" ");

                if (lastChiffres == GameProperties.NOMBRE_CHIFFRES) {

                    for (int a=0;a < GameProperties.NOMBRE_CHIFFRES; a++){
                        temp [a] = userCombi%10;
                        userCombi=userCombi/10;

                        userCombinaison [nombreChiffres] = temp [a];
                        nombreChiffres--;
                    }

                    Ucombi = true;
                } else {
                    System.out.println("Vous n'avez pas saisie un nombres à "+GameProperties.NOMBRE_CHIFFRES+" chiffres");
                    Ucombi = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Vous n'avez pas saisie un nombres à "+GameProperties.NOMBRE_CHIFFRES+" chiffres");
                logger.info("L'utilisateur a mal saisie sa combinaison.");
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
        int nombreProposition = nP+1;

        System.out.println("Veuillez saisir une combinaison à "+GameProperties.NOMBRE_CHIFFRES+" chiffres ");

        System.out.println(" Votre proposition numéro : "+nombreProposition);
        if (nombreProposition == GameProperties.NOMBRE_ESSAIE){
            System.out.println("!! Dernière proposition !! ");
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
