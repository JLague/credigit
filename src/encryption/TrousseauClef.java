package encryption;

import java.math.BigInteger;

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
			nMot0 += Integer.toHexString(Math.floorMod((mot0.charAt(i) ^ fonctionGAvant(mot3).charAt(i)),16));
			//System.out.println("\t> Mot 0 : " + nMot0);
			nMot1 += Integer.toHexString(Math.floorMod((nMot0.charAt(i) ^ mot1.charAt(i)),16));
			//System.out.println("\t> Mot 1 : " + nMot1);
			nMot2 += Integer.toHexString(Math.floorMod((nMot1.charAt(i) ^ mot2.charAt(i)),16));
			//System.out.println("\t> Mot 2 : " + nMot2);
			nMot3 += Integer.toHexString(Math.floorMod((nMot2.charAt(i) ^ mot3.charAt(i)),16));
			//System.out.println("\t> Mot 3 : " + nMot3);
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

		temp = (temp.charAt(0) ^ round) + temp.substring(1, 8);
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

	public static void main(String[] args) {
		TrousseauClef t1 = new TrousseauClef(0, "000102030405060708090a0b0c0d0e0f");

		for (int i = 0; i < 10; i++) {
			System.out.println("[" + t1.round + "] k = " + t1.clef);
			t1.genererProchaine();
		}

		System.out.println("[" + t1.round + "] k = " + t1.clef);
	}
}
