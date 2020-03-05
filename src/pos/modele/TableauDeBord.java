package pos.modele;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class TableauDeBord {
	private Transaction transactionCourante;
	private Etablissement etablissement;
	private Utilisateur utilisateur;
	private List<Produit> inventaire;

	public TableauDeBord() {
		this.transactionCourante = new Transaction();
		this.inventaire = new ArrayList<Produit>();

		populerInventaire();
		// TODO enlever lorsque Etablissement et Utilisateur sont finis
	}

	private void populerInventaire() {
		// TODO Utiliser l'inventaire de l'établissement
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			stream.write(new byte[2]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Produit test1 = new Produit(1, "test1", 10, 10, "SiFang", 13, "Une description", stream);
		Produit test2 = new Produit(2, "test2", 10, 10, "SiFang", 12, "Une description", stream);
		Produit test3 = new Produit(3, "test3", 10, 10, "SiFang", 10, "Une description", stream);
		Produit test4 = new Produit(4, "test4", 10, 10, "SiFang", 12, "Une description", stream);

		inventaire.add(test1);
		inventaire.add(test2);
		inventaire.add(test3);
		inventaire.add(test4);

	}

	public TableauDeBord(Etablissement etablissement, Utilisateur utilisateur) {
		this();
		this.etablissement = etablissement;
		this.utilisateur = utilisateur;
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
	public void creerNouvelleTransaction() {
		this.transactionCourante = new Transaction();
	}

	/**
	 * 
	 * @param produit le produit à enlever
	 */
	public void enleverProduit(Produit produit) {
		this.transactionCourante.removeProduit(produit);
	}

	/**
	 * 
	 * @return l'inventaire du magasin
	 */
	public List<Produit> getListeProduits() {
		return this.inventaire;
	}

	/**
	 * @param nom le nom du produit à chercher
	 * @return le produit
	 */
	public Produit getProduitFromString(String nom) {
		for (Produit p : inventaire) {
			if (p.getNom().equals(nom)) {
				return p;
			}
		}

		return null;
	}
}
