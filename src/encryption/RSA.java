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
	 * Constante représentant l'unité dans les calculs
	 */
	private final static BigInteger un = new BigInteger("1");

	/**
	 * Nombre aléatoire généré avec un algorithme par défaut de Java
	 */
	private final static SecureRandom random = new SecureRandom();

	/**
	 * La clé privée
	 */
	private BigInteger clePrivee;
	/**
	 * La clé plublique
	 */
	private BigInteger clePublique;
	/**
	 * Le module
	 */
	private BigInteger module;

	/**
	 * Constructeur permettant de générer une clé publique et privée de longueur n
	 * 
	 * @param n - La longueur de la clé
	 */
	public RSA(int n) {
		// Génère deux nombres premiers
		BigInteger p = BigInteger.probablePrime(n / 2, random);
		BigInteger q = BigInteger.probablePrime(n / 2, random);
		BigInteger phi = (p.subtract(un)).multiply(q.subtract(un));

		module = p.multiply(q);
		clePublique = new BigInteger("65537"); // Valeur souvent utilisée en pratique
		clePrivee = clePublique.modInverse(phi);
	}

	/**
	 * Méthode permettant d'encrypter un message
	 * 
	 * @param message - Le message converti en BigInteger
	 * @return le message encrypté
	 */
	public BigInteger encrypter(BigInteger message) {
		return message.modPow(clePublique, module);
	}

	/**
	 * Méthode permettant de décrypter un message
	 * 
	 * @param code - Le message encrypté
	 * @return Le message décrypté
	 */
	public BigInteger decrypter(BigInteger code) {
		return code.modPow(clePrivee, module);
	}

	@Override
	public String toString() {
		return "RSA [clePrivee=" + clePrivee + ", clePublique=" + clePublique + ", module=" + module + "]";
	}

}
