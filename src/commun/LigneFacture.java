package commun;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Classe représentant une ligne de la facture dans le TableView du POS
 * 
 * @author Bank-era Corp.
 * 
 */
public class LigneFacture implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Le prix unitaire du produit contenu dans la ligne de la facture
	 */
	private float prixUnitaire;

	/**
	 * Le prix total de la ligne
	 */
	private float prix;

	/**
	 * La quantité du produit dans la ligne
	 */
	private int quantite;

	/**
	 * Le nom du produit
	 */
	private String nom;

	/**
	 * Le produit
	 */
	private Produit produit;

	/**
	 * Formatteur pour l'argent
	 */
	private NumberFormat cf;

	/**
	 * 
	 * @param produit  le produit associé à la ligne
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
		return cf.format(this.prix);
	}
}