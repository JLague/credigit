package pos.ctrl;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;

import commun.*;
import commun.exception.ExceptionCreationCompte;
import commun.exception.ExceptionProduitEtablissement;

/**
 * Interface définissant les comportements obligatoires de POSControleur
 * 
 * @author Bank-era Corp.
 *
 */
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
	 * @return les lignes de la facture courante
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

	/**
	 * @return le nom du vendeur ou null si personne n'est connecté
	 */
	public String getNomVendeur();

	/**
	 * Retourne le numéro d'établissement selon le nom de l'établissement
	 * 
	 * @param nom - Le nom de l'établissement
	 * @return le numéro de l'établissement
	 * @throws ExceptionProduitEtablissement
	 */
	public long getNumeroEtablissement(String nom) throws ExceptionProduitEtablissement;

	/**
	 * Méthode permettant de mettre à jour l'établissement
	 */
	public void updateEtablissement();

	/**
	 * Méthode appelant le modèle afin de créer un nouveau produit dans
	 * l'établissement
	 * 
	 * @param data - Le data du produit
	 * @return vrai si le produit est créé avec succès
	 * @throws ExceptionProduitEtablissement
	 */
	public boolean creerProduit(DataProduit data) throws ExceptionProduitEtablissement;

	/**
	 * Méthode appelant le modèle afin de modifier le produit dans la base de
	 * données
	 * 
	 * @param ancien  - L'ancien produit
	 * @param nouveau - Le produit le remplaçant
	 * @throws ExceptionProduitEtablissement
	 */
	public void modifierProduit(Produit ancien, Produit nouveau) throws ExceptionProduitEtablissement;

	/**
	 * Méthode permettant de supprimer un produit dans la base de données
	 * 
	 * @param produit - Le produit à supprimer
	 */
	public void supprimerProduit(Produit produit);

	/**
	 * Méthode appelant le modèle afin de créer un nouveau vendeur
	 * 
	 * @param data - Le data du vendeur
	 * @throws ExceptionCreationCompte
	 * @throws ExceptionProduitEtablissement
	 */
	public void creerVendeur(DataVendeur data) throws ExceptionCreationCompte, ExceptionProduitEtablissement;

	/**
	 * Load la vue principale du POS à l'aide de l'application
	 */
	public void chargerScene(Scene scene, String title, boolean fullscreen);

	/**
	 * Méthode permettant la connexion au POS
	 * 
	 * @param username         - Le nom d'utilisateur du vendeur
	 * @param password         - Le mot de passe du vendeur
	 * @param nomEtablissement - Le nom de l'établissement
	 * @return vrai si la connexion est possible
	 * @throws ExceptionProduitEtablissement
	 */
	public boolean connexion(String username, String password, String nomEtablissement)
			throws ExceptionProduitEtablissement;

	/**
	 * Méthode appelant le modèle afin d'effectuer le paiement
	 */
	public void paiementEmpreinte();

	/**
	 * Getter de la scène
	 * 
	 * @return la scène
	 */
	public Scene getScene();

	/**
	 * Méthode permettant de rechercher des produits avec certaines caractéristiques
	 * 
	 * @param text - Le texte contenant les caractéristiques
	 * @return - La liste des produits comportant les caractéristiques
	 */
	public ArrayList<Produit> search(String text);

	/**
	 * Méthode permettant d'envoyer le data de du POS au terminal
	 */
	public void transferTerminal();
}
