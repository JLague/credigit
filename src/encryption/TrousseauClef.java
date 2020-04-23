package encryption;


/**
 * Cette classe s'occupe de garder et générer les clés utilisé par l'algorithme.
 * 
 * @author Bank-era Corp.
 */
public class TrousseauClef {
	String clef;
	int round;

	String mot0;
	String mot1;
	String mot2;
	String mot3;

	String nMot0;
	String nMot1;
	String nMot2;
	String nMot3;

	public TrousseauClef(int pRound, String pClef) {
		this.round = pRound;
		this.clef = pClef;
	}

	/**
	 * Génère la prochaine clée sous forme de String
	 */
	public void genererProchaine() {
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
			// System.out.println("\t> Mot 0 : " + nMot0);
			nMot1 += Integer.toHexString(
					Integer.parseInt(nMot0.charAt(i) + "", 16) ^ Integer.parseInt(mot1.charAt(i) + "", 16));
			// System.out.println("\t> Mot 1 : " + nMot1);
			nMot2 += Integer.toHexString(
					Integer.parseInt(nMot1.charAt(i) + "", 16) ^ Integer.parseInt(mot2.charAt(i) + "", 16));
			// System.out.println("\t> Mot 2 : " + nMot2);
			nMot3 += Integer.toHexString(
					Integer.parseInt(nMot2.charAt(i) + "", 16) ^ Integer.parseInt(mot3.charAt(i) + "", 16));
			// System.out.println("\t> Mot 3 : " + nMot3);
		}

		round++;
		clef = nMot0 + nMot1 + nMot2 + nMot3;
	}

	/**
	 * Génère la clée précédente sous forme de string
	 */
	public void genererPrecedente() {
	}

	/**
	 * Accomplit la fonction g sur un mot
	 * 
	 * @param mot - Mot qui subira la fonction g
	 * @return le mot modifié
	 */
	private String fonctionGAvant(String mot) {
		String temp = mot.substring(2, 8) + mot.substring(0, 2);
		temp = Encryption.substitutionAvant(temp.substring(0, 2)) + Encryption.substitutionAvant(temp.substring(2, 4))
				+ Encryption.substitutionAvant(temp.substring(4, 6))
				+ Encryption.substitutionAvant(temp.substring(6, 8));

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
	 * Accomplit l'inverse de la fonction g sur un mot
	 * 
	 * @param mot - Mot qui subira l'inverse de la fonction g
	 * @return le mot modifié
	 */
	private String fonctionGArriere(String mot) {
		return null;
	}

}
