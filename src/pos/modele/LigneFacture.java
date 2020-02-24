package pos.modele;

import java.text.NumberFormat;
import java.util.Locale;

public class LigneFacture {
	private float prixUnitaire;
	private float prix;
	private String prixString;
	private int quantite;
	private String nom;
	private Produit produit;
	
	private NumberFormat cf;
	

	/**
	 * 
	 * @param produit le produit associé à la ligne
	 * @param quantite la quantité du produit
	 */
	public LigneFacture(Produit produit, int quantite) {
		cf = NumberFormat.getCurrencyInstance(new Locale("en", "CA"));
		
		this.prixUnitaire = produit.getPrix();
		this.nom = produit.getNom();
		this.quantite = quantite;
		this.produit = produit;
		calculerPrix();
	}

	/**
	 * @return le prix
	 */
	public float getPrix() {
		return prix;
	}

	/**
	 * @return le quantite
	 */
	public int getQuantite() {
		return quantite;
	}

	/**
	 * @param quantite le quantite à modifier
	 */
	public void setQuantite(int quantite) {
		this.quantite = quantite;
		calculerPrix();
	}

	/**
	 * @return le nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Calculer le prix de la ligne
	 */
	private void calculerPrix() {
		this.prix = this.prixUnitaire * this.quantite;
		prixString = cf.format(this.prix);
	}

	/**
	 * @return le produit
	 */
	public Produit getProduit() {
		return this.produit;
	}
	
	/**
	 * @return le prix en String
	 */
	public String getPrixString() {
	    return prixString;
	}
}
