package com.ocr.escapegame;


public interface IfAttaquant {
    /**
    L'attanquant doit proposer une combinaison pour trouver la combinaison générer par le défenseur
     */
    int[] propositionCombinaison(int nP);
    int[] recupererResults(int[] proposition); // Récupérer la dernière proposition de l'attaquant
    int[] recupererReponse(int[] combinaison); // Récupérer la combinaison du défenseur
}
