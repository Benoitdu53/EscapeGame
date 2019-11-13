package com.ocr.escapegame;

public class Main {

    public static void main(String[] args) {
        boolean modeDev =false;     // Variable qui indique sir le Mode dev est activé

        if(args.length==1 &&args[0].equals("True")){
            modeDev= true;
        }
            GameMode gameMode = new GameMode();
            gameMode.runGameMode(modeDev);

        }
}