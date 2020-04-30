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

//	private AES e1, e2;
//
//	@Before
//	public void creerEncryption() {
//		e1 = new AES("000102030405060708090a0b0c0d0e0f", "Ceci est un test");
//
//		e2 = new AES("000102030405060708090a0b0c0d0e0f", "Ceci est un test, que nous devons faire");
//	}
//
//	@Test
//	public void testGetMatrice() {
//
//		// Vérifie qu'il y aille le bon nombre de matrice et que celles-ci soient bien
//		// remplies
//		List<String[][]> mat1 = e1.getMatrice();
//
//		assertTrue(mat1.size() == 1);
//
//		assertTrue(mat1.get(0)[0][0].equals("43"));
//		assertTrue(mat1.get(0)[0][1].equals("20"));
//		assertTrue(mat1.get(0)[0][2].equals("20"));
//		assertTrue(mat1.get(0)[0][3].equals("74"));
//		assertTrue(mat1.get(0)[1][0].equals("65"));
//		assertTrue(mat1.get(0)[1][1].equals("65"));
//		assertTrue(mat1.get(0)[1][2].equals("75"));
//		assertTrue(mat1.get(0)[1][3].equals("65"));
//		assertTrue(mat1.get(0)[2][0].equals("63"));
//		assertTrue(mat1.get(0)[2][1].equals("73"));
//		assertTrue(mat1.get(0)[2][2].equals("6e"));
//		assertTrue(mat1.get(0)[2][3].equals("73"));
//		assertTrue(mat1.get(0)[3][0].equals("69"));
//		assertTrue(mat1.get(0)[3][1].equals("74"));
//		assertTrue(mat1.get(0)[3][2].equals("20"));
//		assertTrue(mat1.get(0)[3][3].equals("74"));
//
//		List<String[][]> mat2 = e2.getMatrice();
//
//		assertTrue(mat2.size() == 3);
//
//		assertTrue(mat2.get(0)[0][0].equals("43"));
//		assertTrue(mat2.get(0)[0][1].equals("20"));
//		assertTrue(mat2.get(0)[0][2].equals("20"));
//		assertTrue(mat2.get(0)[0][3].equals("74"));
//		assertTrue(mat2.get(0)[1][0].equals("65"));
//		assertTrue(mat2.get(0)[1][1].equals("65"));
//		assertTrue(mat2.get(0)[1][2].equals("75"));
//		assertTrue(mat2.get(0)[1][3].equals("65"));
//		assertTrue(mat2.get(0)[2][0].equals("63"));
//		assertTrue(mat2.get(0)[2][1].equals("73"));
//		assertTrue(mat2.get(0)[2][2].equals("6e"));
//		assertTrue(mat2.get(0)[2][3].equals("73"));
//		assertTrue(mat2.get(0)[3][0].equals("69"));
//		assertTrue(mat2.get(0)[3][1].equals("74"));
//		assertTrue(mat2.get(0)[3][2].equals("20"));
//		assertTrue(mat2.get(0)[3][3].equals("74"));
//
//		assertTrue(mat2.get(1)[0][0].equals("2c"));
//		assertTrue(mat2.get(1)[0][1].equals("65"));
//		assertTrue(mat2.get(1)[0][2].equals("75"));
//		assertTrue(mat2.get(1)[0][3].equals("65"));
//		assertTrue(mat2.get(1)[1][0].equals("20"));
//		assertTrue(mat2.get(1)[1][1].equals("20"));
//		assertTrue(mat2.get(1)[1][2].equals("73"));
//		assertTrue(mat2.get(1)[1][3].equals("76"));
//		assertTrue(mat2.get(1)[2][0].equals("71"));
//		assertTrue(mat2.get(1)[2][1].equals("6e"));
//		assertTrue(mat2.get(1)[2][2].equals("20"));
//		assertTrue(mat2.get(1)[2][3].equals("6f"));
//		assertTrue(mat2.get(1)[3][0].equals("75"));
//		assertTrue(mat2.get(1)[3][1].equals("6f"));
//		assertTrue(mat2.get(1)[3][2].equals("64"));
//		assertTrue(mat2.get(1)[3][3].equals("6e"));
//
//		assertTrue(mat2.get(2)[0][0].equals("73"));
//		assertTrue(mat2.get(2)[0][1].equals("69"));
//		assertTrue(mat2.get(2)[0][2].equals("20"));
//		assertTrue(mat2.get(2)[0][3].equals("20"));
//		assertTrue(mat2.get(2)[1][0].equals("20"));
//		assertTrue(mat2.get(2)[1][1].equals("72"));
//		assertTrue(mat2.get(2)[1][2].equals("20"));
//		assertTrue(mat2.get(2)[1][3].equals("20"));
//		assertTrue(mat2.get(2)[2][0].equals("66"));
//		assertTrue(mat2.get(2)[2][1].equals("65"));
//		assertTrue(mat2.get(2)[2][2].equals("20"));
//		assertTrue(mat2.get(2)[2][3].equals("20"));
//		assertTrue(mat2.get(2)[3][0].equals("61"));
//		assertTrue(mat2.get(2)[3][1].equals("20"));
//		assertTrue(mat2.get(2)[3][2].equals("20"));
//		assertTrue(mat2.get(2)[3][3].equals("20"));
//	}
//
//	@Test
//	public void testGetTrousseau() {
//		assertTrue(e1.getTrousseau() != null);
//		assertTrue(e2.getTrousseau() != null);
//
//	}
//
//	@Test
//	public void testShiftRows() {
//
//		// Vérifie que le shiftRow fonctionne adéquatement
//		e1.shiftRows();
//
//		List<String[][]> mat1 = e1.getMatrice();
//
//		assertTrue(mat1.size() == 1);
//
//		assertTrue(mat1.get(0)[0][0].equals("43"));
//		assertTrue(mat1.get(0)[0][1].equals("20"));
//		assertTrue(mat1.get(0)[0][2].equals("20"));
//		assertTrue(mat1.get(0)[0][3].equals("74"));
//		assertTrue(mat1.get(0)[1][3].equals("65"));
//		assertTrue(mat1.get(0)[1][0].equals("65"));
//		assertTrue(mat1.get(0)[1][1].equals("75"));
//		assertTrue(mat1.get(0)[1][2].equals("65"));
//		assertTrue(mat1.get(0)[2][2].equals("63"));
//		assertTrue(mat1.get(0)[2][3].equals("73"));
//		assertTrue(mat1.get(0)[2][0].equals("6e"));
//		assertTrue(mat1.get(0)[2][1].equals("73"));
//		assertTrue(mat1.get(0)[3][1].equals("69"));
//		assertTrue(mat1.get(0)[3][2].equals("74"));
//		assertTrue(mat1.get(0)[3][3].equals("20"));
//		assertTrue(mat1.get(0)[3][0].equals("74"));
//
//		e2.shiftRows();
//
//		List<String[][]> mat2 = e2.getMatrice();
//
//		assertTrue(mat2.size() == 3);
//
//		assertTrue(mat2.get(0)[0][0].equals("43"));
//		assertTrue(mat2.get(0)[0][1].equals("20"));
//		assertTrue(mat2.get(0)[0][2].equals("20"));
//		assertTrue(mat2.get(0)[0][3].equals("74"));
//		assertTrue(mat2.get(0)[1][3].equals("65"));
//		assertTrue(mat2.get(0)[1][0].equals("65"));
//		assertTrue(mat2.get(0)[1][1].equals("75"));
//		assertTrue(mat2.get(0)[1][2].equals("65"));
//		assertTrue(mat2.get(0)[2][2].equals("63"));
//		assertTrue(mat2.get(0)[2][3].equals("73"));
//		assertTrue(mat2.get(0)[2][0].equals("6e"));
//		assertTrue(mat2.get(0)[2][1].equals("73"));
//		assertTrue(mat2.get(0)[3][1].equals("69"));
//		assertTrue(mat2.get(0)[3][2].equals("74"));
//		assertTrue(mat2.get(0)[3][3].equals("20"));
//		assertTrue(mat2.get(0)[3][0].equals("74"));
//
//		assertTrue(mat2.get(1)[0][0].equals("2c"));
//		assertTrue(mat2.get(1)[0][1].equals("65"));
//		assertTrue(mat2.get(1)[0][2].equals("75"));
//		assertTrue(mat2.get(1)[0][3].equals("65"));
//		assertTrue(mat2.get(1)[1][3].equals("20"));
//		assertTrue(mat2.get(1)[1][0].equals("20"));
//		assertTrue(mat2.get(1)[1][1].equals("73"));
//		assertTrue(mat2.get(1)[1][2].equals("76"));
//		assertTrue(mat2.get(1)[2][2].equals("71"));
//		assertTrue(mat2.get(1)[2][3].equals("6e"));
//		assertTrue(mat2.get(1)[2][0].equals("20"));
//		assertTrue(mat2.get(1)[2][1].equals("6f"));
//		assertTrue(mat2.get(1)[3][1].equals("75"));
//		assertTrue(mat2.get(1)[3][2].equals("6f"));
//		assertTrue(mat2.get(1)[3][3].equals("64"));
//		assertTrue(mat2.get(1)[3][0].equals("6e"));
//
//		assertTrue(mat2.get(2)[0][0].equals("73"));
//		assertTrue(mat2.get(2)[0][1].equals("69"));
//		assertTrue(mat2.get(2)[0][2].equals("20"));
//		assertTrue(mat2.get(2)[0][3].equals("20"));
//		assertTrue(mat2.get(2)[1][3].equals("20"));
//		assertTrue(mat2.get(2)[1][0].equals("72"));
//		assertTrue(mat2.get(2)[1][1].equals("20"));
//		assertTrue(mat2.get(2)[1][2].equals("20"));
//		assertTrue(mat2.get(2)[2][2].equals("66"));
//		assertTrue(mat2.get(2)[2][3].equals("65"));
//		assertTrue(mat2.get(2)[2][0].equals("20"));
//		assertTrue(mat2.get(2)[2][1].equals("20"));
//		assertTrue(mat2.get(2)[3][1].equals("61"));
//		assertTrue(mat2.get(2)[3][2].equals("20"));
//		assertTrue(mat2.get(2)[3][3].equals("20"));
//		assertTrue(mat2.get(2)[3][0].equals("20"));
//
//	}
//
//	@Test
//	public void testShiftRowsInverse() {
//		// Vérifie que le shiftRow et le shiftRowInverse fonctionne adéquatement
//		e1.shiftRows();
//
//		List<String[][]> mat1 = e1.getMatrice();
//
//		assertTrue(mat1.size() == 1);
//
//		assertTrue(mat1.get(0)[0][0].equals("43"));
//		assertTrue(mat1.get(0)[0][1].equals("20"));
//		assertTrue(mat1.get(0)[0][2].equals("20"));
//		assertTrue(mat1.get(0)[0][3].equals("74"));
//		assertTrue(mat1.get(0)[1][3].equals("65"));
//		assertTrue(mat1.get(0)[1][0].equals("65"));
//		assertTrue(mat1.get(0)[1][1].equals("75"));
//		assertTrue(mat1.get(0)[1][2].equals("65"));
//		assertTrue(mat1.get(0)[2][2].equals("63"));
//		assertTrue(mat1.get(0)[2][3].equals("73"));
//		assertTrue(mat1.get(0)[2][0].equals("6e"));
//		assertTrue(mat1.get(0)[2][1].equals("73"));
//		assertTrue(mat1.get(0)[3][1].equals("69"));
//		assertTrue(mat1.get(0)[3][2].equals("74"));
//		assertTrue(mat1.get(0)[3][3].equals("20"));
//		assertTrue(mat1.get(0)[3][0].equals("74"));
//
//		// On devrait retourver la matrice originale
//		e1.shiftRowsInverse();
//
//		List<String[][]> mat3 = e1.getMatrice();
//
//		assertTrue(mat3.size() == 1);
//
//		assertTrue(mat3.get(0)[0][0].equals("43"));
//		assertTrue(mat3.get(0)[0][1].equals("20"));
//		assertTrue(mat3.get(0)[0][2].equals("20"));
//		assertTrue(mat3.get(0)[0][3].equals("74"));
//		assertTrue(mat3.get(0)[1][0].equals("65"));
//		assertTrue(mat3.get(0)[1][1].equals("65"));
//		assertTrue(mat3.get(0)[1][2].equals("75"));
//		assertTrue(mat3.get(0)[1][3].equals("65"));
//		assertTrue(mat3.get(0)[2][0].equals("63"));
//		assertTrue(mat3.get(0)[2][1].equals("73"));
//		assertTrue(mat3.get(0)[2][2].equals("6e"));
//		assertTrue(mat3.get(0)[2][3].equals("73"));
//		assertTrue(mat3.get(0)[3][0].equals("69"));
//		assertTrue(mat3.get(0)[3][1].equals("74"));
//		assertTrue(mat3.get(0)[3][2].equals("20"));
//		assertTrue(mat3.get(0)[3][3].equals("74"));
//
//		// Shift les rangées de la seconde matrice
//		e2.shiftRows();
//
//		List<String[][]> mat2 = e2.getMatrice();
//
//		assertTrue(mat2.size() == 3);
//
//		assertTrue(mat2.get(0)[0][0].equals("43"));
//		assertTrue(mat2.get(0)[0][1].equals("20"));
//		assertTrue(mat2.get(0)[0][2].equals("20"));
//		assertTrue(mat2.get(0)[0][3].equals("74"));
//		assertTrue(mat2.get(0)[1][3].equals("65"));
//		assertTrue(mat2.get(0)[1][0].equals("65"));
//		assertTrue(mat2.get(0)[1][1].equals("75"));
//		assertTrue(mat2.get(0)[1][2].equals("65"));
//		assertTrue(mat2.get(0)[2][2].equals("63"));
//		assertTrue(mat2.get(0)[2][3].equals("73"));
//		assertTrue(mat2.get(0)[2][0].equals("6e"));
//		assertTrue(mat2.get(0)[2][1].equals("73"));
//		assertTrue(mat2.get(0)[3][1].equals("69"));
//		assertTrue(mat2.get(0)[3][2].equals("74"));
//		assertTrue(mat2.get(0)[3][3].equals("20"));
//		assertTrue(mat2.get(0)[3][0].equals("74"));
//
//		assertTrue(mat2.get(1)[0][0].equals("2c"));
//		assertTrue(mat2.get(1)[0][1].equals("65"));
//		assertTrue(mat2.get(1)[0][2].equals("75"));
//		assertTrue(mat2.get(1)[0][3].equals("65"));
//		assertTrue(mat2.get(1)[1][3].equals("20"));
//		assertTrue(mat2.get(1)[1][0].equals("20"));
//		assertTrue(mat2.get(1)[1][1].equals("73"));
//		assertTrue(mat2.get(1)[1][2].equals("76"));
//		assertTrue(mat2.get(1)[2][2].equals("71"));
//		assertTrue(mat2.get(1)[2][3].equals("6e"));
//		assertTrue(mat2.get(1)[2][0].equals("20"));
//		assertTrue(mat2.get(1)[2][1].equals("6f"));
//		assertTrue(mat2.get(1)[3][1].equals("75"));
//		assertTrue(mat2.get(1)[3][2].equals("6f"));
//		assertTrue(mat2.get(1)[3][3].equals("64"));
//		assertTrue(mat2.get(1)[3][0].equals("6e"));
//
//		assertTrue(mat2.get(2)[0][0].equals("73"));
//		assertTrue(mat2.get(2)[0][1].equals("69"));
//		assertTrue(mat2.get(2)[0][2].equals("20"));
//		assertTrue(mat2.get(2)[0][3].equals("20"));
//		assertTrue(mat2.get(2)[1][3].equals("20"));
//		assertTrue(mat2.get(2)[1][0].equals("72"));
//		assertTrue(mat2.get(2)[1][1].equals("20"));
//		assertTrue(mat2.get(2)[1][2].equals("20"));
//		assertTrue(mat2.get(2)[2][2].equals("66"));
//		assertTrue(mat2.get(2)[2][3].equals("65"));
//		assertTrue(mat2.get(2)[2][0].equals("20"));
//		assertTrue(mat2.get(2)[2][1].equals("20"));
//		assertTrue(mat2.get(2)[3][1].equals("61"));
//		assertTrue(mat2.get(2)[3][2].equals("20"));
//		assertTrue(mat2.get(2)[3][3].equals("20"));
//		assertTrue(mat2.get(2)[3][0].equals("20"));
//
//		// On devrait retrouver la matrice originale
//		e2.shiftRowsInverse();
//
//		List<String[][]> mat4 = e2.getMatrice();
//
//		assertTrue(mat4.size() == 3);
//
//		assertTrue(mat4.get(0)[0][0].equals("43"));
//		assertTrue(mat4.get(0)[0][1].equals("20"));
//		assertTrue(mat4.get(0)[0][2].equals("20"));
//		assertTrue(mat4.get(0)[0][3].equals("74"));
//		assertTrue(mat4.get(0)[1][0].equals("65"));
//		assertTrue(mat4.get(0)[1][1].equals("65"));
//		assertTrue(mat4.get(0)[1][2].equals("75"));
//		assertTrue(mat4.get(0)[1][3].equals("65"));
//		assertTrue(mat4.get(0)[2][0].equals("63"));
//		assertTrue(mat4.get(0)[2][1].equals("73"));
//		assertTrue(mat4.get(0)[2][2].equals("6e"));
//		assertTrue(mat4.get(0)[2][3].equals("73"));
//		assertTrue(mat4.get(0)[3][0].equals("69"));
//		assertTrue(mat4.get(0)[3][1].equals("74"));
//		assertTrue(mat4.get(0)[3][2].equals("20"));
//		assertTrue(mat4.get(0)[3][3].equals("74"));
//
//		assertTrue(mat4.get(1)[0][0].equals("2c"));
//		assertTrue(mat4.get(1)[0][1].equals("65"));
//		assertTrue(mat4.get(1)[0][2].equals("75"));
//		assertTrue(mat4.get(1)[0][3].equals("65"));
//		assertTrue(mat4.get(1)[1][0].equals("20"));
//		assertTrue(mat4.get(1)[1][1].equals("20"));
//		assertTrue(mat4.get(1)[1][2].equals("73"));
//		assertTrue(mat4.get(1)[1][3].equals("76"));
//		assertTrue(mat4.get(1)[2][0].equals("71"));
//		assertTrue(mat4.get(1)[2][1].equals("6e"));
//		assertTrue(mat4.get(1)[2][2].equals("20"));
//		assertTrue(mat4.get(1)[2][3].equals("6f"));
//		assertTrue(mat4.get(1)[3][0].equals("75"));
//		assertTrue(mat4.get(1)[3][1].equals("6f"));
//		assertTrue(mat4.get(1)[3][2].equals("64"));
//		assertTrue(mat4.get(1)[3][3].equals("6e"));
//
//		assertTrue(mat4.get(2)[0][0].equals("73"));
//		assertTrue(mat4.get(2)[0][1].equals("69"));
//		assertTrue(mat4.get(2)[0][2].equals("20"));
//		assertTrue(mat4.get(2)[0][3].equals("20"));
//		assertTrue(mat4.get(2)[1][0].equals("20"));
//		assertTrue(mat4.get(2)[1][1].equals("72"));
//		assertTrue(mat4.get(2)[1][2].equals("20"));
//		assertTrue(mat4.get(2)[1][3].equals("20"));
//		assertTrue(mat4.get(2)[2][0].equals("66"));
//		assertTrue(mat4.get(2)[2][1].equals("65"));
//		assertTrue(mat4.get(2)[2][2].equals("20"));
//		assertTrue(mat4.get(2)[2][3].equals("20"));
//		assertTrue(mat4.get(2)[3][0].equals("61"));
//		assertTrue(mat4.get(2)[3][1].equals("20"));
//		assertTrue(mat4.get(2)[3][2].equals("20"));
//		assertTrue(mat4.get(2)[3][3].equals("20"));
//
//	}

//	@Pass
//	public void testSubstitutionAvant() {
//		assertTrue(AES.substitutionAvant("1D").equals("A4"));
//		assertTrue(AES.substitutionAvant("37").equals("9A"));
//		assertTrue(AES.substitutionAvant("81").equals("0C"));
//		assertTrue(AES.substitutionAvant("A5").equals("06"));
//		assertTrue(AES.substitutionAvant("FE").equals("BB"));
//	}

//	@Test
//	public void testMixColumn() {
//		String m = "6353e08c0960e104cd70b751bacad0e7";
//		String s = "";
//		String temp = "";
//		for (int j = 0; j < 4; j++) {
//			for (int i = 0; i < 4; i++) {
//				temp = Integer.toHexString(AES.bitwiseMultiply(Integer.parseInt(AES.getNByte(m, i * 4 + 0), 16), 2)
//						+ AES.bitwiseMultiply(Integer.parseInt(AES.getNByte(m, i * 4 + 5), 16), 3)
//						+ Integer.parseInt(AES.getNByte(m, i * 4 + 10), 16)
//						+ Integer.parseInt(AES.getNByte(m, i * 4 + 15), 16));
//				s += (Integer.parseInt(temp, 16) > 256
//						? Integer.toHexString((AES.bitwiseLongDivision(Integer.parseInt(temp, 16), 0b10001)))
//						: temp);
//				temp = Integer.toHexString(Integer.parseInt(AES.getNByte(m, i * 4 + 0), 16)
//						+ Integer.parseInt(AES.getNByte(m, i * 4 + 5), 16) * 2
//						+ Integer.parseInt(AES.getNByte(m, i * 4 + 10), 16) * 3
//						+ Integer.parseInt(AES.getNByte(m, i * 4 + 15), 16));
//				s += (Integer.parseInt(temp, 16) > 256
//						? Integer.toHexString((AES.bitwiseLongDivision(Integer.parseInt(temp, 16), 0b10001)))
//						: temp);
//				temp = Integer.toHexString(Integer.parseInt(AES.getNByte(m, i * 4 + 0), 16)
//						+ Integer.parseInt(AES.getNByte(m, i * 4 + 5), 16)
//						+ Integer.parseInt(AES.getNByte(m, i * 4 + 10), 16) * 2
//						+ Integer.parseInt(AES.getNByte(m, i * 4 + 15), 16) * 3);
//				s += (Integer.parseInt(temp, 16) > 256
//						? Integer.toHexString((AES.bitwiseLongDivision(Integer.parseInt(temp, 16), 0b10001)))
//						: temp);
//				temp = Integer.toHexString(Integer.parseInt(AES.getNByte(m, i * 4 + 0), 16) * 3
//						+ Integer.parseInt(AES.getNByte(m, i * 4 + 5), 16)
//						+ Integer.parseInt(AES.getNByte(m, i * 4 + 10), 16)
//						+ Integer.parseInt(AES.getNByte(m, i * 4 + 15), 16) * 2);
//				s += (Integer.parseInt(temp, 16) > 256
//						? Integer.toHexString((AES.bitwiseLongDivision(Integer.parseInt(temp, 16), 0b10001)))
//						: temp);
//			}
//		}
//		System.out.println(s);
//	}
	
	@Test
	public void testMix()
	{
		List<String[][]> liste = new ArrayList<>();
		
		String[][] s = {{"63", "09", "cd", "ba"}, {"53", "60", "70", "ca"}, {"e0", "e1", "b7", "d0"}, {"8c", "04", "51", "e7"}};
		
		liste.add(s);
		
		AES.mixColumns(liste);
		
		AES.mixColumnsInverse(liste);
		
		System.out.println();
	}

}
