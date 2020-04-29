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
	 * Tableau temporaire de substitution pour l'encrytion
	 */
	private static final String[] SUBSTITUTION_EN = { "63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67", "2B", "FE", "D7", "AB", "76",
			"CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF", "9C", "A4", "72", "C0", "B7",
			"FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1", "71", "D8", "31", "15", "04", "C7",
			"23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2", "EB", "27", "B2", "75", "09", "83", "2C",
			"1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3", "29", "E3", "2F", "84", "53", "D1", "00", "ED",
			"20", "FC", "B1", "5B", "6A", "CB", "BE", "39", "4A", "4C", "58", "CF", "D0", "EF", "AA", "FB", "43",
			"4D", "33", "85", "45", "F9", "02", "7F", "50", "3C", "9F", "A8", "51", "A3", "40", "8F", "92", "9D",
			"38", "F5", "BC", "B6", "DA", "21", "10", "FF", "F3", "D2", "CD", "0C", "13", "EC", "5F", "97", "44",
			"17", "C4", "A7", "7E", "3D", "64", "5D", "19", "73", "60", "81", "4F", "DC", "22", "2A", "90", "88",
			"46", "EE", "B8", "14", "DE", "5E", "0B", "DB", "E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2",
			"D3", "AC", "62", "91", "95", "E4", "79", "E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56",
			"F4", "EA", "65", "7A", "AE", "08", "BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74",
			"1F", "4B", "BD", "8B", "8A", "70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9",
			"86", "C1", "1D", "9E", "E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9", "CE",
			"55", "28", "DF", "8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F", "B0", "54",
			"BB", "16" };
	
	/**
	 * Tableau temporaire de substitution pour la décryption
	 */
	private static final String[] SUBSTITUTION_DE = { "52", "09", "6A", "D5", "30", "36", "A5", "38", "BF", "40", "A3", "9E",
			"81", "F3", "D7", "FB", "7C", "E3", "39", "82", "9B", "2F", "FF", "87", "34", "8E", "43", "44", "C4", "DE",
			"E9", "CB", "54", "7B", "94", "32", "A6", "C2", "23", "3D", "EE", "4C", "95", "0B", "42", "FA", "C3", "4E",
			"08", "2E", "A1", "66", "28", "D9", "24", "B2", "76", "5B", "A2", "49", "6D", "8B", "D1", "25", "72", "F8",
			"F6", "64", "86", "68", "98", "16", "D4", "A4", "5C", "CC", "5D", "65", "B6", "92", "6C", "70", "48", "50",
			"FD", "ED", "B9", "DA", "5E", "15", "46", "57", "A7", "8D", "9D", "84", "90", "D8", "AB", "00", "8C", "BC",
			"D3", "0A", "F7", "E4", "58", "05", "B8", "B3", "45", "06", "D0", "2C", "1E", "8F", "CA", "3F", "0F", "02",
			"C1", "AF", "BD", "03", "01", "13", "8A", "6B", "3A", "91", "11", "41", "4F", "67", "DC", "EA", "97", "F2",
			"CF", "CE", "F0", "B4", "E6", "73", "96", "AC", "74", "22", "E7", "AD", "35", "85", "E2", "F9", "37", "E8",
			"1C", "75", "DF", "6E", "47", "F1", "1A", "71", "1D", "29", "C5", "89", "6F", "B7", "62", "0E", "AA", "18",
			"BE", "1B", "FC", "56", "3E", "4B", "C6", "D2", "79", "20", "9A", "DB", "C0", "FE", "78", "CD", "5A", "F4",
			"1F", "DD", "A8", "33", "88", "07", "C7", "31", "B1", "12", "10", "59", "27", "80", "EC", "5F", "60", "51",
			"7F", "A9", "19", "B5", "4A", "0D", "2D", "E5", "7A", "9F", "93", "C9", "9C", "EF", "A0", "E0", "3B", "4D",
			"AE", "2A", "F5", "B0", "C8", "EB", "BB", "3C", "83", "53", "99", "61", "17", "2B", "04", "7E", "BA", "77",
			"D6", "26", "E1", "69", "14", "63", "55", "21", "0C", "7D" };
	
	/**
	 * Taille des matrices texte et des clés
	 */
	public static final int TAILLE = 4;

	/**
	 * Le trousseau de Cle
	 */
	private TrousseauClef cles;

	/**
	 * Liste contenant les matrices de textes à encoder
	 */
	private List<String[][]> texte;

	/**
	 * Le texte encrypté
	 */
	private String texteEncrypte = null;

	/**
	 * Remplis la liste de matrice de textes à encoder et crée le trousseau de clé
	 * 
	 * @param cle   - Le mot de passe servant à créer le trousseau de clé
	 * @param texte - Le texte à encoder
	 */
	public AES(String cle, String texte) {
		remplirTexte(texte);
		remplirBoitesSubstitution();
		creerTrousseauCle(cle);
	}

	/**
	 * Retourne le texte encrypté
	 * 
	 * @return Le texte encrypté
	 */
	public String getTextEncrypte() {
		return texteEncrypte;
	}

	/**
	 * Retourne la liste de matrice d'encryption
	 * 
	 * @return La liste de matrice d'encryption
	 */
	protected List<String[][]> getMatrice() {
		return texte;
	}

	/**
	 * Retourne le trousseau de clé
	 * 
	 * @return Le trousseau de clé
	 */
	protected TrousseauClef getTrousseau() {
		return cles;
	}

	/**
	 * Remplis la liste de matrice de textes
	 * 
	 * @param texte - Le texte servant à remplir les matrices
	 */
	private void remplirTexte(String texte) {
		this.texte = new ArrayList<String[][]>();
		int cpt = 0;

		for (int i = 0; i < texte.length(); i += TAILLE * TAILLE) {
			this.texte.add(new String[TAILLE][TAILLE]);

			int temp = 0;
			for (int j = 0; j < TAILLE; j++) {
				for (int k = 0; k < TAILLE; k++) {
					if (i + temp < texte.length())
						this.texte.get(cpt)[k][j] = Integer.toHexString((int) texte.charAt(i + temp));
					else
						this.texte.get(cpt)[k][j] = Integer.toHexString((int) ' ');

					temp++;
				}
			}

			cpt++;
		}
	}
	
	private void remplirBoitesSubstitution()
	{
		//TODO À implémenter avec l'inverse multiplicatif
	}

	/**
	 * Crée le trousseau de clé
	 * 
	 * @param cle - Le mot de passe pour créer le trousseau de clé
	 */
	private void creerTrousseauCle(String cle) {
		cles = new TrousseauClef(cle);
	}

	/**
	 * Décale les rangées selon l'algorithme de RINJDAEL pour l'encryption
	 */
	protected void shiftRows() {
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
	}

	/**
	 * Décale les rangées selon l'algorithme de RINJDAEL pour la décryption
	 */
	protected void shiftRowsInverse() {
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
	}
	
	/**
	 * Substitue chaque caractère de la liste de matrice de texte pour l'encryption
	 */
	protected void sBox()
	{
		for (String[][] matrice : texte) 
			for (int i = 0; i < TAILLE; i++) 
				for (int k = 0; k < TAILLE; k++) 
					matrice[i][k] = SUBSTITUTION_EN[Integer.parseInt(matrice[i][k], 16)];
	}
	
	/**
	 * Substitue chaque caractère de la liste de matrice de texte pour la décryption
	 */
	protected void sBoxInverse()
	{
		for (String[][] matrice : texte) 
			for (int i = 0; i < TAILLE; i++) 
				for (int k = 0; k < TAILLE; k++) 
					matrice[i][k] = SUBSTITUTION_DE[Integer.parseInt(matrice[i][k], 16)];
	}
	
	/**
	 * XOR la clé selon la round à chaque matrice de texte - Utilisez pour l'encryption et la décryption
	 * @param round - La round de l'AES
	 */
	protected void addRoundKey(int round)
	{
		String[][] cle = cles.getCle(round);
		
		for (String[][] matrice : texte) 
			for (int i = 0; i < TAILLE; i++) 
				for (int k = 0; k < TAILLE; k++) 
					matrice[i][k] = Integer.toHexString(Integer.parseInt(matrice[i][k], 16) ^ 
							Integer.parseInt(cle[i][k], 16));
		
	}
	
	protected void mixColumn()
	{
		//TODO à implémenter
	}
	
	
	protected void mixColumnInverse()
	{
		//TODO à implémenter
	}

	/**
	 * Méthode permettant d'encrypter un message
	 * 
	 * @param cle     - La clé d'encryption
	 * @param message - Le message
	 * @return le message codé
	 */
	public static String encrypter(String cle, String message) {
		// TODO À implémenter
		return null;
	}

	/**
	 * Méthode permettant de décrypter un message
	 * 
	 * @param cle     - La clé d'encryption
	 * @param message - Le message codé
	 * @return Le message brut
	 */
	public static String decrypter(String cle, String message) {
		// TODO À implémenter
		return null;
	}
	
	/**
	 * Cette classe s'occupe de garder et générer les clés utilisé par l'algorithme.
	 * 
	 * @author Bank-era Corp.
	 */
	private class TrousseauClef {
		
		private List<String[][]> cles;
		
		private String clef;
		private int round;

		private String mot0;
		private String mot1;
		private String mot2;
		private String mot3;

		private String nMot0;
		private String nMot1;
		private String nMot2;
		private String nMot3;

		public TrousseauClef(String pClef) {

			if (pClef.length() > 32)
				this.clef = pClef.substring(0, 32);
			else
				this.clef = "00000000000000000000000000000000".substring(0, 32 - pClef.length()) + pClef;
			
			cles = new ArrayList<>();
			round = 0;
			
			genererCles();
		}
		
		protected String[][] getCle(int index)
		{
			return cles.get(index);
		}
		
		/**
		 * Génére toute les matrices de clés nécessaires pour l'encryption
		 */
		private void genererCles()
		{
			remplirMatrice();
			while (round < 10)
			{
				genererProchaine();
			}
		}
		
		/**
		 * Remplis la matrice pour la clé et l'ajoute à la liste de clé
		 */
		private void remplirMatrice()
		{
			String[][] cle = new String[TAILLE][TAILLE];
			
			int cpt = 0;
			
			for (int j = 0; j < TAILLE; j++) 
				for (int k = 0; k < TAILLE; k++) {
					cle[k][j] = clef.substring(cpt, cpt+2);
					cpt += 2;
				}	
			
			cles.add(cle);
		}

		/**
		 * Génère la prochaine clée sous forme de String
		 */
		private void genererProchaine() {
			mot0 = clef.substring(0, 8);
			mot1 = clef.substring(8, 16);
			mot2 = clef.substring(16, 24);
			mot3 = clef.substring(24, 32);

			nMot0 = "";
			nMot1 = "";
			nMot2 = "";
			nMot3 = "";

			for (int i = 0; i < mot0.length(); i++) {
				nMot0 += Integer.toHexString(Integer.parseInt(mot0.charAt(i) + "", 16)
						^ Integer.parseInt(fonctionGAvant(mot3).charAt(i) + "", 16));
				nMot1 += Integer.toHexString(
						Integer.parseInt(nMot0.charAt(i) + "", 16) ^ Integer.parseInt(mot1.charAt(i) + "", 16));
				nMot2 += Integer.toHexString(
						Integer.parseInt(nMot1.charAt(i) + "", 16) ^ Integer.parseInt(mot2.charAt(i) + "", 16));
				nMot3 += Integer.toHexString(
						Integer.parseInt(nMot2.charAt(i) + "", 16) ^ Integer.parseInt(mot3.charAt(i) + "", 16));
			}

			round++;
			clef = nMot0 + nMot1 + nMot2 + nMot3;
			remplirMatrice();
		}

		/**
		 * Accomplit la fonction g sur un mot
		 * 
		 * @param mot - Mot qui subira la fonction g
		 * @return le mot modifié
		 */
		private String fonctionGAvant(String mot) {
			String temp = mot.substring(2, 8) + mot.substring(0, 2);
			temp = substitutionAvant(temp.substring(0, 2)) + substitutionAvant(temp.substring(2, 4))
					+ substitutionAvant(temp.substring(4, 6))
					+ substitutionAvant(temp.substring(6, 8));

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
		protected String substitutionAvant(String a) {

			return SUBSTITUTION_EN[Integer.parseInt(a.substring(0, 2), 16)];

		}

		public String toString() {
			String roundStyle;
			
			if(round < 10)
				roundStyle = " "+ round;
			else
				roundStyle = round + "";
			
			return "[" + roundStyle+ "] k = " + clef;
		}

	}
	
	/**
	 * Méthode permettant de padder une String selon un bloc de 128-bit (16 bytes)
	 * 
	 * @param s la String à padder
	 * @return la String paddé
	 */
	private static String padString(String s) {
		byte count = 0;
		while(s.getBytes().length % 15 != 0) {
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
		byte paddingLength = (byte) s.charAt(s.length()-1);
		return s.substring(0, s.length()-paddingLength-1);
	}
}
