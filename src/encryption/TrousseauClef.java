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
	public String getProchaine() {
		mot0 = clef.substring(0,4);
		mot1 = clef.substring(4,8);
		mot2 = clef.substring(8,12);
		mot3 = clef.substring(12,16);
		
		nMot0 = "" + (Integer.parseInt("0x" + mot0)^Integer.parseInt("0x" + fonctionGAvant(mot3)));
		nMot1 = "" + (Integer.parseInt("0x" + mot0)^Integer.parseInt("0x" + mot1));
		nMot2 = "" + (Integer.parseInt("0x" + mot1)^Integer.parseInt("0x" + mot2));
		nMot3 = "" + (Integer.parseInt("0x" + mot2)^Integer.parseInt("0x" + mot3));
		
		return nMot0+nMot1+nMot2+nMot3;
	}

	/**
	 * Génère la clée précédente sous forme de string
	 */
	public String getPrecedente() {
		return null;
	}

	/**
	 * Accomplit la fonction g sur un mot
	 * @param mot - Mot qui subira la fonction g
	 * @return le mot modifié
	 */
	private String fonctionGAvant(String mot) {
		String temp = mot.substring(1, 4) + mot.charAt(0);
		temp = Encryption.substitutionAvant(temp.charAt(0)) + Encryption.substitutionAvant(temp.charAt(1))
				+ Encryption.substitutionAvant(temp.charAt(2)) + Encryption.substitutionAvant(temp.charAt(3));
		
		
		temp = (Integer.parseInt("0x" + temp.substring(0,1))^round) + temp.substring(1,4);
		
		return temp;
	}

	/**
	 * Accomplit l'inverse de ka fonction g sur un mot
	 * @param mot - Mot qui subira l'inverse de la fonction g
	 * @return le mot modifié
	 */
	private String fonctionGArriere(String mot) {
		return null;
	}
}
