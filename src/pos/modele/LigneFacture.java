package pos.modele;

public class LigneFacture {
	private float prixUnitaire;
	private float prix;
	private int quantite;
	private String nom;
	private Produit produit;

	public LigneFacture(Produit produit, int quantite) {
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

}
