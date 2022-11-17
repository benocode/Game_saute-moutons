package fr.benocode.apprentissage;

import java.util.Arrays;

public class App {

    /** Déclaration des constantes */
    public static final char MOUTAV = '>';
    public static final char MOUTREC = '<';
    public static final char VIDE = '_';

    public static void main(String[] args) {

        /** Déclaration des variables */
        int choix;
        char grille[] = new char[7];
        char mouton;
        int sens;
        int mouvement;
        int statut;
        String nouvellePartie;

        /** Règles du jeu */
        System.out.println("Vous jouez au jeu du saute-mouton !");
        System.out.println("L'objectif est de faire passer tous les moutons d'un côté à l'autre soit :");
        System.out.println("    - en avançant d'une case si celle-ci est vide");
        System.out.println(
                "    - en sautant au-dessus d'un mouton qui va dans le sens opposé si la case d'après est vide");
        System.out.println("Le jeu s'arrête une fois que tous les moutons sont inversés ou que vous êtes bloqué.");

        /** Initialisation du plateau de jeu */
        initialiserGrille(grille);
        affichage(grille);

    }

    public static char[] initialiserGrille(char grille[]) {

        /** Fonction pour reboot le jeu */
        Arrays.fill(grille, 0, 3, MOUTAV);
        grille[3] = VIDE;
        Arrays.fill(grille, 4, 7, MOUTREC);

        return grille;
    }

    public static void affichage(char grille[]) {

        /** Affichage du plateau de jeu */
        System.out.println();
        System.out.println("Voici nos moutons :");
        System.out.println(Arrays.toString(grille));

    }

}