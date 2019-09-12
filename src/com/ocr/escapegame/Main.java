package com.ocr.escapegame;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    GameMode gameMode = new GameMode();
	    gameMode.displayGameMode();
	    Scanner sc = new Scanner(System.in);
		int nbMode = sc.nextInt();
		gameMode.displaySelectedMode(nbMode);
    }
}
