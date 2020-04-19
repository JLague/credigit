package encryption;

import java.util.Iterator;
import java.util.SortedSet;

/**
 * Classe utilitaires pour la gestion des matrices carrées
 *
 * @author Bank-era Corp.
 */
public class MatriceUtilitaires {
	/**
	 * Permet de produire une chaîne de caractères pour l'affichage d'une matrice N
	 * X M dans la console. Très utile pour faire des tests.
	 *
	 * <pre>
	 *  Exemple d'affichage voulu:
	 *
	 *  [6, 1, -5]
	 *  [-2, -5, 4]
	 *  [-3, 3, -1]
	 * </pre>
	 *
	 * @param mat la matrice N X M à afficher
	 *
	 * @return la chaîne de caractères
	 */
	// TODO toStringMat - Compléter le code de la méthode
	public static String toStringMat(int[][] mat) {
		String s = "";

		for (int i = 0; i < mat.length; i++) {
			s += "[";

			for (int j = 0; j < mat[i].length; j++) {
				s += mat[i][j];

				if (j != mat[i].length - 1) {
					s += ", ";
				}
			}

			s += "]\n";
		}

		return s;
	}

	/**
	 * Retourne la matrice carrée M X N transposée à partir d'une matrice carrée N X
	 * M reçue.
	 *
	 * La matrice transposée d'une matrice est obtenue en échangeant les lignes et
	 * les colonnes.
	 *
	 * @param mat la matrice d'origine
	 *
	 * @return la matrice carrée M X N transposée
	 */
	// TODO getMatTranspose - Compléter le code de la méthode
	public static int[][] getMatTranspose(int[][] mat) {
		int hauteur = mat.length;
		int largeur = mat[0].length;
		int[][] trans = new int[largeur][hauteur];

		for (int i = 0; i < hauteur; i++) {
			for (int j = 0; j < largeur; j++) {
				trans[j][i] = mat[i][j];
			}
		}

		return trans;
	}

	/**
	 * Retourne la matrice carrée (N-1) X (N-1) mineure d'une matrice carrée N X N
	 * reçue.
	 *
	 * Est la matrice carrée résultante, lorsque l'on supprime toutes les valeurs de
	 * la ligne et de la colonne reçues, à partir la matrice d'origine reçue.
	 *
	 * @param mat   la matrice d'origine
	 * @param ligne la ligne de valeurs à supprimer
	 * @param col   la colonne de valeurs à supprimer
	 *
	 * @return la matrice carrée (N-1) X (N-1) mineure résultante
	 *
	 */
	private static int[][] getMatMineur(int[][] mat, int ligne, int col) {
		int longueur = mat.length;
		int[][] mineur = new int[longueur - 1][longueur - 1];
		int x = 0;
		int y = 0;

		for (int i = 0; i < longueur; i++) {
			if (i != ligne) {
				for (int j = 0; j < longueur; j++) {
					if (j != col) {
						mineur[x][y] = mat[i][j];
						y++;
					}
				}
				y = 0;
				x++;
			}
		}

		return mineur;
	}

	/**
	 * Retourne une matrice N X M résultant de la multiplication d'un scalaire reçu
	 * avec chaque élément d'une matrice N X M reçue. Pas d'arrondi (partie entière)
	 *
	 * @param mat      la matrice d'origine
	 * @param scalaire le scalaire
	 *
	 * @return la matrice résultante de la multiplication avec un scalaire
	 */
	// TODO getMatMultScalaire - Compléter le code de la méthode
	public static int[][] getMatMultScalaire(int[][] mat, float scalaire) {
		int[][] matM = getMatCopieProfonde(mat);

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				matM[i][j] = (int) Math.floor(mat[i][j] * scalaire);
			}
		}

		return matM;
	}

	/**
	 * Retourne une matrice N X M résultant de l'application d'un modulo reçu, sur
	 * chaque élément d'une matrice N X M reçue. Utilise la méthode modulo de
	 * MathUtilitaires.
	 *
	 * @param mat la matrice d'origine
	 * @param mod le modulo à appliquer
	 *
	 * @return la matrice résultante de l'application d'un modulo
	 */
	// TODO getMatModuloX - Compléter le code de la méthode
	public static int[][] getMatModuloX(int[][] mat, int mod) {
		int[][] matMod = getMatCopieProfonde(mat);

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				matMod[i][j] = MathUtilitaires.modulo(matMod[i][j], mod);
			}
		}

		return matMod;
	}

	/**
	 * Calcule le déterminant d'une matrice carrée de N X N.
	 *
	 * ATTENTION ; Il existe plusieurs façons de calculer un déterminant, il faut
	 * faire de la recherche.
	 *
	 * Devrait être une méthode récursive...
	 *
	 * @param mat la matrice carrée pour les calculs.
	 *
	 * @return le déterminant de la matrice.
	 */
	// TODO getDeterminant - MANDAT 2 - Compléter le code de la méthode
	public static int getDeterminant(int[][] mat) {
		int rep = 0;

		if (mat.length == 1) {
			rep = mat[0][0];
		} else if (mat.length == 2) {
			// ad-bc
			rep = mat[0][0] * mat[1][1] - mat[0][1] * mat[1][0];
		} else {
			for (int i = 0; i < mat.length; i++) {
				rep += Math.pow(-1, i) * mat[i][0] * getDeterminant(getMatMineur(mat, i, 0));
			}
		}

		return rep;
	}

	/**
	 * Retourne la matrice carrée des cofacteurs N X N d'une matrice carrée N X N
	 * reçue, utile pour déterminer la matrice adjointe.
	 *
	 * @param mat la matrice d'origine
	 *
	 * @return la matrice carrée des cofacteurs de la matrice d'origine
	 */
	// TODO getMatCofacteurs - MANDAT 2 - Compléter le code de la méthode
	public static int[][] getMatCofacteurs(int[][] mat) {
		int[][] matC = getMatCopieProfonde(mat);

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				matC[i][j] = getDeterminant(getMatMineur(mat, i, j));
				matC[i][j] *= Math.pow(-1, i + j);
			}
		}

		return matC;
	}

	/**
	 * Retourne la matrice N X N adjointe à partir d'une matrice N X N reçue.
	 *
	 * Est la matrice transposée des cofacteurs de la matrice d'origine.
	 *
	 * @param mat la matrice d'origine
	 *
	 * @return la matrice carrée N X N adjointe
	 */
	// TODO getMatAdjointe - MANDAT 2 - Compléter le code de la méthode
	public static int[][] getMatAdjointe(int[][] mat) {
		return getMatTranspose(getMatCofacteurs(mat));
	}

	/**
	 * Calcule le déterminant inverse du chiffre de Hill selon la force brute.
	 *
	 * @param valDet déterminant de la matrice d'origine
	 * @param valMod la valeur du modulo pour le calcul
	 *
	 * @return la valeur du déterminant inverse ou 0
	 */
	public static int getDeterminantInverseHill(int valDet, int valMod) {
		int detInv = 0;
		SortedSet<Integer> premierEntreEux = MathUtilitaires.xPremierEntreEux(0, valMod);

		Iterator<Integer> it = premierEntreEux.iterator();
		Integer i = 0;

		while (it.hasNext() && (detInv == 0)) {
			i = it.next();
			if (MathUtilitaires.modulo((valDet * i), valMod) == 1) {
				detInv = i;
			}
		}

		return detInv;
	}

	/**
	 * Permet de faire une copie profonde de la matrice passée en paramètre, car
	 * mat.clone() ne fait qu'une copie de surface et les éléments de la matrice
	 * d'origine sont alors modifiés
	 * 
	 * @param mat la matrice d'origine
	 * 
	 * @return une copie de la matrice
	 */
	public static int[][] getMatCopieProfonde(int[][] mat) {
		int[][] copie = new int[mat.length][];

		for (int i = 0; i < mat.length; i++) {
			copie[i] = mat[i].clone();
		}

		return copie;
	}
}
