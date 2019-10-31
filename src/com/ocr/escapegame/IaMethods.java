package com.ocr.escapegame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class IaMethods implements IfDefenseur, IfAttaquant {
    private static final Logger logger = LogManager.getLogger(IaMethods.class);
    Scanner waitForKeypress = new Scanner ( System.in );

    int[] iaCombinaison = new int[GameProperties.NOMBRE_CHIFFRES];           // Tableau de la nouvelle combinaison
    int[] derProposition = new int[GameProperties.NOMBRE_CHIFFRES];          // Tableau de la dernière combinaison
    int[] combinaison = new int[GameProperties.NOMBRE_CHIFFRES];             // Tableau de la combinaison du défenseur
    int[] a = new int [GameProperties.NOMBRE_CHIFFRES];                      // Tableau de l'intervalle inférieur
    int[] b = {10,10,10,10};                    // Tableau de l'intervalle supérieur

    /**
     *  Proposition d'une combinaison de l'ia
     * @param nP Nombres de propositions
     * @return La nouvelle proposition de l'ia
     */
    @Override
    public int[] propositionCombinaison(int nP) {

        switch (nP){

            case 0 : // Si c'est la première proposition
                System.out . print (" Appuyez sur la touche Entrée pour continuer " ) ;
                waitForKeypress.nextLine ();System.out.println("Première proposition de l'ia");
                break;
            case 1 : // Si c'est la deuxième proposition
                System.out . print (" Appuyez sur la touche Entrée pour continuer " ) ;
                waitForKeypress.nextLine ();
                System.out.println("Deuxième proposition de l'ia");
                break;
            case 2 : // Si c'est la troisième proposition
                System.out . print (" Appuyez sur la touche Entrée pour continuer " ) ;
                waitForKeypress.nextLine ();
                System.out.println("Troisième proposition de l'ia");
                break;
            case 3 : // Si c'est la quatrième proposition
                System.out . print (" Appuyez sur la touche Entrée pour continuer " ) ;
                waitForKeypress.nextLine ();
                System.out.println("Quatrième et proposition de l'ia");
                break;
        }

        if (nP == 0){
            iaCombinaison[0] = iaCombinaison[1] = iaCombinaison[2] = iaCombinaison[3] = 5;      // Si c'est la première proposition l'ia propose 5555
            }else {                                                 // Sinon l'ia propose une combinaison en fonction du dernier résultat
            derProposition = recupererResults(derProposition);      // et de la combinaison générer par l'utilisateur
            combinaison =recupererReponse(combinaison);

            for (int c=0 ; c<4; c++) {
                if (combinaison[c] < derProposition[c]) {           // Si la proposition est inférieur à la combinaison
                    b[c] = iaCombinaison[c];                        // Le chiffre maximum de l'intervalle est égale au chiffre de la dernière proposition
                    iaCombinaison[c] = (a[c] + b[c]) / 2;           // Le chiffre de la nouvelle combinaison est choisit aléatoirement dans l'invervalle a et b
                } else if (combinaison[c] > derProposition[c]) {    // Si la proposition est supérieur à la combinaison
                    a[c] = iaCombinaison[c];                        // Le chiffre minimum de l'intervalle est égale au chiffre de la dernière proposition
                    iaCombinaison[c] = (a[c] + b[c]) / 2;           // Le chiffre de la nouvelle combinaison est choisit aléatoirement dans l'invervalle a et b
                }
            }
        }
        return iaCombinaison;       // On retourne la combinaison proposer par l'ia
    }

    /**
     * L'Ia génère une combinaison
     * @return La combinaison générée de l'ia
     */
    @Override
    public int[] generateCombinaison() {
        int[] IaGenerate = new int[4];
        // L'Ia définit sa combinaison
        System.out.println("");
        System.out.println("L'Intelligence artificielle définit aléatoirement une combinaison...");
        for (int i = 0; i < 4; i++) {
            int numCombi = (int) (Math.random() * 10);
            IaGenerate[i] = numCombi;
        }

        System.out.println("");
        return IaGenerate;
    }

    /**
     * On récupère la dernière proposition de l'ia pour en proposer une nouvelle
     * @param proposition La dernière proposition
     * @return La dernière proposition
     */
    @Override
    public int [] recupererResults(int[] proposition) {
        this.derProposition = proposition;
        return derProposition;
    }

    /**
     * On récupère la combinaison générer par l'utilisateur pour proposer une combinaison
     * @param combinaison La combinaison de l'utilisateur
     * @return La combinaison de l'utilisateur
     */
    @Override
    public int [] recupererReponse(int[] combinaison) {
        this.combinaison = combinaison;
        return combinaison;
    }
}
