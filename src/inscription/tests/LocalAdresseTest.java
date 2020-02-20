package inscription.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import inscription.modele.ExceptionCreationCompte;
import inscription.modele.LocalAdresse;

/**
 * Tests des méthodes de la classe LocalAdresse
 * 
 * @author Bank-era Corp.
 *
 */
public class LocalAdresseTest {

	private LocalAdresse a1, a2, a3, a4;

	@Before
	public void testLocalAdresseStringIntStringStringStringString() {
		try {
			a1 = new LocalAdresse("123 Abeille", "10", "G2H 6K9", "Québec", "Québec", "Canada");
		} catch (ExceptionCreationCompte e) {
			fail("Ne devrait pas se rendre là");
		}

	}

	@Before
	public void testLocalAdresseStringStringStringStringString() {
		try {
			a2 = new LocalAdresse("    148 OISEAU ", "  G1K  1K9", "Toro  nto", "Ontario", "Canada");
		} catch (ExceptionCreationCompte e) {
			fail("Ne devrait pas se rendre là");
		}

		try {
			a3 = new LocalAdresse("911 Police ", "G2K 3K8  ", "Vancouver  ", "British-Colombia", "Canada");
		} catch (ExceptionCreationCompte e) {
			fail("Ne devrait pas se rendre là");
		}

		try {
			a4 = new LocalAdresse("1600 Pennsylvania Ave NW     ", "g1q 1u8", "   Washington", "Washington DC",
					"United States");
		} catch (ExceptionCreationCompte e) {
			fail("Ne devrait pas se rendre là");
		}
	}

	@Test
	public void testInvalide() {

		LocalAdresse a;

		try {
			a = new LocalAdresse("", "g1q 1u8", "   Washington", "Washington DC", "United States");
			fail("L'adresse est vide");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			a = new LocalAdresse(null, "g1q 1u8", "   Washington", "Washington DC", "United States");
			fail("L'adresse est nulle");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			a = new LocalAdresse("1245 Hibou", null, "g1q 1u8", "   Washington", "Washington DC", "United States");
			fail("Le numéro d'appartment est null");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			a = new LocalAdresse("1 Crocodile", "g1q91u8", "   Washington", "Washington DC", "United States");
			fail("Le code postal est trop long");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			a = new LocalAdresse("1 Crocodile", "g1qu8", "   Washington", "Washington DC", "United States");
			fail("Le code postal est trop court");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			a = new LocalAdresse("AS", "g1q 1u8", "", "Washington DC", "United States");
			fail("La ville est vide");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			a = new LocalAdresse("AS", "g1q 1u8", "ST-LUCIE", "", "United States");
			fail("L'état est vide");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			a = new LocalAdresse("AS", "g1q 1u8", "ST-LUCIE", "Washington DC", "");
			fail("Le pays est vide");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			a = new LocalAdresse("AS", "g1q 1u8", null, "Washington DC", "United States");
			fail("La ville est nulle");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			a = new LocalAdresse("AS", "g1q 1u8", "ST-LUCIE", null, "United States");
			fail("L'état est nul");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			a = new LocalAdresse("AS", "g1q 1u8", "ST-LUCIE", "Washington DC", null);
			fail("Le pays est nul");
		} catch (ExceptionCreationCompte e) {

		}

	}

	@Test
	public void testGetAdresse() {
		assertTrue(a1.getAdresse().equals("123 Abeille"));
		assertTrue(a2.getAdresse().equals("148 OISEAU"));
		assertTrue(a3.getAdresse().equals("911 Police"));
		assertTrue(a4.getAdresse().equals("1600 Pennsylvania Ave NW"));
	}

	@Test
	public void testGetAppartement() {
		assertTrue(a1.getAppartement().equals("10"));
		assertTrue(a2.getAppartement().equals(""));
		assertTrue(a3.getAppartement().equals(""));
		assertTrue(a4.getAppartement().equals(""));
	}

	@Test
	public void testGetCodePostal() {
		assertTrue(a1.getCodePostal().equals("G2H6K9"));
		assertTrue(a2.getCodePostal().equals("G1K1K9"));
		assertTrue(a3.getCodePostal().equals("G2K3K8"));
		assertTrue(a4.getCodePostal().equals("G1Q1U8"));
	}

	@Test
	public void testGetVille() {
		assertTrue(a1.getVille().equals("QUÉBEC"));
		assertTrue(a2.getVille().equals("TORONTO"));
		assertTrue(a3.getVille().equals("VANCOUVER"));
		assertTrue(a4.getVille().equals("WASHINGTON"));
	}

	@Test
	public void testGetEtat() {
		assertTrue(a1.getEtat().equals("QUÉBEC"));
		assertTrue(a2.getEtat().equals("ONTARIO"));
		assertTrue(a3.getEtat().equals("BRITISH-COLOMBIA"));
		assertTrue(a4.getEtat().equals("WASHINGTONDC"));
	}

	@Test
	public void testGetPays() {
		assertTrue(a1.getPays().equals("CANADA"));
		assertTrue(a2.getPays().equals("CANADA"));
		assertTrue(a3.getPays().equals("CANADA"));
		assertTrue(a4.getPays().equals("UNITEDSTATES"));
	}

}
