package com.ocr.escapegame;

public class IaMethods implements IfDefenseur, IfAttaquant {
    // L'Ia propose une combinaison
    @Override
    public int[] propositionCombinaison() {

        return null;
    }

    // L'Ia génère une combinaison
    @Override
    public int[] generateCombinaison() {
        int[] IaGenerate = new int[4];
        // L'Ia définit sa combinaison
        System.out.println("");
        System.out.println("L'Intelligence artificielle définit aléatoirement une combinaison...");
        System.out.println("");
        for (int i = 0; i < 4; i++) {
            int numCombi = (int) (Math.random() * 10);
            IaGenerate[i] = numCombi;
            System.out.print(IaGenerate[i]);
        }
        System.out.println("");
        return IaGenerate;
    }

    // Affiche le résultat suite à une comparaison
    @Override
    public void AffResults(int nbMode) {
        System.out.println("Dommage");

    }
}
