package pos.modele;


import java.io.ByteArrayOutputStream;

/**
 * Classe créant un produit
 * 
 * @author Bank-era Corp.
 *
 */
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

	/**
	 * La quantité de ce produit
	 */
	private int quantite;
	
	/**
	 * L'image du produit
	 */
	private ByteArrayOutputStream image;

	/**
	 * Crée un nouveau produit
	 * 
	 * @param sku - Le stock keeping unit du produit
	 * @param nom - Le nom du produit
	 * @param prix - Le prix du produit
	 * @param coutant - Le prix coutant du produit
	 * @param fournisseur - Le fournisseur du produit
	 * @param quantite - La quantité du produit
	 * @param description - La description du produit
	 * @param image - L'image du produit
	 */
	public Produit(long sku, String nom, float prix, float coutant, String fournisseur, int quantite, String description, ByteArrayOutputStream image) {
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
	 * Retourne le sku du produit
	 * 
	 * @return Le sku du produit
	 */
	public long getSku() {
		return sku;
	}

	/**
	 * Modifie le sku du produit
	 * 
	 * @param sku - Le sku à modifier
	 */
	protected void setSku(long sku) {
		if(sku > 0)
		{
			this.sku = sku;
		}
		
	}

	/**
	 * Retourne le nom du produit
	 * 
	 * @return Le nom du produit
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Modifie le nom du produit
	 * 
	 * @param nom - Le nom à modifier
	 */
	protected void setNom(String nom) {
		if(nom != null && nom.length() != 0)
		{
			this.nom = nom;
		}
		
	}

	/**
	 * Retourne le prix du produit
	 * 
	 * @return Le prix du produit
	 */
	public float getPrix() {
		return prix;
	}

	/**
	 * Modifie le prix du produit
	 * 
	 * @param prix - Le prix du produit à modifier
	 */
	protected void setPrix(float prix) {
		if(prix >= 0)
		{
			this.prix = prix;
		}
		
	}

	/**
	 * Retourne le prix coutant du produit
	 * 
	 * @return Le prix coutant du produit
	 */
	public float getCoutant() {
		return coutant;
	}

	/**
	 * Modifie le prix coutant du produit
	 * 
	 * @param coutant - Le prix coutant du produit à modifier
	 */
	protected void setCoutant(float coutant) {
		
		if(coutant >= 0)
		{
			this.coutant = coutant;
		}
	}

	/**
	 * Retourne le fournisseur du produit
	 * 
	 * @return Le fournisseur du produit
	 */
	public String getFournisseur() {
		return fournisseur;
	}

	/**
	 * Modifie  le fournisseur du produit
	 * 
	 * @param fournisseur - Le fournisseur du produit
	 */
	protected void setFournisseur(String fournisseur) {
		
		if(fournisseur != null && fournisseur.length() != 0)
		{
			this.fournisseur = fournisseur;
		}
		
	}

	/**
	 * Retourne la description du produit
	 * 
	 * @return Le description du produit
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Modifie la description du produit
	 * @param description - La description du produit à modifier
	 */
	protected void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Retourne la quantite du produit
	 * @return La quantite du produit
	 */
	public int getQuantite() {
		return quantite;
	}

	/**
	 * Modifie la quantite du produit
	 * @param quantite - La quantite du produit à modifier
	 */
	protected void setQuantite(int quantite) {
		if(quantite >= 0)
		{
			this.quantite = quantite;
		}	
	}
	
	/**
	 * Retourne l'objet contenant l'image
	 * 
	 * @return L'objet contenant l'image
	 */
	public ByteArrayOutputStream getImage() {
		return image;
	}

	/**
	 * Modifie l'objet contenant l'image
	 * @param image - L'objet contenant l'image à modifier
	 */
	protected void setImage(ByteArrayOutputStream image) {
		if(image != null && image.size() != 0)
		{
			this.image = image;
		}
	}

	@Override
	public String toString() {
		return this.nom;
	}

}
