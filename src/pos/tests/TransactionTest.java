package pos.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import javafx.collections.ObservableList;
import pos.modele.LigneFacture;
import pos.modele.Produit;
import pos.modele.Transaction;

public class TransactionTest {

	Produit p1, p2, p3;
	Transaction tr1 = null;
	List<Produit> produitsAjouter = null;

	@Before
	public void setup() {
		tr1 = new Transaction();
		
		p1 = new Produit(000001, "produitTest", 20.00f, 18.00f, "Disque", "Un produit");
		p2 = new Produit(000002, "produitTest2", 10.50f, 3.00f, "Table", "Un produit2");
		p3 = new Produit(000003, "produitTest3", 2000.00f, 34.70f, "Chaise", "Un produit3");
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
		tr1.addProduit(p1);
		tr1.addProduit(p1);
		tr1.addProduit(p2);
		tr1.addProduit(p3);
		
		ObservableList<LigneFacture> liste = tr1.getLignesFacture();
		
		for(LigneFacture ligne : liste)
		{
			Produit produit = ligne.getProduit();
			
			if(produit.equals(p1))
			{
				assertTrue(ligne.getQuantite() == 2);
			}
			else
			{
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
		
		for(LigneFacture ligne : liste)
		{
			Produit produit = ligne.getProduit();
			
			if(produit.equals(p1))
			{
				assertTrue(ligne.getQuantite() == 2);
			}
			else
			{
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
		
		for(LigneFacture ligne : liste)
		{
			
		}
	}

	@Test
	public void calculerTaxesTotaux() {

	}

}
