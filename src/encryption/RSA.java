package encryption;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Classe permettant d'effectuer des opérations basées sur le système RSA
 * 
 * @author Bank-era Corp.
 *
 */
public class RSA {

	/**
	 * Méthode pour générer une Clé RSA
	 * 
	 * @param n - La longueur de la clé (Approximativement)
	 * @return la clé
	 */
	public static CleRSA genererCle(int n) {
		BigInteger un = new BigInteger("1");
		SecureRandom random = new SecureRandom();

		BigInteger p = BigInteger.probablePrime(n / 2, random);
		BigInteger q = BigInteger.probablePrime(n / 2, random);
		BigInteger phi = (p.subtract(un)).multiply(q.subtract(un));

		BigInteger module = p.multiply(q);
		BigInteger clePublique = new BigInteger("65537"); // Valeur souvent utilisée en pratique
		BigInteger clePrivee = clePublique.modInverse(phi);

		return new CleRSA(clePrivee, clePublique, module);
	}

	/**
	 * Méthode permettant d'encrypter un message
	 * 
	 * @param message - Le message converti en BigInteger
	 * @return le message encrypté
	 */
	public static BigInteger encrypter(BigInteger message, BigInteger clePublique, BigInteger module) {
		return message.modPow(clePublique, module);
	}

	/**
	 * Méthode permettant de décrypter un message
	 * 
	 * @param code - Le message encrypté
	 * @return Le message décrypté
	 */
	public static BigInteger decrypter(BigInteger code, BigInteger clePrivee, BigInteger module) {
		return code.modPow(clePrivee, module);
	}

	/**
	 * Méthode permettant de convertir une String en BigInteger
	 * 
	 * @param message - Le message à convertir
	 * @return le BigInteger représentant le message
	 */
	public static BigInteger stringToInteger(String message) {
		byte[] bytes = message.getBytes();
		return new BigInteger(bytes);
	}

	/**
	 * Méthode permettant de convertir un BigInteger en String
	 * 
	 * @param message - Le message sous forme de BigInteger
	 * @return Le message sous forme de String
	 */
	public static String integerToString(BigInteger message) {
		return new String(message.toByteArray());
	}

	public static void main(String[] args) {
		CleRSA cle = new CleRSA(new BigInteger("32244774284211042705171103939999050641"), new BigInteger("65537"),
				new BigInteger("166671328359045595559284971252973341809"));
		System.out.println("Clé privée: " + cle.getClePrivee());
		System.out.println("Clé publique: " + cle.getClePublique());
		System.out.println("Clé module: " + cle.getModule());
		String message = "Ceci est une cle";
		BigInteger encrypte = RSA.encrypter(RSA.stringToInteger(message), cle.getClePublique(), cle.getModule());
		System.out.println(encrypte);
		System.out.println(RSA.integerToString(RSA.decrypter(encrypte, cle.getClePrivee(), cle.getModule())));
	}

}
