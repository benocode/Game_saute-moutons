package fr.benocode.apprentissage;

import java.util.Arrays;
import java.util.Scanner;

public class App {

    /** Déclaration des variables de classe */
    public static final char MOUTAV = '>';
    public static final char MOUTREC = '<';
    public static final char VIDE = '_';
    public static int mouvement;
    public static int statut;
    public static int choix;
    public static char grille[] = new char[7];
    public static char mouton;
    public static int sens;

    public static void main(String[] args) {

        /** Déclaration des variables de la classe main */
        String nouvellePartie;
        Scanner scanner = new Scanner(System.in);

        /** Règles du jeu */
        System.out.println("Vous jouez au jeu du saute-mouton !");
        System.out.println("L'objectif est de faire passer tous les moutons d'un côté à l'autre soit :");
        System.out.println("\t- en avançant d'une case si celle-ci est vide");
        System.out
                .println("\t- en sautant au-dessus d'un mouton qui va dans le sens opposé si la case d'après est vide");
        System.out.println("Le jeu s'arrête une fois que tous les moutons sont inversés ou que vous êtes bloqué.");

        do {

            /** Initialisation du plateau de jeu */
            initialiserGrille(grille);
            mouton = MOUTAV;
            sens = 1;

            do {

                /** Affichage du plateau de jeu */
                affichage(grille);

                /** Jouer un tour */
                System.out.println(
                        "Choisir le numéro d'un mouton " + mouton + " pour le faire avancer ou sauter sur une case "
                                + VIDE);
                choix = Integer.parseInt(scanner.nextLine());

                // Vérifie que le mouvement est possible, renvoie un message et le nombre de
                // case à avancer
                controleMvt(choix, sens, grille, mouton);

                // Le mouton avance
                if (mouvement > 0) {
                    grille[choix] = VIDE;
                    grille[choix + (mouvement * sens)] = mouton;
                }

                /** Changement de variables pour le joueur 2 */
                sens = sens * -1;
                if (mouton == MOUTAV) {
                    mouton = MOUTREC;
                } else {
                    mouton = MOUTAV;
                }

                /** Vérification de la grille */
                validation(grille);

            } while (statut == 0);

            affichage(grille);

            /** Déclaration du résultat */
            if (statut == 1) {

                System.out.println("Bravo, vous avez réussi !");
            } else {
                System.out.println("Vous êtes bloqué !");
            }

            /** Rejouer */
            System.out.println();
            System.out.println("Une nouvelle partie ? Ecrire oui pour recommencer.");
            nouvellePartie = scanner.nextLine();

        } while (nouvellePartie != "oui");

        scanner.close();
    }

    public static char[] initialiserGrille(char grille[]) {

        /** Fonction pour reboot le jeu */
        Arrays.fill(grille, 0, 3, MOUTAV);
        grille[3] = VIDE;
        Arrays.fill(grille, 4, 7, MOUTREC);

        return grille;
    }

    public static void affichage(char grille[]) {
        int[] numMoutons = { 0, 1, 2, 3, 4, 5, 6 };

        /** Affichage du plateau de jeu */
        System.out.println();
        System.out.println("Voici nos moutons :");
        System.out.println(Arrays.toString(numMoutons));
        System.out.println(Arrays.toString(grille));

    }

    public static int controleMvt(int choix, int sens, char grille[], char mouton) {
        String message;

        /**
         * Vérification si le mouvement est possible et valide le nombre de case à
         * avancer
         */
        if (choix >= 0 && choix <= 6 && !(choix + 1 * sens > 6 || choix + 1 * sens < 0)
                && grille[choix] == mouton) {
            if (grille[choix + (sens * 1)] == VIDE) {
                mouvement = 1;
            } else {
                if (!(choix + 2 * sens > 6 || choix + 2 * sens < 0) && grille[choix + (sens * 2)] == VIDE
                        && ((grille[choix + (sens * 1)] == MOUTAV && sens == -1)
                                || (grille[choix + (sens * 1)] == MOUTREC && sens == 1))) {
                    mouvement = 2;
                } else {
                    mouvement = 0;
                }
            }
        } else {
            mouvement = 0;
        }

        switch (mouvement) {
            case 0 -> message = "Ah non je ne peux pas";
            case 1 -> message = "J'avance.... beeeeeeh";
            case 2 -> message = "Je saute, hop.... beeeeeeh";
            default -> message = "Félicitations : c'est un bug !";
        }

        System.out.println(message);

        return mouvement;
    }

    public static int validation(char grille[]) {

        /** Vérifie si le joueur a gagné ou est bloqué */
        // char[] grilleValide = { '<', '<', '<', '_', '>', '>', '>' };
        char[] grilleValide = { '>', '>', '_', '>', '<', '<', '<' };
        if (Arrays.equals(grilleValide, grille)) {
            statut = 1;
        } else if ((grille[0] == MOUTAV && grille[1] == MOUTREC && grille[2] == MOUTREC) ||
                (grille[0] == MOUTAV && grille[1] == MOUTAV && grille[2] == MOUTREC && grille[3] == MOUTREC) ||
                (grille[1] == MOUTAV && grille[2] == MOUTAV && grille[3] == MOUTREC && grille[4] == MOUTREC) ||
                (grille[2] == MOUTAV && grille[3] == MOUTAV && grille[4] == MOUTREC && grille[5] == MOUTREC) ||
                (grille[3] == MOUTAV && grille[4] == MOUTAV && grille[5] == MOUTREC && grille[6] == MOUTREC) ||
                (grille[4] == MOUTAV && grille[5] == MOUTAV && grille[6] == MOUTREC)) {
            statut = -1;
        } else {
            statut = 0;
        }

        return statut;
    }

}