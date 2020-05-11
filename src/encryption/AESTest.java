package encryption;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Test les méthodes présentes de la classe Encryption
 * 
 * @author Bank-era Corp.
 *
 */
public class AESTest {

	@Test
	public void testRemplirTexteEncryption() {

		// Vérifie qu'il y aille le bon nombre de matrice et que celles-ci soient bien
		// remplies
		List<String[][]> mat1 = AES.remplirTexteEncryption("Ceci est un test");

		assertTrue(mat1.size() == 1);

		assertTrue(mat1.get(0)[0][0].equals("43"));
		assertTrue(mat1.get(0)[0][1].equals("20"));
		assertTrue(mat1.get(0)[0][2].equals("20"));
		assertTrue(mat1.get(0)[0][3].equals("74"));
		assertTrue(mat1.get(0)[1][0].equals("65"));
		assertTrue(mat1.get(0)[1][1].equals("65"));
		assertTrue(mat1.get(0)[1][2].equals("75"));
		assertTrue(mat1.get(0)[1][3].equals("65"));
		assertTrue(mat1.get(0)[2][0].equals("63"));
		assertTrue(mat1.get(0)[2][1].equals("73"));
		assertTrue(mat1.get(0)[2][2].equals("6e"));
		assertTrue(mat1.get(0)[2][3].equals("73"));
		assertTrue(mat1.get(0)[3][0].equals("69"));
		assertTrue(mat1.get(0)[3][1].equals("74"));
		assertTrue(mat1.get(0)[3][2].equals("20"));
		assertTrue(mat1.get(0)[3][3].equals("74"));

		List<String[][]> mat2 = AES.remplirTexteEncryption("Ceci est un test, que nous devons faire");

		assertTrue(mat2.size() == 3);

		assertTrue(mat2.get(0)[0][0].equals("43"));
		assertTrue(mat2.get(0)[0][1].equals("20"));
		assertTrue(mat2.get(0)[0][2].equals("20"));
		assertTrue(mat2.get(0)[0][3].equals("74"));
		assertTrue(mat2.get(0)[1][0].equals("65"));
		assertTrue(mat2.get(0)[1][1].equals("65"));
		assertTrue(mat2.get(0)[1][2].equals("75"));
		assertTrue(mat2.get(0)[1][3].equals("65"));
		assertTrue(mat2.get(0)[2][0].equals("63"));
		assertTrue(mat2.get(0)[2][1].equals("73"));
		assertTrue(mat2.get(0)[2][2].equals("6e"));
		assertTrue(mat2.get(0)[2][3].equals("73"));
		assertTrue(mat2.get(0)[3][0].equals("69"));
		assertTrue(mat2.get(0)[3][1].equals("74"));
		assertTrue(mat2.get(0)[3][2].equals("20"));
		assertTrue(mat2.get(0)[3][3].equals("74"));

		assertTrue(mat2.get(1)[0][0].equals("2c"));
		assertTrue(mat2.get(1)[0][1].equals("65"));
		assertTrue(mat2.get(1)[0][2].equals("75"));
		assertTrue(mat2.get(1)[0][3].equals("65"));
		assertTrue(mat2.get(1)[1][0].equals("20"));
		assertTrue(mat2.get(1)[1][1].equals("20"));
		assertTrue(mat2.get(1)[1][2].equals("73"));
		assertTrue(mat2.get(1)[1][3].equals("76"));
		assertTrue(mat2.get(1)[2][0].equals("71"));
		assertTrue(mat2.get(1)[2][1].equals("6e"));
		assertTrue(mat2.get(1)[2][2].equals("20"));
		assertTrue(mat2.get(1)[2][3].equals("6f"));
		assertTrue(mat2.get(1)[3][0].equals("75"));
		assertTrue(mat2.get(1)[3][1].equals("6f"));
		assertTrue(mat2.get(1)[3][2].equals("64"));
		assertTrue(mat2.get(1)[3][3].equals("6e"));

		assertTrue(mat2.get(2)[0][0].equals("73"));
		assertTrue(mat2.get(2)[0][1].equals("69"));
		assertTrue(mat2.get(2)[0][2].equals("20"));
		assertTrue(mat2.get(2)[0][3].equals("20"));
		assertTrue(mat2.get(2)[1][0].equals("20"));
		assertTrue(mat2.get(2)[1][1].equals("72"));
		assertTrue(mat2.get(2)[1][2].equals("20"));
		assertTrue(mat2.get(2)[1][3].equals("20"));
		assertTrue(mat2.get(2)[2][0].equals("66"));
		assertTrue(mat2.get(2)[2][1].equals("65"));
		assertTrue(mat2.get(2)[2][2].equals("20"));
		assertTrue(mat2.get(2)[2][3].equals("20"));
		assertTrue(mat2.get(2)[3][0].equals("61"));
		assertTrue(mat2.get(2)[3][1].equals("20"));
		assertTrue(mat2.get(2)[3][2].equals("20"));
		assertTrue(mat2.get(2)[3][3].equals("20"));
	}

	@Test
	public void testShiftRows() {

		// Vérifie que le shiftRow fonctionne adéquatement
		List<String[][]> mat1 = AES.shiftRows(AES.remplirTexteEncryption("Ceci est un test"));

		assertTrue(mat1.size() == 1);

		assertTrue(mat1.get(0)[0][0].equals("43"));
		assertTrue(mat1.get(0)[0][1].equals("20"));
		assertTrue(mat1.get(0)[0][2].equals("20"));
		assertTrue(mat1.get(0)[0][3].equals("74"));
		assertTrue(mat1.get(0)[1][3].equals("65"));
		assertTrue(mat1.get(0)[1][0].equals("65"));
		assertTrue(mat1.get(0)[1][1].equals("75"));
		assertTrue(mat1.get(0)[1][2].equals("65"));
		assertTrue(mat1.get(0)[2][2].equals("63"));
		assertTrue(mat1.get(0)[2][3].equals("73"));
		assertTrue(mat1.get(0)[2][0].equals("6e"));
		assertTrue(mat1.get(0)[2][1].equals("73"));
		assertTrue(mat1.get(0)[3][1].equals("69"));
		assertTrue(mat1.get(0)[3][2].equals("74"));
		assertTrue(mat1.get(0)[3][3].equals("20"));
		assertTrue(mat1.get(0)[3][0].equals("74"));

		List<String[][]> mat2 = AES.shiftRows(AES.remplirTexteEncryption("Ceci est un test, que nous devons faire"));

		assertTrue(mat2.size() == 3);

		assertTrue(mat2.get(0)[0][0].equals("43"));
		assertTrue(mat2.get(0)[0][1].equals("20"));
		assertTrue(mat2.get(0)[0][2].equals("20"));
		assertTrue(mat2.get(0)[0][3].equals("74"));
		assertTrue(mat2.get(0)[1][3].equals("65"));
		assertTrue(mat2.get(0)[1][0].equals("65"));
		assertTrue(mat2.get(0)[1][1].equals("75"));
		assertTrue(mat2.get(0)[1][2].equals("65"));
		assertTrue(mat2.get(0)[2][2].equals("63"));
		assertTrue(mat2.get(0)[2][3].equals("73"));
		assertTrue(mat2.get(0)[2][0].equals("6e"));
		assertTrue(mat2.get(0)[2][1].equals("73"));
		assertTrue(mat2.get(0)[3][1].equals("69"));
		assertTrue(mat2.get(0)[3][2].equals("74"));
		assertTrue(mat2.get(0)[3][3].equals("20"));
		assertTrue(mat2.get(0)[3][0].equals("74"));

		assertTrue(mat2.get(1)[0][0].equals("2c"));
		assertTrue(mat2.get(1)[0][1].equals("65"));
		assertTrue(mat2.get(1)[0][2].equals("75"));
		assertTrue(mat2.get(1)[0][3].equals("65"));
		assertTrue(mat2.get(1)[1][3].equals("20"));
		assertTrue(mat2.get(1)[1][0].equals("20"));
		assertTrue(mat2.get(1)[1][1].equals("73"));
		assertTrue(mat2.get(1)[1][2].equals("76"));
		assertTrue(mat2.get(1)[2][2].equals("71"));
		assertTrue(mat2.get(1)[2][3].equals("6e"));
		assertTrue(mat2.get(1)[2][0].equals("20"));
		assertTrue(mat2.get(1)[2][1].equals("6f"));
		assertTrue(mat2.get(1)[3][1].equals("75"));
		assertTrue(mat2.get(1)[3][2].equals("6f"));
		assertTrue(mat2.get(1)[3][3].equals("64"));
		assertTrue(mat2.get(1)[3][0].equals("6e"));

		assertTrue(mat2.get(2)[0][0].equals("73"));
		assertTrue(mat2.get(2)[0][1].equals("69"));
		assertTrue(mat2.get(2)[0][2].equals("20"));
		assertTrue(mat2.get(2)[0][3].equals("20"));
		assertTrue(mat2.get(2)[1][3].equals("20"));
		assertTrue(mat2.get(2)[1][0].equals("72"));
		assertTrue(mat2.get(2)[1][1].equals("20"));
		assertTrue(mat2.get(2)[1][2].equals("20"));
		assertTrue(mat2.get(2)[2][2].equals("66"));
		assertTrue(mat2.get(2)[2][3].equals("65"));
		assertTrue(mat2.get(2)[2][0].equals("20"));
		assertTrue(mat2.get(2)[2][1].equals("20"));
		assertTrue(mat2.get(2)[3][1].equals("61"));
		assertTrue(mat2.get(2)[3][2].equals("20"));
		assertTrue(mat2.get(2)[3][3].equals("20"));
		assertTrue(mat2.get(2)[3][0].equals("20"));

	}

	@Test
	public void testShiftRowsInverse() {
		// Vérifie que le shiftRow et le shiftRowInverse fonctionne adéquatement
		List<String[][]> mat1 = AES.shiftRows(AES.remplirTexteEncryption("Ceci est un test"));

		assertTrue(mat1.size() == 1);

		assertTrue(mat1.get(0)[0][0].equals("43"));
		assertTrue(mat1.get(0)[0][1].equals("20"));
		assertTrue(mat1.get(0)[0][2].equals("20"));
		assertTrue(mat1.get(0)[0][3].equals("74"));
		assertTrue(mat1.get(0)[1][3].equals("65"));
		assertTrue(mat1.get(0)[1][0].equals("65"));
		assertTrue(mat1.get(0)[1][1].equals("75"));
		assertTrue(mat1.get(0)[1][2].equals("65"));
		assertTrue(mat1.get(0)[2][2].equals("63"));
		assertTrue(mat1.get(0)[2][3].equals("73"));
		assertTrue(mat1.get(0)[2][0].equals("6e"));
		assertTrue(mat1.get(0)[2][1].equals("73"));
		assertTrue(mat1.get(0)[3][1].equals("69"));
		assertTrue(mat1.get(0)[3][2].equals("74"));
		assertTrue(mat1.get(0)[3][3].equals("20"));
		assertTrue(mat1.get(0)[3][0].equals("74"));

		// On devrait retourver la matrice originale
		List<String[][]> mat3 = AES.shiftRowsInverse(mat1);

		assertTrue(mat3.size() == 1);

		assertTrue(mat3.get(0)[0][0].equals("43"));
		assertTrue(mat3.get(0)[0][1].equals("20"));
		assertTrue(mat3.get(0)[0][2].equals("20"));
		assertTrue(mat3.get(0)[0][3].equals("74"));
		assertTrue(mat3.get(0)[1][0].equals("65"));
		assertTrue(mat3.get(0)[1][1].equals("65"));
		assertTrue(mat3.get(0)[1][2].equals("75"));
		assertTrue(mat3.get(0)[1][3].equals("65"));
		assertTrue(mat3.get(0)[2][0].equals("63"));
		assertTrue(mat3.get(0)[2][1].equals("73"));
		assertTrue(mat3.get(0)[2][2].equals("6e"));
		assertTrue(mat3.get(0)[2][3].equals("73"));
		assertTrue(mat3.get(0)[3][0].equals("69"));
		assertTrue(mat3.get(0)[3][1].equals("74"));
		assertTrue(mat3.get(0)[3][2].equals("20"));
		assertTrue(mat3.get(0)[3][3].equals("74"));

		// Shift les rangées de la seconde matrice
		List<String[][]> mat2 = AES.remplirTexteEncryption("Ceci est un test, que nous devons faire");

		mat2 = AES.shiftRows(mat2);

		assertTrue(mat2.size() == 3);

		assertTrue(mat2.get(0)[0][0].equals("43"));
		assertTrue(mat2.get(0)[0][1].equals("20"));
		assertTrue(mat2.get(0)[0][2].equals("20"));
		assertTrue(mat2.get(0)[0][3].equals("74"));
		assertTrue(mat2.get(0)[1][3].equals("65"));
		assertTrue(mat2.get(0)[1][0].equals("65"));
		assertTrue(mat2.get(0)[1][1].equals("75"));
		assertTrue(mat2.get(0)[1][2].equals("65"));
		assertTrue(mat2.get(0)[2][2].equals("63"));
		assertTrue(mat2.get(0)[2][3].equals("73"));
		assertTrue(mat2.get(0)[2][0].equals("6e"));
		assertTrue(mat2.get(0)[2][1].equals("73"));
		assertTrue(mat2.get(0)[3][1].equals("69"));
		assertTrue(mat2.get(0)[3][2].equals("74"));
		assertTrue(mat2.get(0)[3][3].equals("20"));
		assertTrue(mat2.get(0)[3][0].equals("74"));

		assertTrue(mat2.get(1)[0][0].equals("2c"));
		assertTrue(mat2.get(1)[0][1].equals("65"));
		assertTrue(mat2.get(1)[0][2].equals("75"));
		assertTrue(mat2.get(1)[0][3].equals("65"));
		assertTrue(mat2.get(1)[1][3].equals("20"));
		assertTrue(mat2.get(1)[1][0].equals("20"));
		assertTrue(mat2.get(1)[1][1].equals("73"));
		assertTrue(mat2.get(1)[1][2].equals("76"));
		assertTrue(mat2.get(1)[2][2].equals("71"));
		assertTrue(mat2.get(1)[2][3].equals("6e"));
		assertTrue(mat2.get(1)[2][0].equals("20"));
		assertTrue(mat2.get(1)[2][1].equals("6f"));
		assertTrue(mat2.get(1)[3][1].equals("75"));
		assertTrue(mat2.get(1)[3][2].equals("6f"));
		assertTrue(mat2.get(1)[3][3].equals("64"));
		assertTrue(mat2.get(1)[3][0].equals("6e"));

		assertTrue(mat2.get(2)[0][0].equals("73"));
		assertTrue(mat2.get(2)[0][1].equals("69"));
		assertTrue(mat2.get(2)[0][2].equals("20"));
		assertTrue(mat2.get(2)[0][3].equals("20"));
		assertTrue(mat2.get(2)[1][3].equals("20"));
		assertTrue(mat2.get(2)[1][0].equals("72"));
		assertTrue(mat2.get(2)[1][1].equals("20"));
		assertTrue(mat2.get(2)[1][2].equals("20"));
		assertTrue(mat2.get(2)[2][2].equals("66"));
		assertTrue(mat2.get(2)[2][3].equals("65"));
		assertTrue(mat2.get(2)[2][0].equals("20"));
		assertTrue(mat2.get(2)[2][1].equals("20"));
		assertTrue(mat2.get(2)[3][1].equals("61"));
		assertTrue(mat2.get(2)[3][2].equals("20"));
		assertTrue(mat2.get(2)[3][3].equals("20"));
		assertTrue(mat2.get(2)[3][0].equals("20"));

		// On devrait retrouver la matrice originale

		List<String[][]> mat4 = AES.shiftRowsInverse(mat2);

		assertTrue(mat4.size() == 3);

		assertTrue(mat4.get(0)[0][0].equals("43"));
		assertTrue(mat4.get(0)[0][1].equals("20"));
		assertTrue(mat4.get(0)[0][2].equals("20"));
		assertTrue(mat4.get(0)[0][3].equals("74"));
		assertTrue(mat4.get(0)[1][0].equals("65"));
		assertTrue(mat4.get(0)[1][1].equals("65"));
		assertTrue(mat4.get(0)[1][2].equals("75"));
		assertTrue(mat4.get(0)[1][3].equals("65"));
		assertTrue(mat4.get(0)[2][0].equals("63"));
		assertTrue(mat4.get(0)[2][1].equals("73"));
		assertTrue(mat4.get(0)[2][2].equals("6e"));
		assertTrue(mat4.get(0)[2][3].equals("73"));
		assertTrue(mat4.get(0)[3][0].equals("69"));
		assertTrue(mat4.get(0)[3][1].equals("74"));
		assertTrue(mat4.get(0)[3][2].equals("20"));
		assertTrue(mat4.get(0)[3][3].equals("74"));

		assertTrue(mat4.get(1)[0][0].equals("2c"));
		assertTrue(mat4.get(1)[0][1].equals("65"));
		assertTrue(mat4.get(1)[0][2].equals("75"));
		assertTrue(mat4.get(1)[0][3].equals("65"));
		assertTrue(mat4.get(1)[1][0].equals("20"));
		assertTrue(mat4.get(1)[1][1].equals("20"));
		assertTrue(mat4.get(1)[1][2].equals("73"));
		assertTrue(mat4.get(1)[1][3].equals("76"));
		assertTrue(mat4.get(1)[2][0].equals("71"));
		assertTrue(mat4.get(1)[2][1].equals("6e"));
		assertTrue(mat4.get(1)[2][2].equals("20"));
		assertTrue(mat4.get(1)[2][3].equals("6f"));
		assertTrue(mat4.get(1)[3][0].equals("75"));
		assertTrue(mat4.get(1)[3][1].equals("6f"));
		assertTrue(mat4.get(1)[3][2].equals("64"));
		assertTrue(mat4.get(1)[3][3].equals("6e"));

		assertTrue(mat4.get(2)[0][0].equals("73"));
		assertTrue(mat4.get(2)[0][1].equals("69"));
		assertTrue(mat4.get(2)[0][2].equals("20"));
		assertTrue(mat4.get(2)[0][3].equals("20"));
		assertTrue(mat4.get(2)[1][0].equals("20"));
		assertTrue(mat4.get(2)[1][1].equals("72"));
		assertTrue(mat4.get(2)[1][2].equals("20"));
		assertTrue(mat4.get(2)[1][3].equals("20"));
		assertTrue(mat4.get(2)[2][0].equals("66"));
		assertTrue(mat4.get(2)[2][1].equals("65"));
		assertTrue(mat4.get(2)[2][2].equals("20"));
		assertTrue(mat4.get(2)[2][3].equals("20"));
		assertTrue(mat4.get(2)[3][0].equals("61"));
		assertTrue(mat4.get(2)[3][1].equals("20"));
		assertTrue(mat4.get(2)[3][2].equals("20"));
		assertTrue(mat4.get(2)[3][3].equals("20"));

	}

	@Test
	public void testMixCoulmns() {
		List<String[][]> liste = new ArrayList<>();

		String[][] s = { { "63", "09", "cd", "ba" }, { "53", "60", "70", "ca" }, { "e0", "e1", "b7", "d0" },
				{ "8c", "04", "51", "e7" } };

		liste.add(s);

		liste = AES.mixColumns(liste);

		assertTrue(liste.size() == 1);

		assertTrue(liste.get(0)[0][0].equals("5f"));
		assertTrue(liste.get(0)[1][0].equals("72"));
		assertTrue(liste.get(0)[2][0].equals("64"));
		assertTrue(liste.get(0)[3][0].equals("15"));

		assertTrue(liste.get(0)[0][1].equals("57"));
		assertTrue(liste.get(0)[1][1].equals("f5"));
		assertTrue(liste.get(0)[2][1].equals("bc"));
		assertTrue(liste.get(0)[3][1].equals("92"));

		assertTrue(liste.get(0)[0][2].equals("f7"));
		assertTrue(liste.get(0)[1][2].equals("be"));
		assertTrue(liste.get(0)[2][2].equals("3b"));
		assertTrue(liste.get(0)[3][2].equals("29"));

		assertTrue(liste.get(0)[0][3].equals("1d"));
		assertTrue(liste.get(0)[1][3].equals("b9"));
		assertTrue(liste.get(0)[2][3].equals("f9"));
		assertTrue(liste.get(0)[3][3].equals("1a"));

		List<String[][]> liste1 = new ArrayList<>();

		String[][] s1 = { { "36", "f9", "9f", "c4" }, { "33", "b5", "2c", "40" }, { "9d", "39", "09", "6d" },
				{ "50", "26", "2d", "23" } };

		liste1.add(s1);

		liste1 = AES.mixColumns(liste1);

		assertTrue(liste1.size() == 1);

		assertTrue(liste1.get(0)[0][0].equals("f4"));
		assertTrue(liste1.get(0)[1][0].equals("bc"));
		assertTrue(liste1.get(0)[2][0].equals("d4"));
		assertTrue(liste1.get(0)[3][0].equals("54"));

		assertTrue(liste1.get(0)[0][1].equals("32"));
		assertTrue(liste1.get(0)[1][1].equals("e5"));
		assertTrue(liste1.get(0)[2][1].equals("54"));
		assertTrue(liste1.get(0)[3][1].equals("d0"));

		assertTrue(liste1.get(0)[0][2].equals("75"));
		assertTrue(liste1.get(0)[1][2].equals("f1"));
		assertTrue(liste1.get(0)[2][2].equals("d6"));
		assertTrue(liste1.get(0)[3][2].equals("c5"));

		assertTrue(liste1.get(0)[0][3].equals("1d"));
		assertTrue(liste1.get(0)[1][3].equals("d0"));
		assertTrue(liste1.get(0)[2][3].equals("3b"));
		assertTrue(liste1.get(0)[3][3].equals("3c"));
	}

	@Test
	public void testMixCoulmnsInverse() {
		List<String[][]> liste = new ArrayList<>();

		String[][] s = { { "5f", "57", "f7", "1d" }, { "72", "f5", "be", "b9" }, { "64", "bc", "3b", "f9" },
				{ "15", "92", "29", "1a" } };

		liste.add(s);

		liste = AES.mixColumnsInverse(liste);

		assertTrue(liste.size() == 1);

		assertTrue(liste.get(0)[0][0].equals("63"));
		assertTrue(liste.get(0)[1][0].equals("53"));
		assertTrue(liste.get(0)[2][0].equals("e0"));
		assertTrue(liste.get(0)[3][0].equals("8c"));

		assertTrue(liste.get(0)[0][1].equals("09"));
		assertTrue(liste.get(0)[1][1].equals("60"));
		assertTrue(liste.get(0)[2][1].equals("e1"));
		assertTrue(liste.get(0)[3][1].equals("04"));

		assertTrue(liste.get(0)[0][2].equals("cd"));
		assertTrue(liste.get(0)[1][2].equals("70"));
		assertTrue(liste.get(0)[2][2].equals("b7"));
		assertTrue(liste.get(0)[3][2].equals("51"));

		assertTrue(liste.get(0)[0][3].equals("ba"));
		assertTrue(liste.get(0)[1][3].equals("ca"));
		assertTrue(liste.get(0)[2][3].equals("d0"));
		assertTrue(liste.get(0)[3][3].equals("e7"));

		List<String[][]> liste1 = new ArrayList<>();

		String[][] s1 = { { "f4", "32", "75", "1d" }, { "bc", "e5", "f1", "d0" }, { "d4", "54", "d6", "3b" },
				{ "54", "d0", "c5", "3c" } };

		liste1.add(s1);

		liste1 = AES.mixColumnsInverse(liste1);

		assertTrue(liste1.size() == 1);

		assertTrue(liste1.get(0)[0][0].equals("36"));
		assertTrue(liste1.get(0)[1][0].equals("33"));
		assertTrue(liste1.get(0)[2][0].equals("9d"));
		assertTrue(liste1.get(0)[3][0].equals("50"));

		assertTrue(liste1.get(0)[0][1].equals("f9"));
		assertTrue(liste1.get(0)[1][1].equals("b5"));
		assertTrue(liste1.get(0)[2][1].equals("39"));
		assertTrue(liste1.get(0)[3][1].equals("26"));

		assertTrue(liste1.get(0)[0][2].equals("9f"));
		assertTrue(liste1.get(0)[1][2].equals("2c"));
		assertTrue(liste1.get(0)[2][2].equals("09"));
		assertTrue(liste1.get(0)[3][2].equals("2d"));

		assertTrue(liste1.get(0)[0][3].equals("c4"));
		assertTrue(liste1.get(0)[1][3].equals("40"));
		assertTrue(liste1.get(0)[2][3].equals("6d"));
		assertTrue(liste1.get(0)[3][3].equals("23"));
	}

	@Test
	public void testSBox() {

		List<String[][]> liste = new ArrayList<>();

		String[][] s = { { "5f", "57", "f7", "1d" }, { "72", "f5", "be", "b9" }, { "64", "bc", "3b", "f9" },
				{ "15", "92", "29", "1a" } };

		liste.add(s);
		liste = AES.subBytes(liste);

		assertTrue(liste.size() == 1);

		assertTrue(liste.get(0)[0][0].equals("cf"));
		assertTrue(liste.get(0)[1][0].equals("40"));
		assertTrue(liste.get(0)[2][0].equals("43"));
		assertTrue(liste.get(0)[3][0].equals("59"));

		assertTrue(liste.get(0)[0][1].equals("5b"));
		assertTrue(liste.get(0)[1][1].equals("e6"));
		assertTrue(liste.get(0)[2][1].equals("65"));
		assertTrue(liste.get(0)[3][1].equals("4f"));

		assertTrue(liste.get(0)[0][2].equals("68"));
		assertTrue(liste.get(0)[1][2].equals("ae"));
		assertTrue(liste.get(0)[2][2].equals("e2"));
		assertTrue(liste.get(0)[3][2].equals("a5"));

		assertTrue(liste.get(0)[0][3].equals("a4"));
		assertTrue(liste.get(0)[1][3].equals("56"));
		assertTrue(liste.get(0)[2][3].equals("99"));
		assertTrue(liste.get(0)[3][3].equals("a2"));

	}

	@Test
	public void testSBoxInverse() {

		List<String[][]> liste = new ArrayList<>();

		String[][] s = { { "5f", "57", "f7", "1d" }, { "72", "f5", "be", "b9" }, { "64", "bc", "3b", "f9" },
				{ "15", "92", "29", "1a" } };

		liste.add(s);
		liste = AES.subBytesInverse(liste);

		assertTrue(liste.size() == 1);

		assertTrue(liste.get(0)[0][0].equals("84"));
		assertTrue(liste.get(0)[1][0].equals("1e"));
		assertTrue(liste.get(0)[2][0].equals("8c"));
		assertTrue(liste.get(0)[3][0].equals("2f"));

		assertTrue(liste.get(0)[0][1].equals("da"));
		assertTrue(liste.get(0)[1][1].equals("77"));
		assertTrue(liste.get(0)[2][1].equals("78"));
		assertTrue(liste.get(0)[3][1].equals("74"));

		assertTrue(liste.get(0)[0][2].equals("26"));
		assertTrue(liste.get(0)[1][2].equals("5a"));
		assertTrue(liste.get(0)[2][2].equals("49"));
		assertTrue(liste.get(0)[3][2].equals("4c"));

		assertTrue(liste.get(0)[0][3].equals("de"));
		assertTrue(liste.get(0)[1][3].equals("db"));
		assertTrue(liste.get(0)[2][3].equals("69"));
		assertTrue(liste.get(0)[3][3].equals("43"));

	}

	@Test
	public void testaddRoundKey() {
		List<String[][]> liste = new ArrayList<>();

		String[][] s = { { "5f", "57", "f7", "1d" }, { "72", "f5", "be", "b9" }, { "64", "bc", "3b", "f9" },
				{ "15", "92", "29", "1a" } };

		liste.add(s);

		String[][] cle = { { "d6", "d2", "da", "d6" }, { "aa", "af", "a6", "ab" }, { "74", "72", "78", "76" },
				{ "fd", "fa", "f1", "fe" } };

		liste = AES.addRoundKey(liste, cle);

		assertTrue(liste.size() == 1);

		assertTrue(liste.get(0)[0][0].equals("89"));
		assertTrue(liste.get(0)[1][0].equals("d8"));
		assertTrue(liste.get(0)[2][0].equals("10"));
		assertTrue(liste.get(0)[3][0].equals("e8"));

		assertTrue(liste.get(0)[0][1].equals("85"));
		assertTrue(liste.get(0)[1][1].equals("5a"));
		assertTrue(liste.get(0)[2][1].equals("ce"));
		assertTrue(liste.get(0)[3][1].equals("68"));

		assertTrue(liste.get(0)[0][2].equals("2d"));
		assertTrue(liste.get(0)[1][2].equals("18"));
		assertTrue(liste.get(0)[2][2].equals("43"));
		assertTrue(liste.get(0)[3][2].equals("d8"));

		assertTrue(liste.get(0)[0][3].equals("cb"));
		assertTrue(liste.get(0)[1][3].equals("12"));
		assertTrue(liste.get(0)[2][3].equals("8f"));
		assertTrue(liste.get(0)[3][3].equals("e4"));
	}

	@Test
	public void testRemplirDecryption() {
		// Vérifie qu'il y aille le bon nombre de matrice et que celles-ci soient bien
		// remplies
		List<String[][]> mat1 = AES.remplirTexteDecryption(("436563692065737420756e2074657374"));

		assertTrue(mat1.size() == 1);

		assertTrue(mat1.get(0)[0][0].equals("43"));
		assertTrue(mat1.get(0)[0][1].equals("20"));
		assertTrue(mat1.get(0)[0][2].equals("20"));
		assertTrue(mat1.get(0)[0][3].equals("74"));
		assertTrue(mat1.get(0)[1][0].equals("65"));
		assertTrue(mat1.get(0)[1][1].equals("65"));
		assertTrue(mat1.get(0)[1][2].equals("75"));
		assertTrue(mat1.get(0)[1][3].equals("65"));
		assertTrue(mat1.get(0)[2][0].equals("63"));
		assertTrue(mat1.get(0)[2][1].equals("73"));
		assertTrue(mat1.get(0)[2][2].equals("6e"));
		assertTrue(mat1.get(0)[2][3].equals("73"));
		assertTrue(mat1.get(0)[3][0].equals("69"));
		assertTrue(mat1.get(0)[3][1].equals("74"));
		assertTrue(mat1.get(0)[3][2].equals("20"));
		assertTrue(mat1.get(0)[3][3].equals("74"));

		List<String[][]> mat2 = AES.remplirTexteDecryption(
				"436563692065737420756e20746573742c20717565206e6f7573206465766f6e73206661697265202020202020202020");

		assertTrue(mat2.size() == 3);

		assertTrue(mat2.get(0)[0][0].equals("43"));
		assertTrue(mat2.get(0)[0][1].equals("20"));
		assertTrue(mat2.get(0)[0][2].equals("20"));
		assertTrue(mat2.get(0)[0][3].equals("74"));
		assertTrue(mat2.get(0)[1][0].equals("65"));
		assertTrue(mat2.get(0)[1][1].equals("65"));
		assertTrue(mat2.get(0)[1][2].equals("75"));
		assertTrue(mat2.get(0)[1][3].equals("65"));
		assertTrue(mat2.get(0)[2][0].equals("63"));
		assertTrue(mat2.get(0)[2][1].equals("73"));
		assertTrue(mat2.get(0)[2][2].equals("6e"));
		assertTrue(mat2.get(0)[2][3].equals("73"));
		assertTrue(mat2.get(0)[3][0].equals("69"));
		assertTrue(mat2.get(0)[3][1].equals("74"));
		assertTrue(mat2.get(0)[3][2].equals("20"));
		assertTrue(mat2.get(0)[3][3].equals("74"));

		assertTrue(mat2.get(1)[0][0].equals("2c"));
		assertTrue(mat2.get(1)[0][1].equals("65"));
		assertTrue(mat2.get(1)[0][2].equals("75"));
		assertTrue(mat2.get(1)[0][3].equals("65"));
		assertTrue(mat2.get(1)[1][0].equals("20"));
		assertTrue(mat2.get(1)[1][1].equals("20"));
		assertTrue(mat2.get(1)[1][2].equals("73"));
		assertTrue(mat2.get(1)[1][3].equals("76"));
		assertTrue(mat2.get(1)[2][0].equals("71"));
		assertTrue(mat2.get(1)[2][1].equals("6e"));
		assertTrue(mat2.get(1)[2][2].equals("20"));
		assertTrue(mat2.get(1)[2][3].equals("6f"));
		assertTrue(mat2.get(1)[3][0].equals("75"));
		assertTrue(mat2.get(1)[3][1].equals("6f"));
		assertTrue(mat2.get(1)[3][2].equals("64"));
		assertTrue(mat2.get(1)[3][3].equals("6e"));

		assertTrue(mat2.get(2)[0][0].equals("73"));
		assertTrue(mat2.get(2)[0][1].equals("69"));
		assertTrue(mat2.get(2)[0][2].equals("20"));
		assertTrue(mat2.get(2)[0][3].equals("20"));
		assertTrue(mat2.get(2)[1][0].equals("20"));
		assertTrue(mat2.get(2)[1][1].equals("72"));
		assertTrue(mat2.get(2)[1][2].equals("20"));
		assertTrue(mat2.get(2)[1][3].equals("20"));
		assertTrue(mat2.get(2)[2][0].equals("66"));
		assertTrue(mat2.get(2)[2][1].equals("65"));
		assertTrue(mat2.get(2)[2][2].equals("20"));
		assertTrue(mat2.get(2)[2][3].equals("20"));
		assertTrue(mat2.get(2)[3][0].equals("61"));
		assertTrue(mat2.get(2)[3][1].equals("20"));
		assertTrue(mat2.get(2)[3][2].equals("20"));
		assertTrue(mat2.get(2)[3][3].equals("20"));
	}

	@Test
	public void testViderEncryption() {
		List<String[][]> liste = new ArrayList<>();

		String[][] s = { { "5f", "57", "f7", "1d" }, { "72", "f5", "be", "b9" }, { "64", "bc", "3b", "f9" },
				{ "15", "92", "29", "1a" } };

		liste.add(s);

		assertTrue(AES.viderTexteEncryption(liste).equals("5f72641557f5bc92f7be3b291db9f91a"));

		String[][] s1 = { { "2c", "65", "75", "65" }, { "20", "20", "73", "76" }, { "71", "6e", "20", "6f" },
				{ "75", "6f", "64", "6e" } };
		String[][] s2 = { { "73", "69", "20", "20" }, { "20", "72", "20", "20" }, { "66", "65", "20", "20" },
				{ "61", "20", "20", "20" } };

		liste.add(s1);
		liste.add(s2);

		assertTrue(AES.viderTexteEncryption(liste).equals(
				"5f72641557f5bc92f7be3b291db9f91a2c20717565206e6f7573206465766f6e73206661697265202020202020202020"));
	}

	@Test
	public void testViderDecryption() {
		List<String[][]> liste = new ArrayList<>();

		String[][] s = { { "43", "20", "20", "74" }, { "65", "65", "75", "65" }, { "63", "73", "6e", "73" },
				{ "69", "74", "20", "74" } };

		liste.add(s);

		List<String[][]> liste1 = new ArrayList<>();

		String[][] s1 = { { "43", "20", "20", "74" }, { "65", "65", "75", "65" }, { "63", "73", "6e", "73" },
				{ "69", "74", "20", "74" } };
		String[][] s2 = { { "2c", "65", "75", "65" }, { "20", "20", "73", "76" }, { "71", "6e", "20", "6f" },
				{ "75", "6f", "64", "6e" } };
		String[][] s3 = { { "73", "69", "20", "20" }, { "20", "72", "20", "20" }, { "66", "65", "20", "20" },
				{ "61", "20", "20", "20" } };

		liste1.add(s1);
		liste1.add(s2);
		liste1.add(s3);

		assertTrue(AES.viderTexteDecryption(liste1).equals("Ceci est un test, que nous devons faire"));

	}

	@Test
	public void testEncrypterDecrypter() {
		// Test 1 -- Normal
		System.out.println("-------------------TEST 1-------------------");
		String message1 = "Ceci est un test";
		String test1 = AES.encrypter("test", message1);
		System.out.println(test1);
		String test1D = AES.decrypter("test", test1);
		System.out.println(test1D);
		assertTrue(message1.equals(test1D));

		// Test 2 -- Long message
		System.out.println("-------------------TEST 2-------------------");
		String message2 = "Voici le plus long message du monde!";
		String test2 = AES.encrypter("Ceci est un cle12", message2);
		System.out.println(test2);
		String test2D = AES.decrypter("Ceci est un cle12", test2);
		System.out.println(test2D);
		assertTrue(message2.equals(test2D));

		// Test 3 -- Longue clé
		System.out.println("-------------------TEST 3-------------------");
		String message3 = "Message top";
		String test3 = AES.encrypter("Voici la pire cle du monde entier", message3);
		System.out.println(test3);
		String test3D = AES.decrypter("Voici la pire cle du monde entier", test3);
		System.out.println(test3D);
		assertTrue(message3.equals(test3D));

		// Test 4 -- Accent et ponctuation spécial
		System.out.println("-------------------TEST 4-------------------");
		String message4 = "Étienne Être Râteau Leçon; Lagüe!#$%?&*()";
		String test4 = AES.encrypter("Mauvaise clé", message4);
		System.out.println(test4);
		String test4D = AES.decrypter("Mauvaise clé", test4);
		System.out.println(test4D);
		assertTrue(message4.equals(test4D));

		// Test 5 -- Chiffre
		System.out.println("-------------------TEST 5-------------------");
		String message5 = "Message top secret 01234";
		String test5 = AES.encrypter("01234", message5);
		System.out.println(test5);
		String test5D = AES.decrypter("01234", test5);
		System.out.println(test5D);
		assertTrue(message5.equals(test5D));

	}

	@Test
	public void testGenererCles() {
		// Clé bonne grosseur 16 caractères
		List<String[][]> liste = AES.genererCles("Voici une clé123");

		// S'assure que le trousseau a bien généré 11 clés
		assertTrue(liste.size() == 11);

		// S'assure qu'un clé a bien une longueur de 32 caractère en HexaString
		List<String[][]> listeTemp = new ArrayList<>();
		listeTemp.add(liste.get(0));
		assertTrue(AES.viderTexteEncryption(listeTemp).length() == 32);
		listeTemp.clear();
		listeTemp.add(liste.get(10));
		assertTrue(AES.viderTexteEncryption(listeTemp).length() == 32);

		// Clé trop longue - Devrait couper le bout de clé inutilisé
		List<String[][]> liste1 = AES.genererCles("Voici une clé12356789098765456");

		// S'assure que le trousseau a bien généré 11 clés
		assertTrue(liste1.size() == 11);

		// S'assure qu'un clé a bien une longueur de 32 caractère en HexaString
		List<String[][]> listeTemp1 = new ArrayList<>();
		listeTemp1.add(liste1.get(0));
		assertTrue(AES.viderTexteEncryption(listeTemp1).length() == 32);
		listeTemp1.clear();
		listeTemp1.add(liste1.get(10));
		assertTrue(AES.viderTexteEncryption(listeTemp1).length() == 32);

		// Clé trop courte - Devrait padder la clé
		List<String[][]> liste2 = AES.genererCles("Clé12");

		// S'assure que le trousseau a bien généré 11 clés
		assertTrue(liste2.size() == 11);

		// S'assure qu'un clé a bien une longueur de 32 caractère en HexaString
		List<String[][]> listeTemp2 = new ArrayList<>();
		listeTemp2.add(liste2.get(0));
		assertTrue(AES.viderTexteEncryption(listeTemp2).length() == 32);
		listeTemp2.clear();
		listeTemp2.add(liste2.get(10));
		assertTrue(AES.viderTexteEncryption(listeTemp2).length() == 32);
	}

}
