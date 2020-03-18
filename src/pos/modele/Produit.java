package pos.modele;

import pos.exception.ExceptionProduitEtablissement;

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
	private byte[] image;

	/**
	 * Constructeur utilisé par POJO
	 */
	public Produit() {
	}

	/**
	 * Crée un nouveau produit
	 * 
	 * @param sku         - Le stock keeping unit du produit
	 * @param nom         - Le nom du produit
	 * @param prix        - Le prix du produit
	 * @param coutant     - Le prix coutant du produit
	 * @param fournisseur - Le fournisseur du produit
	 * @param quantite    - La quantité du produit
	 * @param description - La description du produit
	 * @param image       - L'image du produit
	 * @throws ExceptionProduitEtablissement
	 */
	public Produit(long sku, String nom, float prix, float coutant, String fournisseur, int quantite,
			String description, byte[] image) throws ExceptionProduitEtablissement {
		setSku(sku);
		setNom(nom);
		setPrix(prix);
		setCoutant(coutant);
		setFournisseur(fournisseur);
		setQuantite(quantite);
		setDescription(description);
		setImage(image);
	}

	public Produit(DataProduit data) throws ExceptionProduitEtablissement {
		setSku(data.getSku());
		setNom(data.getNom());
		setPrix(data.getPrix());
		setCoutant(data.getCoutant());
		setFournisseur(data.getFournisseur());
		setQuantite(data.getQuantite());
		setDescription(data.getDescription());
		setImage(data.getImage());
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
	 * @throws ExceptionProduitEtablissement
	 */
	public void setSku(long sku) throws ExceptionProduitEtablissement {
		if (sku > 0) {
			this.sku = sku;
		} else {
			throw new ExceptionProduitEtablissement("Le SKU n'est pas valide.");
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
	 * @throws ExceptionProduitEtablissement
	 */
	public void setNom(String nom) throws ExceptionProduitEtablissement {
		if (nom != null && nom.length() != 0) {
			this.nom = nom;
		} else {
			throw new ExceptionProduitEtablissement("Le nom du produit n'est pas valide.");
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
	 * @throws ExceptionProduitEtablissement
	 */
	public void setPrix(float prix) throws ExceptionProduitEtablissement {
		if (prix >= 0) {
			this.prix = prix;
		} else {
			throw new ExceptionProduitEtablissement("Le prix n'est pas valide.");
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
	 * @throws ExceptionProduitEtablissement
	 */
	public void setCoutant(float coutant) throws ExceptionProduitEtablissement {

		if (coutant >= 0) {
			this.coutant = coutant;
		} else {
			throw new ExceptionProduitEtablissement("Le prix coutant n'est pas valide.");
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
	 * Modifie le fournisseur du produit
	 * 
	 * @param fournisseur - Le fournisseur du produit
	 * @throws ExceptionProduitEtablissement
	 */
	public void setFournisseur(String fournisseur) throws ExceptionProduitEtablissement {

		if (fournisseur != null && fournisseur.length() != 0) {
			this.fournisseur = fournisseur;
		} else {
			throw new ExceptionProduitEtablissement("Le fournisseur n'est pas valide.");
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
	 * 
	 * @param description - La description du produit à modifier
	 * @throws ExceptionProduitEtablissement
	 */
	public void setDescription(String description) throws ExceptionProduitEtablissement {

		if (description != null && description.length() != 0) {
			this.description = description;
		} else {
			throw new ExceptionProduitEtablissement("La description n'est pas valide.");
		}

	}

	/**
	 * Retourne la quantite du produit
	 * 
	 * @return La quantite du produit
	 */
	public int getQuantite() {
		return quantite;
	}

	/**
	 * Modifie la quantite du produit
	 * 
	 * @param quantite - La quantite du produit à modifier
	 * @throws ExceptionProduitEtablissement
	 */
	public void setQuantite(int quantite) throws ExceptionProduitEtablissement {
		if (quantite >= 0) {
			this.quantite = quantite;
		} else {
			throw new ExceptionProduitEtablissement("La quantite n'est pas valide.");
		}
	}

	/**
	 * Retourne l'objet contenant l'image
	 * 
	 * @return L'objet contenant l'image
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * Modifie l'objet contenant l'image
	 * 
	 * @param image - L'objet contenant l'image à modifier
	 * @throws ExceptionProduitEtablissement
	 */
	public void setImage(byte[] image) throws ExceptionProduitEtablissement {
		if (image != null && image.length != 0) {
			this.image = image;
		} else {
			throw new ExceptionProduitEtablissement("L'image n'est pas valide.");
		}
	}

	@Override
	public String toString() {
		return this.nom;
	}

}
