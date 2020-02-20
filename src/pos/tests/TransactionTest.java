package pos.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pos.modele.Produit;
import pos.modele.Transaction;

public class TransactionTest {
	
	Transaction tr1 = null;

	@Before
	public void setup() {
		tr1 = new Transaction();
	}

	@Test
	public void validerTest() {
		// S'assurer que les infos nécessaires sont initialiser lors de la création
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
	public void ajouterProduits() {
		// Test avec des produits valides
		tr1.addProduit(new Produit(000001, "produitTest", 20.00f, 18.00f, "Disque", "Un produit"));
		tr1.addProduit(new Produit(000002, "produitTest2", 10.50f, 3.00f, "Table", "Un produit2"));
		tr1.addProduit(new Produit(000003, "produitTest3", 2000.00f, 34.70f, "Chaise", "Un produit3"));
		
		// Test avec des prodiits invalides
	}

	@Test
	public void retirerProduits() {
		fail("Not yet implemented");
	}

	@Test
	public void calculerTaxesTotaux() {
		fail("Not yet implemented");
	}

}
