package commun;

import java.util.ArrayList;
import java.util.List;

import commun.exception.ExceptionProduitEtablissement;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 * 
 * Classe métier permettant de contrôler les différentes fonctions de
 * l'interface du POS
 * 
 * @author Bank-era Corp.
 *
 */
public class TableauDeBord {
	/**
	 * La transaction affichée dans le tableau de bord
	 */
	private Transaction transactionCourante;

	/**
	 * L'établissement du POS
	 */
	private Etablissement etablissement;

	/**
	 * Le vendeur utilisant le POS
	 */
	private Vendeur vendeur = null;

	/**
	 * Constructeur par défaut
	 */
	public TableauDeBord() {
		this.etablissement = new Etablissement();
		try {
			this.etablissement.setNom("Etablissement non enregistré");
		} catch (ExceptionProduitEtablissement e) {
			e.printStackTrace();
		}
		this.transactionCourante = new Transaction(etablissement);
	}

	/**
	 * Constructeur utilisé lors du démarrage du POS
	 * 
	 * @param etablissement - L'établissement utilisant le POS
	 * @param vendeur       - Le vendeur connecté
	 */
	public TableauDeBord(Etablissement etablissement, Vendeur vendeur) {
		this();
		this.etablissement = etablissement;
		this.vendeur = vendeur;
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
		this.transactionCourante = new Transaction(etablissement);
	}

	/**
	 * 
	 * @param produit le produit à enlever
	 */
	public void enleverProduit(Produit produit) {
		this.transactionCourante.removeProduit(produit);
	}

	/**
	 * Méthode pour rechercher un produit dans l'inventaire de l'établissement
	 * 
	 * @param text - Texte représentant soit le sku (Débute par «.»), soit le prix d'un produit
	 *  
	 * @return La liste de produits correspondante à la recherche
	 */
	public ArrayList<Produit> search(String text) {
		ArrayList<Produit> listProd = new ArrayList<Produit>();
		for (Produit produit : etablissement.getInventaire()) {

			if (text.length() != 0 && text.charAt(0) == '.') {
				// Il s'agit d'un sku
				if (String.valueOf(produit.getSku()).contains(text.subSequence(1, text.length()))) {
					listProd.add(produit);
				}
			} else {
				if (String.valueOf(produit.getPrix()).contains(text.subSequence(0, text.length()))) {
					listProd.add(produit);
				}
			}
		}
		return listProd;
	}

	/**
	 * @return le nom du vendeur ou null si personne n'est connecté
	 */
	public String getNomVendeur() {
		if (vendeur != null)
			return vendeur.getPrenom();
		return null;
	}

	/**
	 * @param vendeur le vendeur aillant une session ouverte
	 */
	public void setVendeur(Vendeur vendeur) {
		this.vendeur = vendeur;
	}

	/**
	 * @return l'inventaire du magasin
	 */
	public List<Produit> getInventaire() {
		return this.etablissement.getInventaire();
	}

	/**
	 * @param produits les produits de l'inventaire
	 */
	public void setInventaire(List<Produit> produits) {
		this.etablissement.setInventaire(produits);
	}

	/**
	 * @param etablissement l'établissement
	 */
	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}

	/**
	 * @return l'établissement
	 */
	public Etablissement getEtablissement() {
		return this.etablissement;
	}

	/**
	 * @return la transaction courante
	 */
	public Transaction getTransaction() {
		return transactionCourante;
	}

	/**
	 * @param t la transaction
	 */
	public void setTransaction(Transaction t) {
		transactionCourante = t;
	}
}
