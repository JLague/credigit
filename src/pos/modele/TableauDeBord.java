package pos.modele;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exception.ExceptionCreationCompte;
import exception.ExceptionProduitEtablissement;
import pos.modele.Connexion;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class TableauDeBord {
	private Transaction transactionCourante;
	private Etablissement etablissement;
	private Vendeur vendeur;
	private List<Produit> inventaire;
	private Connexion dbConnection;

	public TableauDeBord() {
		this.transactionCourante = new Transaction();
		this.inventaire = new ArrayList<Produit>();

		dbConnection = new Connexion();

		populerInventaire();
		// TODO enlever lorsque Etablissement et Utilisateur sont finis
	}

	private void populerInventaire() {
		// TODO Utiliser l'inventaire de l'établissement
		byte[] stream = { 1 };

		try {
			Produit test1 = new Produit(11111, "test1", 05, 11, "SiFang", 13, "Une description", stream);
			Produit test2 = new Produit(22222, "test2", 10, 12, "SiFang", 12, "Une description", stream);
			Produit test3 = new Produit(33333, "test3", 27, 13, "SiFang", 10, "Une description", stream);
			Produit test4 = new Produit(43434, "test4", 38.4f, 14, "SiFang", 12, "Une description", stream);

			inventaire.add(test1);
			inventaire.add(test2);
			inventaire.add(test3);
			inventaire.add(test4);
		} catch (ExceptionProduitEtablissement e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	public void creerNouveauVendeur(DataVendeur data) throws ExceptionCreationCompte, ExceptionProduitEtablissement {
		Vendeur nouveauVendeur = new Vendeur(data);

		// La validation se fait ici parce qu'on a besoin de communiquer avec la base de
		// donnée
		if (dbConnection.validerNomUtilisateur(nouveauVendeur.getUsername())) {
			dbConnection.ajouterCompteVendeur(nouveauVendeur);
			// TODO ajouter le vendeur à l'établissement
			// etablissement.ajouterVendeur(nouveauVendeur);
		} else
			throw new ExceptionCreationCompte("Le nom d'utilisateur choisi est déjà utilisé.");
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
	 * Méthode permettant à un utilisateur de se connecter si les informations sont
	 * valides
	 * 
	 * @param username le nom d'utilisateur du vendeur
	 * @param password le mot de passe du vendeur
	 * @return true si la connection est réussi
	 */
	public boolean connecter(String username, String password) {
		password = encryption.SHAUtility.hashPassword(password);
		DataVendeur data = dbConnection.connecter(username, password);

		if (data != null) {
			try {
				vendeur = new Vendeur(data, data.getPassword());
			} catch (ExceptionCreationCompte e) {
				e.printStackTrace();
			}
			return true;
		}

		return false;
	}

	public boolean ajouterProduit(Produit produit) {
		return dbConnection.ajouterProduit(produit);
	}
}
