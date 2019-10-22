package com.ocr.escapegame;


public interface IfAttaquant {
    /*
    L'attanquant doit proposer une combinaison pour trouver la combinaison générer par le défenseur
     */
    int[] propositionCombinaison();
    void AffResults(int nbMode);
}
