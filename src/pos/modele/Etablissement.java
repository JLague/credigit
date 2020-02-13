package pos.modele;

import java.util.List;

public class Etablissement {

	/**
	 * Nom de l'établissement
	 */
	private String nom;
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
	private List<Utiliseur> utilisateurs;
	/**
	 * Balance de la compagnie
	 */
	private float balance;
	
	public Etablissement(String nom, String adresse) {
		// TODO Auto-generated constructor stub
	}
	
	
	
	/**
	 * @return le nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom le nom à modifier
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return le adresse
	 */
	public String getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse le adresse à modifier
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	/**
	 * @return le inventaire
	 */
	public List<Produit> getInventaire() {
		return inventaire;
	}
	/**
	 * @param inventaire le inventaire à modifier
	 */
	public void setInventaire(List<Produit> inventaire) {
		this.inventaire = inventaire;
	}
	/**
	 * @return le utilisateurs
	 */
	public List<Utiliseur> getUtilisateurs() {
		return utilisateurs;
	}
	/**
	 * @param utilisateurs le utilisateurs à modifier
	 */
	public void setUtilisateurs(List<Utiliseur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	/**
	 * @return le balance
	 */
	public float getBalance() {
		return balance;
	}
	/**
	 * @param balance le balance à modifier
	 */
	public void setBalance(float balance) {
		this.balance = balance;
	}

}