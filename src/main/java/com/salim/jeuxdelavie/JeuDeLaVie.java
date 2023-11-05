package com.salim.jeuxdelavie;

public class JeuDeLaVie {

    public static void main(String[] args) {
        int rows = 10; // Nombre de lignes
        int cols = 10; // Nombre de colonnes
        int generations = 10; // Nombre de générations à simuler

        int[][] grid = new int[rows][cols];

        // Initialisez l'état initial ici (0 pour cellule morte, 1 pour cellule vivante)
        // Exemple : Faites clignoter un motif "Glider"
        grid[1][2] = 1;
        grid[2][3] = 1;
        grid[3][1] = 1;
        grid[3][2] = 1;
        grid[3][3] = 1;

        System.out.println("Generation 0:");
        afficherGrille(grid);

        for (int gen = 1; gen <= generations; gen++) {
            grid = calculerGenerationSuivante(grid);
            System.out.println("Generation " + gen + ":");
            afficherGrille(grid);
        }
    }

    // Méthode pour calculer la génération suivante
    public static int[][] calculerGenerationSuivante(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] nouvelleGeneration = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int voisinsVivants = compterVoisinsVivants(grid, i, j);
                if (grid[i][j] == 1) {
                    if (voisinsVivants < 2 || voisinsVivants > 3) {
                        nouvelleGeneration[i][j] = 0; // La cellule meurt
                    } else {
                        nouvelleGeneration[i][j] = 1; // La cellule reste en vie
                    }
                } else {
                    if (voisinsVivants == 3) {
                        nouvelleGeneration[i][j] = 1; // Une nouvelle cellule naît
                    }
                }
            }
        }
        return nouvelleGeneration;
    }

    // Méthode pour compter le nombre de voisins vivants d'une cellule
    public static int compterVoisinsVivants(int[][] grid, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;
        int voisinsVivants = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    voisinsVivants += grid[newRow][newCol];
                }
            }
        }
        voisinsVivants -= grid[row][col]; // Soustraire la cellule elle-même car elle a été comptée précédemment
        return voisinsVivants;
    }

    // Méthode pour afficher la grille
    public static void afficherGrille(int[][] grid) {
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print((cell == 1) ? "O " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
