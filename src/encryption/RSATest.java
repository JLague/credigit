package encryption;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe testant les méthodes pour la clé RSA
 * 
 * @author Bank-era Corp.
 *
 */
public class RSATest {

	CleRSA c1, c2, c3;

	@Before
	public void testGenererCle() {
		c1 = RSA.genererCle(128);
		c2 = RSA.genererCle(192);
		c3 = RSA.genererCle(256);

		// La vérification de la longueur est approximative car la génération est
		// approximative
		assertTrue(c1.getClePrivee().bitLength() >= 122 && c1.getClePrivee().bitLength() <= 130);
		assertTrue(c2.getClePrivee().bitLength() >= 186 && c2.getClePrivee().bitLength() <= 194);
		assertTrue(c3.getClePrivee().bitLength() >= 240 && c3.getClePrivee().bitLength() <= 260);

		// Valide que la clé publique est bel et bien à la valeur définie
		assertTrue(c1.getClePublique().intValueExact() == c2.getClePublique().intValueExact());
		assertTrue(c2.getClePublique().intValueExact() == c3.getClePublique().intValueExact());
		assertTrue(c3.getClePublique().intValueExact() == 65537);

		// Vérifie que les clés privées sont différentes
		assertFalse((c1.getClePrivee()).equals(c2.getClePrivee()));
		assertFalse((c1.getClePrivee()).equals(c3.getClePrivee()));
		assertFalse((c2.getClePrivee()).equals(c3.getClePrivee()));

		// Vérifie que les modules sont différents
		assertFalse((c1.getModule()).equals(c2.getModule()));
		assertFalse((c1.getModule()).equals(c3.getModule()));
		assertFalse((c2.getModule()).equals(c3.getModule()));
	}

	@Test
	public void testEncrypter() {
		String message1 = "Hello World!";
		String message2 = "Aloha";
		String message3 = "5555555555";

		String[] test1 = new String[3];
		test1[0] = RSA
				.integerToString(RSA.encrypter(RSA.stringToInteger(message1), c1.getClePublique(), c1.getModule()));
		test1[1] = RSA
				.integerToString(RSA.encrypter(RSA.stringToInteger(message2), c1.getClePublique(), c1.getModule()));
		test1[2] = RSA
				.integerToString(RSA.encrypter(RSA.stringToInteger(message3), c1.getClePublique(), c1.getModule()));

		String[] test2 = new String[3];
		test2[0] = RSA
				.integerToString(RSA.encrypter(RSA.stringToInteger(message1), c2.getClePublique(), c2.getModule()));
		test2[1] = RSA
				.integerToString(RSA.encrypter(RSA.stringToInteger(message2), c2.getClePublique(), c2.getModule()));
		test2[2] = RSA
				.integerToString(RSA.encrypter(RSA.stringToInteger(message3), c2.getClePublique(), c2.getModule()));

		String[] test3 = new String[3];
		test3[0] = RSA
				.integerToString(RSA.encrypter(RSA.stringToInteger(message1), c3.getClePublique(), c3.getModule()));
		test3[1] = RSA
				.integerToString(RSA.encrypter(RSA.stringToInteger(message2), c3.getClePublique(), c3.getModule()));
		test3[2] = RSA
				.integerToString(RSA.encrypter(RSA.stringToInteger(message3), c3.getClePublique(), c3.getModule()));

		// Tests visuels
		System.out
				.println("------------------------------------- Encryption -----------------------------------------");
		System.out.println();
		System.out.println("----------- Test1 en cours ----------------");
		System.out.println();
		afficherTableau(test1);
		System.out.println();

		System.out.println("----------- Test2 en cours ----------------");
		System.out.println();
		afficherTableau(test2);
		System.out.println();

		System.out.println("----------- Test3 en cours ----------------");
		System.out.println();
		afficherTableau(test2);
		System.out.println();

		// Valide que les Strings sont différentes de message original
		assertFalse(test1[0].equals(message1));
		assertFalse(test1[1].equals(message2));
		assertFalse(test1[2].equals(message3));

		assertFalse(test2[0].equals(message1));
		assertFalse(test2[1].equals(message2));
		assertFalse(test2[2].equals(message3));

		assertFalse(test3[0].equals(message1));
		assertFalse(test3[1].equals(message2));
		assertFalse(test3[2].equals(message3));

		// Valide que les messages cryptés sont tous différents

		// Entre les String du test1
		assertFalse(test1[0].equals(test1[1]));
		assertFalse(test1[0].equals(test1[2]));
		assertFalse(test1[1].equals(test1[0]));
		assertFalse(test1[1].equals(test1[2]));
		assertFalse(test1[2].equals(test1[0]));
		assertFalse(test1[2].equals(test1[1]));

		// Entre les String du test2
		assertFalse(test2[0].equals(test2[1]));
		assertFalse(test2[0].equals(test2[2]));
		assertFalse(test2[1].equals(test2[0]));
		assertFalse(test2[1].equals(test2[2]));
		assertFalse(test2[2].equals(test2[0]));
		assertFalse(test2[2].equals(test2[1]));

		// Entre les String du test3
		assertFalse(test3[0].equals(test3[1]));
		assertFalse(test3[0].equals(test3[2]));
		assertFalse(test3[1].equals(test3[0]));
		assertFalse(test3[1].equals(test3[2]));
		assertFalse(test3[2].equals(test3[0]));
		assertFalse(test3[2].equals(test3[1]));

		// Les Strings du test1 vs les autres tests
		assertFalse(test1[0].equals(test2[0]));
		assertFalse(test1[0].equals(test2[1]));
		assertFalse(test1[0].equals(test2[2]));
		assertFalse(test1[1].equals(test2[0]));
		assertFalse(test1[1].equals(test2[1]));
		assertFalse(test1[1].equals(test2[2]));
		assertFalse(test1[2].equals(test2[0]));
		assertFalse(test1[2].equals(test2[1]));
		assertFalse(test1[2].equals(test2[2]));

		assertFalse(test1[0].equals(test3[0]));
		assertFalse(test1[0].equals(test3[1]));
		assertFalse(test1[0].equals(test3[2]));
		assertFalse(test1[1].equals(test3[0]));
		assertFalse(test1[1].equals(test3[1]));
		assertFalse(test1[1].equals(test3[2]));
		assertFalse(test1[2].equals(test3[0]));
		assertFalse(test1[2].equals(test3[1]));
		assertFalse(test1[2].equals(test3[2]));

		// Les Strings du test2 vs ceux du test3
		assertFalse(test2[0].equals(test3[0]));
		assertFalse(test2[0].equals(test3[1]));
		assertFalse(test2[0].equals(test3[2]));
		assertFalse(test2[1].equals(test3[0]));
		assertFalse(test2[1].equals(test3[1]));
		assertFalse(test2[1].equals(test3[2]));
		assertFalse(test2[2].equals(test3[0]));
		assertFalse(test2[2].equals(test3[1]));
		assertFalse(test2[2].equals(test3[2]));
	}

	@Test
	public void testDecrypter() {
		// Messages de base
		String message1 = "Hello World!";
		String message2 = "Aloha";
		String message3 = "5555555555";

		// Encryption
		BigInteger message1Encrypte1 = RSA.encrypter(RSA.stringToInteger(message1), c1.getClePublique(),
				c1.getModule());
		BigInteger message1Encrypte2 = RSA.encrypter(RSA.stringToInteger(message1), c2.getClePublique(),
				c2.getModule());
		BigInteger message1Encrypte3 = RSA.encrypter(RSA.stringToInteger(message1), c3.getClePublique(),
				c3.getModule());

		BigInteger message2Encrypte1 = RSA.encrypter(RSA.stringToInteger(message2), c1.getClePublique(),
				c1.getModule());
		BigInteger message2Encrypte2 = RSA.encrypter(RSA.stringToInteger(message2), c2.getClePublique(),
				c2.getModule());
		BigInteger message2Encrypte3 = RSA.encrypter(RSA.stringToInteger(message2), c3.getClePublique(),
				c3.getModule());

		BigInteger message3Encrypte1 = RSA.encrypter(RSA.stringToInteger(message3), c1.getClePublique(),
				c1.getModule());
		BigInteger message3Encrypte2 = RSA.encrypter(RSA.stringToInteger(message3), c2.getClePublique(),
				c2.getModule());
		BigInteger message3Encrypte3 = RSA.encrypter(RSA.stringToInteger(message3), c3.getClePublique(),
				c3.getModule());

		// Decryption
		String message1Decrypte1 = RSA
				.integerToString(RSA.decrypter(message1Encrypte1, c1.getClePrivee(), c1.getModule()));
		String message1Decrypte2 = RSA
				.integerToString(RSA.decrypter(message1Encrypte2, c2.getClePrivee(), c2.getModule()));
		String message1Decrypte3 = RSA
				.integerToString(RSA.decrypter(message1Encrypte3, c3.getClePrivee(), c3.getModule()));

		String message2Decrypte1 = RSA
				.integerToString(RSA.decrypter(message2Encrypte1, c1.getClePrivee(), c1.getModule()));
		String message2Decrypte2 = RSA
				.integerToString(RSA.decrypter(message2Encrypte2, c2.getClePrivee(), c2.getModule()));
		String message2Decrypte3 = RSA
				.integerToString(RSA.decrypter(message2Encrypte3, c3.getClePrivee(), c3.getModule()));

		String message3Decrypte1 = RSA
				.integerToString(RSA.decrypter(message3Encrypte1, c1.getClePrivee(), c1.getModule()));
		String message3Decrypte2 = RSA
				.integerToString(RSA.decrypter(message3Encrypte2, c2.getClePrivee(), c2.getModule()));
		String message3Decrypte3 = RSA
				.integerToString(RSA.decrypter(message3Encrypte3, c3.getClePrivee(), c3.getModule()));

		// Tests visuels
		System.out
				.println("------------------------------------- Decryption -----------------------------------------");
		System.out.println();
		System.out.println("----------- Test1 en cours ----------------");
		System.out.println();
		System.out.println(message1Decrypte1);
		System.out.println(message1Decrypte2);
		System.out.println(message1Decrypte3);
		System.out.println();

		System.out.println("----------- Test2 en cours ----------------");
		System.out.println();
		System.out.println(message2Decrypte1);
		System.out.println(message2Decrypte2);
		System.out.println(message2Decrypte3);
		System.out.println();

		System.out.println("----------- Test3 en cours ----------------");
		System.out.println();
		System.out.println(message3Decrypte1);
		System.out.println(message3Decrypte2);
		System.out.println(message3Decrypte3);
		System.out.println();

		// Validation des résultats

		// 1er message
		assertTrue(message1Decrypte1.equals(message1));
		assertTrue(message1Decrypte2.equals(message1));
		assertTrue(message1Decrypte3.equals(message1));

		// 2e message
		assertTrue(message2Decrypte1.equals(message2));
		assertTrue(message2Decrypte2.equals(message2));
		assertTrue(message2Decrypte3.equals(message2));

		// 3e message
		assertTrue(message3Decrypte1.equals(message3));
		assertTrue(message3Decrypte2.equals(message3));
		assertTrue(message3Decrypte3.equals(message3));

	}

	private void afficherTableau(String[] tab) {
		String affichage = "";
		for (int i = 0; i < tab.length; i++) {
			affichage += tab[i] + "\n";
		}

		System.out.println(affichage);
	}

}
