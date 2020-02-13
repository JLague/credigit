package pos.modele;

public class Produit {
	
	/**
	 * Le sku du produit
	 */
	private long sku;
	
	/**
	 * Le nom du produit
	 */
	private String nom;
	
	/**
	 * Le prix du produit
	 */
	private float prix;
	
	/**
	 * Le coûtant du produit
	 */
	private float coutant;
	
	/**
	 * Le fournisseur du produit
	 */
	private String fournisseur;
	
	/**
	 * Une brève description du produit
	 */
	private String description;

	public Produit(long sku, String nom, float prix, float coutant, String fournisseur, String description)
	{
		this.sku = sku;
		this.nom = nom;
		this.prix = prix;
		this.coutant = coutant;
		this.fournisseur = fournisseur;
		this.description = description;
	}
	
	/**
	 * @return the sku
	 */
	public long getSku() {
		return sku;
	}

	/**
	 * @param sku the sku to set
	 */
	public void setSku(long sku) {
		this.sku = sku;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prix
	 */
	public float getPrix() {
		return prix;
	}

	/**
	 * @param prix the prix to set
	 */
	public void setPrix(float prix) {
		this.prix = prix;
	}

	/**
	 * @return the coutant
	 */
	public float getCoutant() {
		return coutant;
	}

	/**
	 * @param coutant the coutant to set
	 */
	public void setCoutant(float coutant) {
		this.coutant = coutant;
	}

	/**
	 * @return the fournisseur
	 */
	public String getFournisseur() {
		return fournisseur;
	}

	/**
	 * @param fournisseur the fournisseur to set
	 */
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString()
	{
		return this.nom;
	}
	
}
