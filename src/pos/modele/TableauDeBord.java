package pos.modele;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class TableauDeBord {
	private Transaction transactionCourante;
	private Etablissement etablissement;
	private Utilisateur utilisateur;

	public TableauDeBord()
	{
		this.transactionCourante = new Transaction();
		//TODO enlever lorsque Etablissement et Utilisateur sont finis
	}
	
	public TableauDeBord(Etablissement etablissement, Utilisateur utilisateur) {
		this.etablissement = etablissement;
		this.utilisateur = utilisateur;
		this.transactionCourante = new Transaction();
	}

	/**
	 * @param produit le produit à ajouter
	 */
	public void addProduitATransaction(Produit produit) {
		this.transactionCourante.addProduit(produit);
	}

	/**
	 * @param produits les produits à ajouter
	 */
	public void addProduitsATransaction(List<Produit> produits) {
		this.transactionCourante.addProduits(produits);
	}

	/**
	 * 
	 * @return une liste contenant les StringProperty pour les prix, dans l'ordre
	 *         suivant : sous-total, taxes, total.
	 */
	public List<StringProperty> getProperties() {
		ArrayList<StringProperty> properties = new ArrayList<>();

		properties.add(transactionCourante.sousTotalProperty());
		properties.add(transactionCourante.taxesProperty());
		properties.add(transactionCourante.totalProperty());

		return properties;
	}

	/**
	 * @return une liste observable contenant les lignes de la facture courante
	 */
	public ObservableList<LigneFacture> getLignesFacture() {
		return transactionCourante.getLignesFacture();
	}
	
	/**
	 * Permet de créer une nouvelle transaction
	 */
	public void creerNouvelleTransaction()
	{
		this.transactionCourante = new Transaction();
	}
	
	/**
	 * 
	 * @param produit le produit à enlever
	 */
	public void enleverProduit(Produit produit)
	{
		this.transactionCourante.removeProduit(produit);
	}
}
