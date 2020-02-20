package pos.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pos.modele.Produit;
import pos.modele.Transaction;

public class TransactionTest {

	Transaction tr1 = null;
	List<Produit> produitsAjouter = null;

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
	public void ajouterProduit() {
		tr1.addProduit(new Produit(000001, "produitTest", 20.00f, 18.00f, "Disque", "Un produit"));
		tr1.addProduit(new Produit(000002, "produitTest2", 10.50f, 3.00f, "Table", "Un produit2"));
		tr1.addProduit(new Produit(000003, "produitTest3", 2000.00f, 34.70f, "Chaise", "Un produit3"));
	}

	@Test
	public void ajouterProduits() {
		produitsAjouter = new ArrayList<Produit>();
		produitsAjouter.add(new Produit(000004, "produitTest4", 10.00f, 6.50f, "Joie de vivre", "0"));
		produitsAjouter.add(new Produit(000005, "produitTest5", 17.00f, 12.40f, "Pluie", "chamanique"));
		
		tr1.addProduits(produitsAjouter);
	}

	@Test
	public void retirerProduits() {
	}

	@Test
	public void calculerTaxesTotaux() {

	}

}
