package commun.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * Méthode servant à hasher les mots de passe pour les stocker dans la base de
 * donnée
 *
 */
public class SHAUtil {
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
