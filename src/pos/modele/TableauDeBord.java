package pos.modele;

import java.util.ArrayList;
import java.util.List;

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
	private Transaction transactionCourante;
	private Etablissement etablissement;
	private Vendeur vendeur = null;
	private List<Produit> inventaire;

	public TableauDeBord() {
		this.transactionCourante = new Transaction();
		this.inventaire = new ArrayList<Produit>();
	}

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

	public ArrayList<Produit> search(String text) {
		ArrayList<Produit> listProd = new ArrayList<Produit>();
		for (Produit produit : inventaire) {

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
	
	/*
	 * @param vendeur le vendeur aillant une session ouverte
	 */
	public void setVendeur(Vendeur vendeur) {
		this.vendeur = vendeur;
	}
	
	/**
	 * @return l'inventaire du magasin
	 */
	public List<Produit> getInventaire() {
		return this.inventaire;
	}
	
	/**
	 * @param produits les produits de l'inventaire
	 */
	public void setInventaire(List<Produit> produits) {
		this.inventaire = produits;
	}
}
