package pos.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import commun.exception.ExceptionCreationCompte;
import commun.exception.ExceptionProduitEtablissement;
import commun.*;


/**
 * Classe testant la classe Etablissement
 * 
 * @author Bank-era
 *
 */
public class EtablissementTest {

	private Etablissement e1, e2;

	@Before
	public void testEtablissementStringStringFloatString() {

		try {
			e1 = new Etablissement("Allo", "Quebec", 0f, "allo@gmail.com");
			e2 = new Etablissement("Credigit", "Montreal", 10000f, "credigit@gmail.com");
		} catch (ExceptionProduitEtablissement e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInavlide() {

		try {
			e1 = new Etablissement("", "Quebec", 0f, "allo@gmail.com");

			fail("Nom vide");
		} catch (ExceptionProduitEtablissement e) {
		}

		try {
			e1 = new Etablissement(null, "Quebec", 0f, "allo@gmail.com");

			fail("Nom null");
		} catch (ExceptionProduitEtablissement e) {
		}

		try {
			e1 = new Etablissement("Allo", "", 0f, "allo@gmail.com");

			fail("Adresse vide");
		} catch (ExceptionProduitEtablissement e) {
		}

		try {
			e1 = new Etablissement("Allo", null, 0f, "allo@gmail.com");

			fail("Adresse null");
		} catch (ExceptionProduitEtablissement e) {
		}

		try {
			e1 = new Etablissement("Allo", "Quebec", 0f, "");

			fail("Courriel vide");
		} catch (ExceptionProduitEtablissement e) {
		}

		try {
			e1 = new Etablissement("Allo", "Quebec", 0f, null);

			fail("Courriel null");
		} catch (ExceptionProduitEtablissement e) {
		}

		try {
			e1 = new Etablissement("Allo", "Quebec", 0f, "allo@gmail.com");
			e1.ajouterProduitInventaire(null);

			fail("Ajout d'un produit null");
		} catch (ExceptionProduitEtablissement e) {
		}

		try {
			e1 = new Etablissement("Allo", "Quebec", 0f, "allo@gmail.com");
			e1.ajouterVendeur(null);

			fail("Ajout d'un vendeur null");
		} catch (ExceptionProduitEtablissement e) {
		}

		try {
			e1 = new Etablissement("Allo", "Quebec", 0f, "allo@gmail.com");
			e1.ajouterTransaction(null);

			fail("Ajout transaction null");
		} catch (ExceptionProduitEtablissement e) {
		}

	}

	@Test
	public void testGetNumero() {
		assertTrue(e1.getNumero() < 100000);
		assertTrue(e1.getNumero() > 9999);
		System.out.println(e1.getNumero());

		assertTrue(e2.getNumero() < 100000);
		assertTrue(e2.getNumero() > 9999);
		System.out.println(e2.getNumero());

	}

	@Test
	public void testGetNom() {
		assertTrue(e1.getNom().equals("Allo"));
		assertTrue(e2.getNom().equals("Credigit"));
	}

	@Test
	public void testGetAdresse() {
		assertTrue(e1.getAdresse().equals("Quebec"));
		assertTrue(e2.getAdresse().equals("Montreal"));
	}

	@Test
	public void testGetInventaire() {
		assertTrue(e1.getInventaire().size() == 0);
		assertTrue(e2.getInventaire().size() == 0);
	}

	@Test
	public void testAjouterProduitInventaire() {
		Produit p1 = null, p2 = null;
		DataProduit d1 = new DataProduit();

		byte[] array1 = { 0, 1 };

		d1.setCoutant(12.34f);
		d1.setDescription("Une banane d'Asie");
		d1.setFournisseur("China");
		d1.setImage(array1);
		d1.setNom("Banane");
		d1.setPrix(13.45f);
		d1.setQuantite(45);
		d1.setSku(1234);

		DataProduit d2 = new DataProduit();

		byte[] array2 = { 1, 1 };

		d2.setCoutant(2.3f);
		d2.setDescription("Un chocolat");
		d2.setFournisseur("Leclerc");
		d2.setImage(array2);
		d2.setNom("Chocolat");
		d2.setPrix(3.5f);
		d2.setQuantite(234);
		d2.setSku(564);

		try {
			p1 = new Produit(d1);
			p2 = new Produit(d2);
		} catch (ExceptionProduitEtablissement e) {
			e.printStackTrace();
		}

		try {
			e1.ajouterProduitInventaire(p1);
			e1.ajouterProduitInventaire(p2);
			e2.ajouterProduitInventaire(p2);
		} catch (ExceptionProduitEtablissement e) {
			e.printStackTrace();
		}

		assertTrue(e1.getInventaire().get(0).equals(p1));
		assertTrue(e1.getInventaire().get(1).equals(p2));
		assertTrue(e2.getInventaire().get(0).equals(p2));

		assertTrue(e1.getInventaire().size() == 2);
		assertTrue(e2.getInventaire().size() == 1);
	}

	@Test
	public void testGetUtilisateurs() {
		assertTrue(e1.getUtilisateurs().size() == 0);
		assertTrue(e2.getUtilisateurs().size() == 0);
	}

	@Test
	public void testAjouterVendeur() {

		Vendeur v1 = null, v2 = null;
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

		try {
			e1.ajouterVendeur(v1);
			e2.ajouterVendeur(v1);
			e2.ajouterVendeur(v2);
		} catch (ExceptionProduitEtablissement e) {
			e.printStackTrace();
		}

		assertTrue(e1.getUtilisateurs().get(0).equals(v1));
		assertTrue(e2.getUtilisateurs().get(0).equals(v1));
		assertTrue(e2.getUtilisateurs().get(1).equals(v2));

		assertTrue(e1.getUtilisateurs().size() == 1);
		assertTrue(e2.getUtilisateurs().size() == 2);
	}

	@Test
	public void testGetBalance() {
		assertTrue(e1.getBalance() == 0f);
		assertTrue(e2.getBalance() == 10000f);
	}

	@Test
	public void testAjouterBalance() {
		e1.ajouterBalance(345.89f);
		assertTrue(e1.getBalance() == 345.89f);

		e2.ajouterBalance(45.6f);
		assertTrue(e2.getBalance() == 10045.6f);

		e2.ajouterBalance(-1000f);
		assertTrue(e2.getBalance() == 9045.6f);
	}

	@Test
	public void testGetTransactions() {
		assertTrue(e1.getTransactions().size() == 0);
		Transaction transactionTest = new Transaction(e1);
		try {
			e2.ajouterTransaction(transactionTest);
		} catch (ExceptionProduitEtablissement e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(e2.getTransactions().size() == 1);
		assertTrue(e2.getTransactions().get(0) == transactionTest);
	}

	@Test
	public void testAjouterTransaction() {
		assertTrue(e1.getTransactions().size() == 0);
		Transaction transactionTest = new Transaction(e1);
		try {
			e1.ajouterTransaction(transactionTest);
		} catch (ExceptionProduitEtablissement e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(e1.getTransactions().size() == 1);
		assertTrue(e1.getTransactions().get(0) == transactionTest);

		assertTrue(e2.getTransactions().size() == 0);
		Transaction transactionTest2 = new Transaction(e2);
		Transaction transactionTest3 = new Transaction(e2);
		try {
			e2.ajouterTransaction(transactionTest2);
			e2.ajouterTransaction(transactionTest3);
		} catch (ExceptionProduitEtablissement e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(e2.getTransactions().size() == 2);
		assertTrue(e2.getTransactions().get(0) == transactionTest2);
		assertTrue(e2.getTransactions().get(1) == transactionTest3);
	}

	@Test
	public void testGetCourriel() {
		assertTrue(e1.getCourriel().equals("allo@gmail.com"));
		assertTrue(e2.getCourriel().equals("credigit@gmail.com"));
	}
	
	@Test
	public void testSupprimerProduitInventaire() {
		Produit p1 = null, p2 = null;
		DataProduit d1 = new DataProduit();

		byte[] array1 = { 0, 1 };

		d1.setCoutant(12.34f);
		d1.setDescription("Une banane d'Asie");
		d1.setFournisseur("China");
		d1.setImage(array1);
		d1.setNom("Banane");
		d1.setPrix(13.45f);
		d1.setQuantite(45);
		d1.setSku(1234);

		DataProduit d2 = new DataProduit();

		byte[] array2 = { 1, 1 };

		d2.setCoutant(2.3f);
		d2.setDescription("Un chocolat");
		d2.setFournisseur("Leclerc");
		d2.setImage(array2);
		d2.setNom("Chocolat");
		d2.setPrix(3.5f);
		d2.setQuantite(234);
		d2.setSku(564);
		
		try {
			p1 = new Produit(d1);
			p2 = new Produit(d2);
		} catch (ExceptionProduitEtablissement e) {
			e.printStackTrace();
		}

		try {
			e1.ajouterProduitInventaire(p1);
			e1.ajouterProduitInventaire(p2);
			e2.ajouterProduitInventaire(p2);
		} catch (ExceptionProduitEtablissement e) {
			e.printStackTrace();
		}
		
		assertTrue(e1.supprimerProduitInventaire(p1));
		assertTrue(e1.supprimerProduitInventaire(p2));
		assertTrue(e2.supprimerProduitInventaire(p2));
		
		assertTrue(e1.getInventaire().isEmpty());
		assertTrue(e2.getInventaire().isEmpty());
	}

}
