package encryption;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
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
	public void testMixCoulmns()
	{
		List<String[][]> liste = new ArrayList<>();
		
		String[][] s = {{"63", "09", "cd", "ba"}, {"53", "60", "70", "ca"}, {"e0", "e1", "b7", "d0"}, {"8c", "04", "51", "e7"}};
		
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
		
		String[][] s1 = {{"36", "f9", "9f", "c4"}, {"33", "b5", "2c", "40"}, {"9d", "39", "09", "6d"}, {"50", "26", "2d", "23"}};
		
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
	public void testMixCoulmnsInverse()
	{
		List<String[][]> liste = new ArrayList<>();
		
		String[][] s = {{"5f", "57", "f7", "1d"}, {"72", "f5", "be", "b9"}, {"64", "bc", "3b", "f9"}, {"15", "92", "29", "1a"}};
		
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
		
		String[][] s1 = {{"f4", "32", "75", "1d"}, {"bc", "e5", "f1", "d0"}, {"d4", "54", "d6", "3b"}, {"54", "d0", "c5", "3c"}};
		
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
		
		String[][] s = {{"5f", "57", "f7", "1d"}, {"72", "f5", "be", "b9"}, {"64", "bc", "3b", "f9"}, {"15", "92", "29", "1a"}};
		
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
		
		String[][] s = {{"5f", "57", "f7", "1d"}, {"72", "f5", "be", "b9"}, {"64", "bc", "3b", "f9"}, {"15", "92", "29", "1a"}};
		
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
	public void addRoundKey()
	{
		//TODO  
		fail();
	}

}
