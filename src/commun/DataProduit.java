package commun;

/**
 * Classe transitoire contenant le data d'un produit
 * 
 * @author Bank-era Corp.
 *
 */
public class DataProduit {

	/**
	 * Le SKU du produit
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
	 * La quantité de ce produit
	 */
	private int quantite;

	/**
	 * Le fournisseur du produit
	 */
	private String fournisseur;

	/**
	 * Une brève description du produit
	 */
	private String description;

	/**
	 * L'image du produit
	 */
	private byte[] image;

	/**
	 * Constructeur par défaut
	 */
	public DataProduit() {

	}

	/**
	 * Constructeur avec paramètres
	 * 
	 * @param sku         - Le code du produit
	 * @param nom         - Le nom du produit
	 * @param prix        - Le prix de vente du produit
	 * @param coutant     - Le prix coûtant du produit
	 * @param fournisseur - Le nom du fournisseur
	 * @param quantite    - La quantité en inventaire
	 * @param description - La description du produit
	 * @param image       - L'image du produit
	 */
	public DataProduit(long sku, String nom, float prix, float coutant, String fournisseur, int quantite,
			String description, byte[] image) {
		setSku(sku);
		setNom(nom);
		setPrix(prix);
		setCoutant(coutant);
		setFournisseur(fournisseur);
		setQuantite(quantite);
		setDescription(description);
		setImage(image);
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

	/**
	 * 
	 * @return la quantité
	 */
	public int getQuantite() {
		return quantite;
	}

	/**
	 * 
	 * @param quantite - La quantité
	 */
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	/**
	 * 
	 * @return l'image
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * 
	 * @param image - L'image
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}

}
