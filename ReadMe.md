Développeur d’application JAVA OpenClassrooms
Projet 3 : Mettez votre logique à l’épreuve

Présentation de l’application :

		L’application contient le jeu « Escape Game » .

	Dans Escape Game, il y a 3 modes de jeu :

			Mode Challenger : Vous devez trouver la combinaison de X chiffres générée par l’intelligence artificielle, vous avez X chances.

			Mode Défenseur : Vous générez une combinaison de X chiffres que l’intelligence artificielle doit trouver en X chances.

			Mode Duel : Vous et l’intelligence artificielle générés une combinaison et à tour de rôle vous devez trouver la combinaison de l’adversaire. 

	( X est donné au début de chaque partie. )


Développement de l’application :

	L’application à été développée avec IntelliJ IDEA CE et Java 12 version 12.0.1.
	Utilisation de Apache Log4j pour la gestion des lois.


Utilisation de l’application :

	Changez les propriétés :

		Pour changer les propriétés aller dans le fichier config.properties et effectuer les changements que vous souhaitez.

		- Nombre de chiffres dans la combinaison : 	nombre.chiffre.combinaison
		- Nombre d’essais : 	nombre.essaie
		- Activer le mode développeur : 		mode.dev 	( Ce mode permet d’afficher la solution en début de partie )


Lancement de l’application :

	Importez, si ce n'est pas fait, l'ensemble du repertoire depuis Github.

		- Naviguer via le terminal jusqu'au fichier EscapeGame.jar qui se trouve dans /EscapeGame/out/artifacts/EscapeGame_jar/.
		- Écrivez ensuite la commande qui permet de lancer l'application :
			- java -jar EscapeGame.jar 
			- java -jar EscapeGame.jar true ( Pour le lancer en mode développeur).

Version 1.0

Auteur : Benoit Brichet 
