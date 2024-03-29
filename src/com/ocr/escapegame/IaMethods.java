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
    int[] b = new int [GameProperties.NOMBRE_CHIFFRES];                      // Tableau de l'intervalle supérieur

    /**
     *  Proposition d'une combinaison de l'ia
     * @param nP Le nombres de propositions
     * @return La nouvelle proposition de l'ia
     */
    @Override
    public int[] propositionCombinaison(int nP) {

        System.out . print (" Appuyez sur la touche Entrée pour continuer " ) ;
        waitForKeypress.nextLine ();

        if (nP == 0){                                                   // Si c'est la première propisition de l'IA
            for (int a=0;a<GameProperties.NOMBRE_CHIFFRES; a++){        // On met le chiffre supérieur de l'intervalle à 10
                b[a]=10;
            }
            iaCombinaison = this.generateCombinaison();                 // Et on définit aléatoirement une combinaison au hasard

            }else {                                                 // Sinon l'ia propose une combinaison en fonction du dernier résultat
            derProposition = recupererResults(derProposition);      // et de la combinaison générer par l'utilisateur
            combinaison =recupererReponse(combinaison);

            for (int c=0 ; c<GameProperties.NOMBRE_CHIFFRES; c++) {
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
        int[] IaGenerate = new int[GameProperties.NOMBRE_CHIFFRES];
        // L'Ia définit sa combinaison
        for (int i = 0; i < GameProperties.NOMBRE_CHIFFRES; i++) {
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

    /**
     * On récupère l'identité de l'attaquant
     * @param roleAtt Identité de l'attaquant
     * @return  L'identité de l'attaquant
     */
    @Override
    public String roleAttaquant(String roleAtt) {
        roleAtt = "Ia";
        return roleAtt;
    }
}
