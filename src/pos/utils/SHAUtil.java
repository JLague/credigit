package pos.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * Méthode servant à hasher les mots de passe pour les stocker dans la base de
 * donnée
 * 
 * @author Bank-era Corp.
 *
 */
public class SHAUtil {

	/**
	 * Méthode permettant de hacher le mot de passe
	 * 
	 * @param originalPassword - Le mot de passe original
	 * @return le mot de passe haché en hexadécimal
	 */
	public static String hashPassword(String originalPassword) {
		MessageDigest digest = null;

		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		byte[] encodedHash = digest.digest(originalPassword.getBytes(StandardCharsets.UTF_8));

		return bytesToHex(encodedHash);
	}

	/**
	 * Méthode permettant de transformer des bytes en hexadécimal
	 * 
	 * @param hash - les bytes hachés
	 * @return les bytes en hexadécimal
	 */
	private static String bytesToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
