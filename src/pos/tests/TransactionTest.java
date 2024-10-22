package pos.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import commun.DataProduit;
import commun.Etablissement;
import commun.LigneFacture;
import commun.Produit;
import commun.Transaction;
import commun.exception.ExceptionProduitEtablissement;
import javafx.collections.ObservableList;

/**
 * Classe testant la classe Transaction
 * 
 * @author Bank-era Corp.
 *
 */
public class TransactionTest {

	Produit p1, p2, p3;
	Transaction tr1 = null;
	List<Produit> produitsAjouter = null;

	@Before
	public void setup() throws ExceptionProduitEtablissement {
		Etablissement e1 = new Etablissement();
		tr1 = new Transaction(e1);

		DataProduit d1 = new DataProduit();

		byte[] array1 = { 0, 1 };

		d1.setCoutant(12.34f);
		d1.setDescription("Une banane d'Asie");
		d1.setFournisseur("China");
		d1.setImage(array1);
		d1.setNom("Banane");
		d1.setPrix(13.45f);
		d1.setQuantite(45);
		d1.setSku(12);

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

		DataProduit d3 = new DataProduit();

		byte[] array3 = { 1, 1 };

		d3.setCoutant(2.3f);
		d3.setDescription("Un gateau");
		d3.setFournisseur("Vachon");
		d3.setImage(array3);
		d3.setNom("Gateau");
		d3.setPrix(3.5f);
		d3.setQuantite(234);
		d3.setSku(565);

		p1 = new Produit(d1);
		p2 = new Produit(d2);
		p3 = new Produit(d3);
	}

	@Test
	public void validerTest() {
		// S'assurer que les infos nécessaires sont initialisées lors de la création
		assert (tr1.getHeure() != null);

		// S'assurer que les variables sont bien initialisé
		assert (tr1.getMontantTaxes() == 0);
		assert (tr1.getMontantTotal() == 0);
		assert (tr1.getSousTotal() == 0);

		// S'assurer que la liste de nouveaux produits est nulle
		assert (tr1.getProduits().size() == 0);

		assert (tr1.getNumero() > 0);
	}

	@Test
	public void ajouterProduit() {
		tr1.addProduit(p1);
		tr1.addProduit(p1);
		tr1.addProduit(p2);
		tr1.addProduit(p3);

		ObservableList<LigneFacture> liste = tr1.getLignesFacture();

		for (LigneFacture ligne : liste) {
			Produit produit = ligne.getProduit();

			if (produit.equals(p1)) {
				assertTrue(ligne.getQuantite() == 2);
			} else {
				assertTrue(produit.equals(p2) || produit.equals(p3));
			}

		}
	}

	@Test
	public void ajouterProduits() {
		produitsAjouter = new ArrayList<Produit>();
		produitsAjouter.add(p1);
		produitsAjouter.add(p1);
		produitsAjouter.add(p2);

		tr1.addProduits(produitsAjouter);

		ObservableList<LigneFacture> liste = tr1.getLignesFacture();

		for (LigneFacture ligne : liste) {
			Produit produit = ligne.getProduit();

			if (produit.equals(p1)) {
				assertTrue(ligne.getQuantite() == 2);
			} else {
				assertTrue(produit.equals(p2));
			}

		}
	}

	@Test
	public void retirerProduits() {
		tr1.addProduit(p1);
		tr1.addProduit(p1);
		tr1.addProduit(p2);
		tr1.addProduit(p3);

		ObservableList<LigneFacture> liste = tr1.getLignesFacture();

		boolean flag1 = false;
		boolean flag2 = false;
		for (LigneFacture ligne : liste) {
			if(ligne.getProduit() == p1) {
				flag1 = true;
			}
		}
		assertTrue(flag1);
		
		tr1.removeProduit(p1);
		
		flag1 = false;
		
		for (LigneFacture ligne : liste) {
			if(ligne.getProduit() == p1) {
				flag1 = true;
			} else if(ligne.getProduit() == p2) {
				flag2 = true;
			}
		}
		assertFalse(flag1);
		assertTrue(flag2);
	}
}
