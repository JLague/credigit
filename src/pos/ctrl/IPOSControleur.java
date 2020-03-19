package pos.ctrl;

import java.util.List;

import pos.exception.ExceptionProduitEtablissement;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import pos.modele.DataProduit;
import pos.modele.LigneFacture;
import pos.modele.Produit;

public interface IPOSControleur {
	
	public final static String NOM_ETABLISSEMENT = "Credigit";

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
	public List<Produit> getInventaire();

	public void produitSelectionne(int ligne, int colonne);

	public void inputEntree(String input);

	public boolean creerProduit(DataProduit data) throws ExceptionProduitEtablissement;

	public boolean connexion(String username, String password, String nomEtablissement) throws ExceptionProduitEtablissement;

	public void paiementEmpreinte();

	public Scene getScene();
}
