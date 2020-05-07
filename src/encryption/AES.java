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
	private static final String[] SUBSTITUTION_EN = { "63", "7C", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67",
			"2b", "fe", "d7", "ab", "76", "ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c",
			"a4", "72", "c0", "b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31",
			"15", "04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75", "09",
			"83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84", "53", "d1", "00",
			"ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf", "d0", "ef", "aa", "fb", "43",
			"4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8", "51", "a3", "40", "8f", "92", "9d", "38",
			"f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2", "cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4",
			"a7", "7e", "3d", "64", "5d", "19", "73", "60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8",
			"14", "de", "5e", "0b", "db", "e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91",
			"95", "e4", "79", "e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae",
			"08", "ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a", "70",
			"3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e", "e1", "f8", "98",
			"11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df", "8c", "a1", "89", "0d", "bf",
			"e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16" };

	/**
	 * Tableau de substitution pour la décryption
	 */
	private static final String[] SUBSTITUTION_DE = { "52", "09", "6a", "d5", "30", "36", "a5", "38", "bf", "40", "a3",
			"9e", "81", "f3", "d7", "fb", "7c", "e3", "39", "82", "9b", "2f", "ff", "87", "34", "8e", "43", "44", "c4",
			"de", "e9", "cb", "54", "7b", "94", "32", "a6", "c2", "23", "3d", "ee", "4c", "95", "0b", "42", "fa", "c3",
			"4e", "08", "2e", "a1", "66", "28", "d9", "24", "b2", "76", "5b", "a2", "49", "6d", "8b", "d1", "25", "72",
			"f8", "f6", "64", "86", "68", "98", "16", "d4", "a4", "5c", "cc", "5d", "65", "b6", "92", "6c", "70", "48",
			"50", "fd", "ed", "b9", "da", "5e", "15", "46", "57", "a7", "8d", "9d", "84", "90", "d8", "ab", "00", "8c",
			"bc", "d3", "0a", "f7", "e4", "58", "05", "b8", "b3", "45", "06", "d0", "2c", "1e", "8f", "ca", "3f", "0f",
			"02", "c1", "af", "bd", "03", "01", "13", "8a", "6b", "3a", "91", "11", "41", "4f", "67", "dc", "ea", "97",
			"f2", "cf", "ce", "f0", "b4", "e6", "73", "96", "ac", "74", "22", "e7", "ad", "35", "85", "e2", "f9", "37",
			"e8", "1c", "75", "df", "6e", "47", "f1", "1a", "71", "1d", "29", "c5", "89", "6f", "b7", "62", "0e", "aa",
			"18", "be", "1b", "fc", "56", "3e", "4b", "c6", "d2", "79", "20", "9a", "db", "c0", "fe", "78", "cd", "5a",
			"f4", "1f", "dd", "a8", "33", "88", "07", "c7", "31", "b1", "12", "10", "59", "27", "80", "ec", "5f", "60",
			"51", "7f", "a9", "19", "b5", "4a", "0d", "2d", "e5", "7a", "9f", "93", "c9", "9c", "ef", "a0", "e0", "3b",
			"4d", "ae", "2a", "f5", "b0", "c8", "eb", "bb", "3c", "83", "53", "99", "61", "17", "2b", "04", "7e", "ba",
			"77", "d6", "26", "e1", "69", "14", "63", "55", "21", "0c", "7d" };

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
	public static List<String[][]> remplirTexteEncryption(String texte) {
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
	public static List<String[][]> shiftRows(List<String[][]> texte) {
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
	public static List<String[][]> shiftRowsInverse(List<String[][]> texte) {
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
	public static List<String[][]> subBytes(List<String[][]> texte) {
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
	public static List<String[][]> subBytesInverse(List<String[][]> texte) {
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

				
				for(int k = 0; k < TAILLE; k ++)
				{
					String temporaire =  Integer.toHexString(temp[k]);
					
					if(temporaire.length() == 1)
						temporaire = "0" + Integer.toHexString(temp[k]);
					
					matrice[k][i] = temporaire;
				}
				
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

				for(int k = 0; k < TAILLE; k ++)
				{
					String temporaire =  Integer.toHexString(temp[k]);
					
					if(temporaire.length() == 1)
						temporaire = "0" + Integer.toHexString(temp[k]);
					
					matrice[k][i] = temporaire;
				}

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
