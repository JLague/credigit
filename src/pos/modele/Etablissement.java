package pos.modele;

import java.util.ArrayList;
import java.util.List;

import pos.exception.ExceptionProduitEtablissement;

/**
 * Classe créant un établissement
 * @author Bank-era Corp.
 *
 */
public class Etablissement {

	/**
	 * Nom de l'établissement
	 */
	private String nom;
	/**
	 * Courriel de l'entreprise
	 */
	private String courriel;
	/**
	 * Adresse de l'entreprise
	 */
	private String adresse;
	/**
	 * Liste de tout les produits de l'entreprise
	 */
	private List<Produit> inventaire;
	/**
	 * Listes des utilisateurs
	 */
	private List<Vendeur> vendeurs;
	/**
	 * Balance de la compagnie
	 */
	private float balance = 0;
	/**
	 * Liste contenant les transactions d'un établissement
	 */
	private List<Transaction> transactions;
	
	/**
	 * Numéro d'établissement
	 */
	private long numero;

	/**
	 * Crée un établissement
	 * @param nom - Nom de l'établissement
	 * @param adresse - Adresse de l'établissement
	 * @param balance - Balance de l'établissement
	 * @param courriel - Courriel de l'établissment
	 * @throws ExceptionProduitEtablissement 
	 */
	public Etablissement(String nom, String adresse, float balance, String courriel) throws ExceptionProduitEtablissement {
		
		setNom(nom);
		setAdresse(adresse);
		setBalance(balance);
		setCourriel(courriel);
		
		inventaire = new ArrayList<Produit>();
		transactions = new ArrayList<Transaction>();
		vendeurs = new ArrayList<Vendeur>();
		
		genererNumero();
	}
	
	/**
	 * Génère un numéro de 5 chiffres aléatoire pour l'établissement
	 */
	private void genererNumero()
	{
		numero = (long) (Math.random() * (89999)) + 10000;
	}
	
	/**
	 * Retourne le numéro d'établissement
	 * 
	 * @return Le numéro d'établissment
	 */
	public long getNumero()
	{
		return numero;
	}

	/**
	 * Retourne le nom de l'établissement
	 * @return Le nom de l'établissement
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Modifie le nom de l'établissement
	 * @param nom - Le nom de l'établissement à modifier
	 * @throws ExceptionProduitEtablissement 
	 */
	protected void setNom(String nom) throws ExceptionProduitEtablissement {
		
		if(nom != null && nom.length() != 0)
		{
			this.nom = nom;
		}
		else
		{
			throw new ExceptionProduitEtablissement("Le nom de l'établissement n'est pas valide.");
		}
		
	}

	/**
	 * Retourne l'adresse de l'établissement
	 * @return L'adresse de l'établissement
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * Modifie l'adresse de l'établissement
	 * @param adresse - L'adresse de l'établissement à modifier
	 * @throws ExceptionProduitEtablissement 
	 */
	protected void setAdresse(String adresse) throws ExceptionProduitEtablissement {
		
		if(adresse != null && adresse.length() != 0)
		{
			this.adresse = adresse;
		}
		else
		{
			throw new ExceptionProduitEtablissement("L'adresse de l'établissement n'est pas valide.");
		}
		
	}

	/**
	 * Retourne l'inventaire de l'établissement
	 * @return L'inventaire de l'établissement
	 */
	public List<Produit> getInventaire() {
		return inventaire;
	}

	/**
	 * Ajoute un produit à l'inventaire de l'établissement
	 * @param produit - Le produit à ajouter
	 * @throws ExceptionProduitEtablissement 
	 */
	protected void ajouterProduitInventaire(Produit produit) throws ExceptionProduitEtablissement {
		
		if(produit != null)
		{
			inventaire.add(produit);
		}	
		else
		{
			throw new ExceptionProduitEtablissement("Le produit à ajouter n'est pas valide.");
		}
	}

	/**
	 * Retourne les utilisateurs de l'établissement
	 * @return Les utilisateurs de de l'établissement
	 */
	public List<Vendeur> getUtilisateurs() {
		return vendeurs;
	}

	/**
	 * Ajoute un vendeur à l'établissement
	 * @param vendeur - Le vendeur à ajouter
	 * @throws ExceptionProduitEtablissement 
	 */
	protected void ajouterVendeur(Vendeur vendeur) throws ExceptionProduitEtablissement {
		
		if(vendeur != null)
		{
			vendeurs.add(vendeur);
		}
		else
		{
			throw new ExceptionProduitEtablissement("Le vendeur à ajouter n'est pas valide.");
		}
		
	}

	/**
	 * Retourne la balance de l'établissement
	 * @return La balance de l'établissement
	 */
	public float getBalance() {
		return balance;
	}

	/**
	 * Modifie la balance de l'établissement
	 * @param balance - La balance de l'établissement à modifier
	 */
	protected void setBalance(float balance) {
		this.balance = balance;
	}
	
	/**
	 * Ajoute le montant de l'ajout à la balance
	 * 
	 * @param ajout - Le montant à ajouter
	 */
	protected void ajouterBalance(float ajout)
	{
		balance += ajout;
	}
	
	/**
	 * Retourne les transcations de l'établissement
	 * @return Les transactions de l'établissement
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * Ajoute une transactions à la liste de transactions de l'établissement
	 * @param transaction - La transaction à ajouter
	 * @throws ExceptionProduitEtablissement 
	 */
	public void ajouterTransaction(Transaction transaction) throws ExceptionProduitEtablissement {
		
		if(transaction != null)
		{
			transactions.add(transaction);
		}
		else
		{
			throw new ExceptionProduitEtablissement("La transaction à ajouter n'est pas valide.");
		}
		
	}

	/**
	 * Retourne le courriel de l'établissement
	 * @return Le courriel de l'établissement
	 */
	public String getCourriel() {
		return courriel;
	}

	/**
	 * Modifie le courriel de l'établissement
	 * @param courriel - Le courriel à modifier
	 * @throws ExceptionProduitEtablissement 
	 */
	public void setCourriel(String courriel) throws ExceptionProduitEtablissement {
		
		if(adresse != null && adresse.length() != 0)
		{
			this.courriel = courriel;
		}
		else
		{
			throw new ExceptionProduitEtablissement("Le courriel de l'établissement n'est pas valide.");
		}
		
	}

}