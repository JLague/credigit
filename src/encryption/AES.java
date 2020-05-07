package encryption;

import java.util.*;

/**
 * Classe permettant d'encrypter un message
 * 
 * @author Bank-era Corp.
 *
 */
public class AES {

	/**
	 * Tableau de substitution pour l'encrytion
	 */
	private static final String[] SUBSTITUTION_EN = { "63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67",
			"2B", "FE", "D7", "AB", "76", "CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF", "9C",
			"A4", "72", "C0", "B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1", "71", "D8", "31",
			"15", "04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2", "EB", "27", "B2", "75", "09",
			"83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3", "29", "E3", "2F", "84", "53", "D1", "00",
			"ED", "20", "FC", "B1", "5B", "6A", "CB", "BE", "39", "4A", "4C", "58", "CF", "D0", "EF", "AA", "FB", "43",
			"4D", "33", "85", "45", "F9", "02", "7F", "50", "3C", "9F", "A8", "51", "A3", "40", "8F", "92", "9D", "38",
			"F5", "BC", "B6", "DA", "21", "10", "FF", "F3", "D2", "CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4",
			"A7", "7E", "3D", "64", "5D", "19", "73", "60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8",
			"14", "DE", "5E", "0B", "DB", "E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC", "62", "91",
			"95", "E4", "79", "E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4", "EA", "65", "7A", "AE",
			"08", "BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74", "1F", "4B", "BD", "8B", "8A", "70",
			"3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9", "86", "C1", "1D", "9E", "E1", "F8", "98",
			"11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9", "CE", "55", "28", "DF", "8C", "A1", "89", "0D", "BF",
			"E6", "42", "68", "41", "99", "2D", "0F", "B0", "54", "BB", "16" };

	/**
	 * Tableau de substitution pour la décryption
	 */
	private static final String[] SUBSTITUTION_DE = { "52", "09", "6A", "D5", "30", "36", "A5", "38", "BF", "40", "A3",
			"9E", "81", "F3", "D7", "FB", "7C", "E3", "39", "82", "9B", "2F", "FF", "87", "34", "8E", "43", "44", "C4",
			"DE", "E9", "CB", "54", "7B", "94", "32", "A6", "C2", "23", "3D", "EE", "4C", "95", "0B", "42", "FA", "C3",
			"4E", "08", "2E", "A1", "66", "28", "D9", "24", "B2", "76", "5B", "A2", "49", "6D", "8B", "D1", "25", "72",
			"F8", "F6", "64", "86", "68", "98", "16", "D4", "A4", "5C", "CC", "5D", "65", "B6", "92", "6C", "70", "48",
			"50", "FD", "ED", "B9", "DA", "5E", "15", "46", "57", "A7", "8D", "9D", "84", "90", "D8", "AB", "00", "8C",
			"BC", "D3", "0A", "F7", "E4", "58", "05", "B8", "B3", "45", "06", "D0", "2C", "1E", "8F", "CA", "3F", "0F",
			"02", "C1", "AF", "BD", "03", "01", "13", "8A", "6B", "3A", "91", "11", "41", "4F", "67", "DC", "EA", "97",
			"F2", "CF", "CE", "F0", "B4", "E6", "73", "96", "AC", "74", "22", "E7", "AD", "35", "85", "E2", "F9", "37",
			"E8", "1C", "75", "DF", "6E", "47", "F1", "1A", "71", "1D", "29", "C5", "89", "6F", "B7", "62", "0E", "AA",
			"18", "BE", "1B", "FC", "56", "3E", "4B", "C6", "D2", "79", "20", "9A", "DB", "C0", "FE", "78", "CD", "5A",
			"F4", "1F", "DD", "A8", "33", "88", "07", "C7", "31", "B1", "12", "10", "59", "27", "80", "EC", "5F", "60",
			"51", "7F", "A9", "19", "B5", "4A", "0D", "2D", "E5", "7A", "9F", "93", "C9", "9C", "EF", "A0", "E0", "3B",
			"4D", "AE", "2A", "F5", "B0", "C8", "EB", "BB", "3C", "83", "53", "99", "61", "17", "2B", "04", "7E", "BA",
			"77", "D6", "26", "E1", "69", "14", "63", "55", "21", "0C", "7D" };

	/**
	 * Taille des matrices texte et des clés
	 */
	public static final int TAILLE = 4;

	/**
	 * Méthode permettant d'encrypter un message
	 * 
	 * @param cle     - La clé d'encryption
	 * @param message - Le message
	 * @return Le message codé
	 */
	public static String encrypter(String cle, String message) {
		List<String[][]> cles = genererCles(cle);
		List<String[][]> texteAEncoder = remplirTexteEncryption(message);

		texteAEncoder = addRoundKey(texteAEncoder, cles.get(0));

		for (int i = 1; i < 10; i++) {
			texteAEncoder = subBytes(texteAEncoder);
			texteAEncoder = shiftRows(texteAEncoder);
			texteAEncoder = mixColumns(texteAEncoder);
			texteAEncoder = addRoundKey(texteAEncoder, cles.get(i));
		}

		texteAEncoder = subBytes(texteAEncoder);
		texteAEncoder = shiftRows(texteAEncoder);
		texteAEncoder = addRoundKey(texteAEncoder, cles.get(10));

		return viderTexteEncryption(texteAEncoder);
	}

	/**
	 * Méthode permettant de décrypter un message
	 * 
	 * @param cle     - La clé d'encryption
	 * @param message - Le message codé
	 * @return Le message brut
	 */
	public static String decrypter(String cle, String message) {
		List<String[][]> cles = genererCles(cle);
		List<String[][]> texteAEncoder = remplirTexteDecryption(message);

		// MixColumnsInverse sur clé 1 à 9

		// Est-ce que on commence avec la dernière ou première clé????
		texteAEncoder = addRoundKey(texteAEncoder, cles.get(10));

		for (int i = 9; i > 0; i--) {
			texteAEncoder = subBytesInverse(texteAEncoder);
			texteAEncoder = shiftRowsInverse(texteAEncoder);
			texteAEncoder = mixColumnsInverse(texteAEncoder);
			texteAEncoder = addRoundKey(texteAEncoder, cles.get(i));
		}

		texteAEncoder = subBytesInverse(texteAEncoder);
		texteAEncoder = shiftRowsInverse(texteAEncoder);
		texteAEncoder = addRoundKey(texteAEncoder, cles.get(0));

		return viderTexteDecryption(texteAEncoder);
	}

	/**
	 * Remplis la liste de matrices de textes lors de l'encryption
	 * 
	 * @param texte - Le texte servant à remplir les matrices
	 */
	private static List<String[][]> remplirTexteEncryption(String texte) {
		List<String[][]> liste = new ArrayList<String[][]>();
		int cpt = 0;

		for (int i = 0; i < texte.length(); i += TAILLE * TAILLE) {
			liste.add(new String[TAILLE][TAILLE]);

			int temp = 0;
			for (int j = 0; j < TAILLE; j++) {
				for (int k = 0; k < TAILLE; k++) {
					if (i + temp < texte.length())
						liste.get(cpt)[k][j] = Integer.toHexString((int) texte.charAt(i + temp));
					else
						liste.get(cpt)[k][j] = Integer.toHexString((int) ' ');

					temp++;
				}
			}

			cpt++;
		}

		return liste;
	}

	/**
	 * Remplis la liste de matrices de textes lors de la décryption
	 * 
	 * @param texte - Le texte servant à remplir les matrices
	 */
	private static List<String[][]> remplirTexteDecryption(String texte) {
		List<String[][]> liste = new ArrayList<String[][]>();
		int cpt = 0;

		for (int i = 0; i < texte.length(); i += TAILLE * TAILLE) {
			liste.add(new String[TAILLE][TAILLE]);

			int temp = 0;
			for (int j = 0; j < TAILLE; j++) {
				for (int k = 0; k < TAILLE; k++) {

					if (i + temp < texte.length())
						liste.get(cpt)[k][j] = texte.substring(temp, temp + 2);
					else
						liste.get(cpt)[k][j] = Integer.toHexString((int) ' ');

					temp += 2;
				}
			}

			cpt++;
		}

		return liste;
	}

	/**
	 * Vide la liste de matrices de textes pour mettre les caractère en String lors
	 * de l'encryption
	 * 
	 * @param liste - La liste de matrices à vider
	 * @return Le message contenu dans les matrices
	 */
	private static String viderTexteEncryption(List<String[][]> liste) {
		String texte = "";

		for (int i = 0; i < liste.size(); i++) {
			for (int j = 0; j < TAILLE; j++) {
				for (int k = 0; k < TAILLE; k++) {

					if (liste.get(i)[k][j].length() == 1) {
						liste.get(i)[k][j] = "0" + liste.get(i)[k][j];
					}

					texte += liste.get(i)[k][j];
				}
			}
		}

		return texte;
	}

	/**
	 * Vide la liste de matrices de textes pour mettre les caractère en String lors
	 * de la décryption
	 * 
	 * @param liste - La liste de matrices à vider
	 * @return Le message contenu dans les matrices
	 */
	private static String viderTexteDecryption(List<String[][]> liste) {
		String texte = "";

		for (int i = 0; i < liste.size(); i++) {
			for (int j = 0; j < TAILLE; j++) {
				for (int k = 0; k < TAILLE; k++) {

					texte += String.valueOf((char) ((int) Integer.parseInt(liste.get(i)[k][j], 16)));
				}
			}
		}

		return texte;
	}

	/**
	 * Décale les rangées selon l'algorithme de RINJDAEL pour l'encryption
	 * 
	 * @param texte - La liste de matrices
	 * @return La liste de matrices modifiée
	 */
	private static List<String[][]> shiftRows(List<String[][]> texte) {
		for (String[][] matrice : texte) {
			int cpt = 1;

			for (int i = 1; i < TAILLE; i++) {
				String[] temp = new String[TAILLE];

				for (int j = 0; j < TAILLE; j++) {
					temp[j] = matrice[i][j];
				}

				for (int k = 0; k < TAILLE; k++) {
					matrice[i][k] = temp[(k + cpt) % 4];
				}

				cpt++;
			}
		}

		return texte;
	}

	/**
	 * Décale les rangées selon l'algorithme de RINJDAEL pour la décryption
	 * 
	 * @param texte - La liste de matrices
	 * @return La liste de matrices modifiée
	 */
	private static List<String[][]> shiftRowsInverse(List<String[][]> texte) {
		for (String[][] matrice : texte) {
			int cpt = 1;

			for (int i = 1; i < TAILLE; i++) {
				String[] temp = new String[TAILLE];

				for (int j = 0; j < TAILLE; j++) {
					temp[j] = matrice[i][j];
				}

				for (int k = 0; k < TAILLE; k++) {
					int a = k - cpt;

					if (a < 0) {
						a += 4;
					}

					matrice[i][k] = temp[(a) % 4];
				}

				cpt++;
			}
		}

		return texte;
	}

	/**
	 * Substitue chaque caractère de la liste de matrice de texte pour l'encryption
	 * 
	 * @param texte - La liste de matrices
	 * @return La liste de matrices modifiée
	 */
	private static List<String[][]> subBytes(List<String[][]> texte) {
		for (String[][] matrice : texte)
			for (int i = 0; i < TAILLE; i++)
				for (int k = 0; k < TAILLE; k++)
					matrice[i][k] = SUBSTITUTION_EN[Integer.parseInt(matrice[i][k], 16)];

		return texte;
	}

	/**
	 * Substitue chaque caractère de la liste de matrice de texte pour l'encryption
	 * 
	 * @param texte - La liste de matrices
	 * @return La liste de matrices modifiée
	 */
	private static List<String[][]> subBytesInverse(List<String[][]> texte) {
		for (String[][] matrice : texte)
			for (int i = 0; i < TAILLE; i++)
				for (int k = 0; k < TAILLE; k++)
					matrice[i][k] = SUBSTITUTION_DE[Integer.parseInt(matrice[i][k], 16)];

		return texte;
	}

	/**
	 * XOR la clé selon la round à chaque matrice de texte - Utilisez pour
	 * l'encryption et la décryption
	 * 
	 * @param round - La round de l'AES
	 */
	private static List<String[][]> addRoundKey(List<String[][]> texte, String[][] cle) {

		for (String[][] matrice : texte)
			for (int i = 0; i < TAILLE; i++)
				for (int k = 0; k < TAILLE; k++)
					matrice[i][k] = Integer
							.toHexString(Integer.parseInt(matrice[i][k], 16) ^ Integer.parseInt(cle[i][k], 16));

		return texte;

	}

	public static List<String[][]> mixColumns(List<String[][]> texte) {

		for (String[][] matrice : texte) {

			for (int i = 0; i < TAILLE; i++) {

				Integer[] colonne = new Integer[4];
				Integer[] temp = new Integer[4];

				colonne[0] = Integer.valueOf(matrice[0][i], 16);
				colonne[1] = Integer.valueOf(matrice[1][i], 16);
				colonne[2] = Integer.valueOf(matrice[2][i], 16);
				colonne[3] = Integer.valueOf(matrice[3][i], 16);

				temp[0] = (bitwiseMultiply(colonne[0], 2) ^ bitwiseMultiply(colonne[1], 3) ^ colonne[2] ^ colonne[3]);
				temp[1] = (colonne[0] ^ bitwiseMultiply(colonne[1], 2) ^ bitwiseMultiply(colonne[2], 3) ^ colonne[3]);
				temp[2] = (colonne[0] ^ colonne[1] ^ bitwiseMultiply(colonne[2], 2) ^ bitwiseMultiply(colonne[3], 3));
				temp[3] = (bitwiseMultiply(colonne[0], 3) ^ colonne[1] ^ colonne[2] ^ bitwiseMultiply(colonne[3], 2));

				for (int j = 0; j < TAILLE; j++) {
					if (temp[j] > 255) {
						temp[j] ^= 0x1B;
						temp[j] ^= 0b100000000;
					}

				}

				matrice[0][i] = Integer.toHexString(temp[0]);
				matrice[1][i] = Integer.toHexString(temp[1]);
				matrice[2][i] = Integer.toHexString(temp[2]);
				matrice[3][i] = Integer.toHexString(temp[3]);

			}
		}

		return texte;
	}

	public static List<String[][]> mixColumnsInverse(List<String[][]> texte) {
		for (String[][] matrice : texte) {

			for (int i = 0; i < TAILLE; i++) {

				Integer[] colonne = new Integer[4];
				Integer[] temp = new Integer[4];

				colonne[0] = Integer.valueOf(matrice[0][i], 16);
				colonne[1] = Integer.valueOf(matrice[1][i], 16);
				colonne[2] = Integer.valueOf(matrice[2][i], 16);
				colonne[3] = Integer.valueOf(matrice[3][i], 16);

				temp[0] = (bitwiseMultiply(colonne[0], 14) ^ bitwiseMultiply(colonne[1], 11)
						^ bitwiseMultiply(colonne[2], 13) ^ bitwiseMultiply(colonne[3], 9));
				temp[1] = (bitwiseMultiply(colonne[0], 9) ^ bitwiseMultiply(colonne[1], 14)
						^ bitwiseMultiply(colonne[2], 11) ^ bitwiseMultiply(colonne[3], 13));
				temp[2] = (bitwiseMultiply(colonne[0], 13) ^ bitwiseMultiply(colonne[1], 9)
						^ bitwiseMultiply(colonne[2], 14) ^ bitwiseMultiply(colonne[3], 11));
				temp[3] = (bitwiseMultiply(colonne[0], 11) ^ bitwiseMultiply(colonne[1], 13)
						^ bitwiseMultiply(colonne[2], 9) ^ bitwiseMultiply(colonne[3], 14));

				for (int j = 0; j < TAILLE; j++) {
//						if(temp[j] > 255)
//						{
//							if(temp[j] > 511)
//							//À vérifier!!!! car erreur
//							temp[j] ^= 0x1B;
//							temp[j] ^= 0b100000000;
//						}	

					if (temp[j] < 0) {
						temp[j] += 256;
					}
				}

				matrice[0][i] = Integer.toHexString(temp[0]);
				matrice[1][i] = Integer.toHexString(temp[1]);
				matrice[2][i] = Integer.toHexString(temp[2]);
				matrice[3][i] = Integer.toHexString(temp[3]);

			}
		}

		return texte;
	}

	/**
	 * Génére toute les matrices de clés nécessaires pour l'encryption
	 */
	private static List<String[][]> genererCles(String pClef) {
		String clef;
		List<String[][]> cles = new ArrayList<>();

		pClef = convertirHexaString(pClef);

		if (pClef.length() > 32)
			clef = pClef.substring(0, 32);
		else
			clef = "00000000000000000000000000000000".substring(0, 32 - pClef.length()) + pClef;

		int round = 0;

		cles.add(remplirMatrice(clef));
		while (round < 10) {
			cles.add(genererProchaine(clef, round));
			round++;
		}

		return cles;
	}

	private static String convertirHexaString(String cle) {
		String temp = "";

		for (int i = 0; i < cle.length(); i++) {
			temp += Integer.toHexString((int) cle.charAt(i));
		}

		return temp;
	}

	/**
	 * Remplis la matrice pour la clé et l'ajoute à la liste de clé
	 */
	private static String[][] remplirMatrice(String clef) {
		String[][] cle = new String[TAILLE][TAILLE];

		int cpt = 0;

		for (int j = 0; j < TAILLE; j++)
			for (int k = 0; k < TAILLE; k++) {
				cle[k][j] = clef.substring(cpt, cpt + 2);
				cpt += 2;
			}

		return cle;
	}

	/**
	 * Génère la prochaine clée sous forme de String
	 */
	private static String[][] genererProchaine(String clef, int round) {
		String mot0 = clef.substring(0, 8);
		String mot1 = clef.substring(8, 16);
		String mot2 = clef.substring(16, 24);
		String mot3 = clef.substring(24, 32);

		String nMot0 = "";
		String nMot1 = "";
		String nMot2 = "";
		String nMot3 = "";

		for (int i = 0; i < mot0.length(); i++) {
			nMot0 += Integer.toHexString(Integer.parseInt(mot0.charAt(i) + "", 16)
					^ Integer.parseInt(fonctionGAvant(mot3, round).charAt(i) + "", 16));
			nMot1 += Integer.toHexString(
					Integer.parseInt(nMot0.charAt(i) + "", 16) ^ Integer.parseInt(mot1.charAt(i) + "", 16));
			nMot2 += Integer.toHexString(
					Integer.parseInt(nMot1.charAt(i) + "", 16) ^ Integer.parseInt(mot2.charAt(i) + "", 16));
			nMot3 += Integer.toHexString(
					Integer.parseInt(nMot2.charAt(i) + "", 16) ^ Integer.parseInt(mot3.charAt(i) + "", 16));
		}

		clef = nMot0 + nMot1 + nMot2 + nMot3;
		return remplirMatrice(clef);
	}

	/**
	 * Accomplit la fonction g sur un mot
	 * 
	 * @param mot - Mot qui subira la fonction g
	 * @return le mot modifié
	 */
	private static String fonctionGAvant(String mot, int round) {
		String temp = mot.substring(2, 8) + mot.substring(0, 2);
		temp = substitutionAvant(temp.substring(0, 2)) + substitutionAvant(temp.substring(2, 4))
				+ substitutionAvant(temp.substring(4, 6)) + substitutionAvant(temp.substring(6, 8));

		int rc = 1 << round;

		if (round == 8) {
			rc = 27;
		} else if (round == 9) {
			rc = 54;
		}

		temp = Integer.toHexString((Integer.parseInt(temp.substring(0, 2), 16) ^ rc)) + temp.substring(2, 8);
		if (temp.length() == 7)
			temp = "0" + temp;
		return temp;
	}

	/**
	 * Tableau de substitution de Rinjdael pour l'encryption
	 * 
	 * @param a - La String à substituer
	 * @return La string substituée
	 */
	private static String substitutionAvant(String a) {

		return SUBSTITUTION_EN[Integer.parseInt(a.substring(0, 2), 16)];

	}

	private static int bitwiseMultiply(int a, int b) {
//		int a = n1, b = n2, result = 0;
//		while (b != 0) {
//			if ((b & 01) != 0) {
//				result = result ^ a;
//			}
//			a <<= 1;
//			b >>= 1;
//		}
//		return result;

		byte aa = (byte) a, bb = (byte) b, r = 0, t;
		while (aa != 0) {
			if ((aa & 1) != 0)
				r = (byte) (r ^ bb);
			t = (byte) (bb & 0x80);
			bb = (byte) (bb << 1);
			if (t != 0)
				bb = (byte) (bb ^ 0x1b);
			aa = (byte) ((aa & 0xff) >>> 1);
		}

		/*
		 * ATTENTION ne pas remettre le résultat de 256 + r dans r, puisque r est un
		 * byte est va overflow. À la place, on doit return directement pour renvoyer un
		 * int
		 */
		if (r < 0) {
			return 256 + r;
		}

		return r;
	}

	/**
	 * Méthode permettant de padder une String selon un bloc de 128-bit (16 bytes)
	 * 
	 * @param s la String à padder
	 * @return la String paddé
	 */
	private static String padString(String s) {
		byte count = 0;
		while (s.getBytes().length % 15 != 0) {
			count++;
			s += " ";
		}

		s += (char) count;

		return s;
	}

	/**
	 * Méthode permettant de padder une String selon un bloc de 128-bit (16 bytes)
	 * 
	 * @param s la String à padder
	 * @return la String paddé
	 */
	private static String unpadString(String s) {
		byte paddingLength = (byte) s.charAt(s.length() - 1);
		return s.substring(0, s.length() - paddingLength - 1);
	}
}
