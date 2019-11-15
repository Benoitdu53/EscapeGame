package com.ocr.escapegame;

public class Main {

    public static boolean modeDevargs;        // Variable qui indique sir le Mode dev est activé


    public static void main(String[] args) {

        if(args.length==1 && args[0].equals("true") || GameProperties.MODE_DEV){
            modeDevargs= true;
        }
            GameMode gameMode = new GameMode();
            gameMode.runGameMode();

        }
}


