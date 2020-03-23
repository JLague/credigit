package pos.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pos.exception.ExceptionCreationCompte;
import pos.modele.DataVendeur;
import pos.modele.Vendeur;

/**
 * Classe testant la classe Vendeur
 * 
 * @author Bank-era
 *
 */
public class VendeurTest {

	private Vendeur v1, v2;

	@Before
	public void testVendeurDataVendeur() {

		DataVendeur d1 = new DataVendeur();

		d1.setCourriel("test1@gmail.com");
		d1.setNom("Test");
		d1.setPrenom("Un");
		d1.setPassword("Test1234");
		d1.setUsername("LePremierTest");

		DataVendeur d2 = new DataVendeur();

		d2.setCourriel("jean.talbot1@outlook.com");
		d2.setNom("Talbot");
		d2.setPrenom("Jean");
		d2.setPassword("Soccer12");
		d2.setUsername("Johnny");

		try {
			v1 = new Vendeur(d1);
			v2 = new Vendeur(d2);
		} catch (ExceptionCreationCompte e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInvalide() {
		try {
			DataVendeur d1 = new DataVendeur();

			d1.setCourriel("");
			d1.setNom("Test");
			d1.setPrenom("Un");
			d1.setPassword("Test1234");
			d1.setUsername("LePremierTest");

			Vendeur v = new Vendeur(d1);
			fail("Courriel vide");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			DataVendeur d1 = new DataVendeur();

			d1.setCourriel(null);
			d1.setNom("Test");
			d1.setPrenom("Un");
			d1.setPassword("Test1234");
			d1.setUsername("LePremierTest");

			Vendeur v = new Vendeur(d1);
			fail("Courriel null");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			DataVendeur d1 = new DataVendeur();

			d1.setCourriel("test1@gmail.com");
			d1.setNom("");
			d1.setPrenom("Un");
			d1.setPassword("Test1234");
			d1.setUsername("LePremierTest");

			Vendeur v = new Vendeur(d1);
			fail("Nom vide");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			DataVendeur d1 = new DataVendeur();

			d1.setCourriel("test1@gmail.com");
			d1.setNom(null);
			d1.setPrenom("Un");
			d1.setPassword("Test1234");
			d1.setUsername("LePremierTest");

			Vendeur v = new Vendeur(d1);
			fail("Nom null");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			DataVendeur d1 = new DataVendeur();

			d1.setCourriel("test1@gmail.com");
			d1.setNom("Test");
			d1.setPrenom("");
			d1.setPassword("Test1234");
			d1.setUsername("LePremierTest");

			Vendeur v = new Vendeur(d1);
			fail("Prenom vide");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			DataVendeur d1 = new DataVendeur();

			d1.setCourriel("test1@gmail.com");
			d1.setNom("Test");
			d1.setPrenom(null);
			d1.setPassword("Test1234");
			d1.setUsername("LePremierTest");

			Vendeur v = new Vendeur(d1);
			fail("Prenom null");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			DataVendeur d1 = new DataVendeur();

			d1.setCourriel("test1@gmail.com");
			d1.setNom("Test");
			d1.setPrenom("Un");
			d1.setPassword("Test123");
			d1.setUsername("LePremierTest");

			Vendeur v = new Vendeur(d1);
			fail("Password trop court");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			DataVendeur d1 = new DataVendeur();

			d1.setCourriel("test1@gmail.com");
			d1.setNom("Test");
			d1.setPrenom("Un");
			d1.setPassword(null);
			d1.setUsername("LePremierTest");

			Vendeur v = new Vendeur(d1);
			fail("Password null");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			DataVendeur d1 = new DataVendeur();

			d1.setCourriel("test1@gmail.com");
			d1.setNom("Test");
			d1.setPrenom("Un");
			d1.setPassword("Test1234");
			d1.setUsername("");

			Vendeur v = new Vendeur(d1);
			fail("Username vide");
		} catch (ExceptionCreationCompte e) {

		}

		try {
			DataVendeur d1 = new DataVendeur();

			d1.setCourriel("test1@gmail.com");
			d1.setNom("Test");
			d1.setPrenom("Un");
			d1.setPassword("Test1234");
			d1.setUsername(null);

			Vendeur v = new Vendeur(d1);
			fail("Username null");
		} catch (ExceptionCreationCompte e) {

		}

	}

	@Test
	public void testGetPrenom() {
		assertTrue(v1.getPrenom().equals("Un"));
		assertTrue(v2.getPrenom().equals("Jean"));
	}

	@Test
	public void testGetNom() {
		assertTrue(v1.getNom().equals("Test"));
		assertTrue(v2.getNom().equals("Talbot"));
	}

	@Test
	public void testGetUsername() {
		assertTrue(v1.getUsername().equals("LePremierTest"));
		assertTrue(v2.getUsername().equals("Johnny"));
	}

	@Test
	public void testGetPassword() {
		assertTrue(v1.getPassword().equals(pos.utils.SHAUtil.hashPassword("Test1234")));
		assertTrue(v2.getPassword().equals(pos.utils.SHAUtil.hashPassword("Soccer12")));
	}

	@Test
	public void testGetCourriel() {
		assertTrue(v1.getCourriel().equals("test1@gmail.com"));
		assertTrue(v2.getCourriel().equals("jean.talbot1@outlook.com"));
	}

}
