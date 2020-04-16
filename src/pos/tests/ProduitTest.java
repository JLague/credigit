package pos.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import commun.exception.ExceptionProduitEtablissement;
import commun.DataProduit;
import commun.Produit;

/**
 * Classe testant la classe produit
 * 
 * @author Bank-era
 *
 */
public class ProduitTest {

	private Produit p1, p2;

	@Before
	public void testProduitDataProduit() {

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testInvalide() {
		try {

			DataProduit d1 = new DataProduit();

			byte[] array1 = { 0, 1 };

			d1.setCoutant(-12.34f);
			d1.setDescription("Une banane d'Asie");
			d1.setFournisseur("China");
			d1.setImage(array1);
			d1.setNom("Banane");
			d1.setPrix(13.45f);
			d1.setQuantite(45);
			d1.setSku(1234);

			new Produit(d1);

			fail("Coutant négatif");
		} catch (ExceptionProduitEtablissement e) {

		}

		try {

			DataProduit d1 = new DataProduit();

			byte[] array1 = { 0, 1 };

			d1.setCoutant(12.34f);
			d1.setDescription("");
			d1.setFournisseur("China");
			d1.setImage(array1);
			d1.setNom("Banane");
			d1.setPrix(13.45f);
			d1.setQuantite(45);
			d1.setSku(1234);

			new Produit(d1);

			fail("Description vide");
		} catch (ExceptionProduitEtablissement e) {

		}

		try {

			DataProduit d1 = new DataProduit();

			byte[] array1 = { 0, 1 };

			d1.setCoutant(12.34f);
			d1.setDescription(null);
			d1.setFournisseur("China");
			d1.setImage(array1);
			d1.setNom("Banane");
			d1.setPrix(13.45f);
			d1.setQuantite(45);
			d1.setSku(1234);

			new Produit(d1);

			fail("Description null");
		} catch (ExceptionProduitEtablissement e) {

		}

		try {

			DataProduit d1 = new DataProduit();

			byte[] array1 = { 0, 1 };

			d1.setCoutant(12.34f);
			d1.setDescription("Une banane d'Asie");
			d1.setFournisseur("");
			d1.setImage(array1);
			d1.setNom("Banane");
			d1.setPrix(13.45f);
			d1.setQuantite(45);
			d1.setSku(1234);

			new Produit(d1);

			fail("Fournisseur vide");
		} catch (ExceptionProduitEtablissement e) {

		}

		try {

			DataProduit d1 = new DataProduit();

			byte[] array1 = { 0, 1 };

			d1.setCoutant(12.34f);
			d1.setDescription("Une banane d'Asie");
			d1.setFournisseur(null);
			d1.setImage(array1);
			d1.setNom("Banane");
			d1.setPrix(13.45f);
			d1.setQuantite(45);
			d1.setSku(1234);

			new Produit(d1);

			fail("Fournisseur null");
		} catch (ExceptionProduitEtablissement e) {

		}

		try {

			DataProduit d1 = new DataProduit();

			byte[] array1 = { 0, 1 };

			d1.setCoutant(12.34f);
			d1.setDescription("Une banane d'Asie");
			d1.setFournisseur("China");
			d1.setImage(array1);
			d1.setNom("");
			d1.setPrix(13.45f);
			d1.setQuantite(45);
			d1.setSku(1234);

			new Produit(d1);

			fail("Nom vide");
		} catch (ExceptionProduitEtablissement e) {

		}

		try {

			DataProduit d1 = new DataProduit();

			byte[] array1 = { 0, 1 };

			d1.setCoutant(12.34f);
			d1.setDescription("Une banane d'Asie");
			d1.setFournisseur("China");
			d1.setImage(array1);
			d1.setNom(null);
			d1.setPrix(13.45f);
			d1.setQuantite(45);
			d1.setSku(1234);

			new Produit(d1);

			fail("Nom null");
		} catch (ExceptionProduitEtablissement e) {

		}

		try {

			DataProduit d1 = new DataProduit();

			byte[] array1 = { 0, 1 };

			d1.setCoutant(12.34f);
			d1.setDescription("Une banane d'Asie");
			d1.setFournisseur("China");
			d1.setImage(array1);
			d1.setNom("Banane");
			d1.setPrix(-13.45f);
			d1.setQuantite(45);
			d1.setSku(1234);

			new Produit(d1);

			fail("Prix négatif");
		} catch (ExceptionProduitEtablissement e) {

		}

		try {

			DataProduit d1 = new DataProduit();

			byte[] array1 = { 0, 1 };

			d1.setCoutant(12.34f);
			d1.setDescription("Une banane d'Asie");
			d1.setFournisseur("China");
			d1.setImage(array1);
			d1.setNom("Banane");
			d1.setPrix(13.45f);
			d1.setQuantite(-1);
			d1.setSku(1234);

			new Produit(d1);

			fail("Quantité négative");
		} catch (ExceptionProduitEtablissement e) {

		}

		try {

			DataProduit d1 = new DataProduit();

			byte[] array1 = { 0, 1 };

			d1.setCoutant(12.34f);
			d1.setDescription("Une banane d'Asie");
			d1.setFournisseur("China");
			d1.setImage(array1);
			d1.setNom("Banane");
			d1.setPrix(13.45f);
			d1.setQuantite(45);
			d1.setSku(-1234);

			new Produit(d1);

			fail("Sku négatif");
		} catch (ExceptionProduitEtablissement e) {

		}

	}

	@Test
	public void testGetSku() {
		assertTrue(p1.getSku() == 1234);
		assertTrue(p2.getSku() == 564);
	}

	@Test
	public void testGetNom() {
		assertTrue(p1.getNom().equals("Banane"));
		assertTrue(p2.getNom().equals("Chocolat"));
	}

	@Test
	public void testGetPrix() {
		assertTrue(p1.getPrix() == 13.45f);
		assertTrue(p2.getPrix() == 3.5f);
	}

	@Test
	public void testGetCoutant() {
		assertTrue(p1.getCoutant() == 12.34f);
		assertTrue(p2.getCoutant() == 2.3f);
	}

	@Test
	public void testGetFournisseur() {
		assertTrue(p1.getFournisseur().equals("China"));
		assertTrue(p2.getFournisseur().equals("Leclerc"));
	}

	@Test
	public void testGetDescription() {
		assertTrue(p1.getDescription().equals("Une banane d'Asie"));
		assertTrue(p2.getDescription().equals("Un chocolat"));
	}

	@Test
	public void testGetQuantite() {
		assertTrue(p1.getQuantite() == 45);
		assertTrue(p2.getQuantite() == 234);
	}

	@Test
	public void testGetImage() {
		assertTrue(p1.getImage()[0] == 0);
		assertTrue(p2.getImage()[0] == 1);

		assertTrue(p1.getImage()[1] == 1);
		assertTrue(p2.getImage()[1] == 1);
	}
}
