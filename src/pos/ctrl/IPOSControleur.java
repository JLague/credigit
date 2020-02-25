package pos.ctrl;

import java.util.List;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import pos.modele.DataVue;
import pos.modele.LigneFacture;
import pos.modele.Produit;

public interface IPOSControleur {

	/**
	 * Permet d'enlever un produit
	 */
	public void enleverProduit(Produit produit);
	
	/**
	 * @return les propriétés des prix pour la vue
	 */
	public List<StringProperty> getPrixProperties();
	
	/**
	 * @return les lignes de la facure courante
	 */
	public ObservableList<LigneFacture> getLignesFacture();
	
	/**
	 * @param produit le produit à ajouter à la transaction
	 */
	public void ajouterProduitATransaction(Produit produit);
	
	/**
	 * @param produit les produits à ajouter à la transaction
	 */
	public void ajouterProduitsATransaction(List<Produit> produits);
	
	/**
	 * Permet de créer une nouvelle transaction;
	 */
	public void creerNouvelleTransaction();
	
	/**
	 * @return la liste de tous les produits
	 */
	public List<Produit> getListeProduits();
	
	/**
	 * @return le produit correspondant au nom
	 */
	public Produit getProduitFromString(String nom);

	public void produitSelectionne(int ligne, int colonne);

	public void inputEntree(String input);

	public void creerProduit(DataVue data);

	public boolean connexion(String username, String password);

	public void paiementEmpreinte();

	public Scene getScene();
}
