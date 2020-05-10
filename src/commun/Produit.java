package commun;

import java.io.Serializable;

import commun.exception.ExceptionProduitEtablissement;

/**
 * Classe créant un produit
 * 
 * @author Bank-era Corp.
 *
 */
public class Produit implements Serializable, Cloneable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 5192964164564910569L;

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
	 * Le path de l'image dans la base de données
	 */
	private String imagePathDB;

	/**
	 * Constructeur utilisé par POJO
	 */
	public Produit() {
	}

	/**
	 * Crée un nouveau produit
	 * 
	 * @param data - L'objet transitoire contenant le produit
	 * @throws ExceptionProduitEtablissement
	 */
	public Produit(DataProduit data) throws ExceptionProduitEtablissement {
		setSku(data.getSku());
		setNom(data.getNom());
		setPrix(data.getPrix());
		setCoutant(data.getCoutant());
		setFournisseur(data.getFournisseur());
		setQuantite(data.getQuantite());
		setDescription(data.getDescription());
		setImage(data.getImage());
		setImagePathDB(null);
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
	public void setQuantite(int quantite) {

		this.quantite = quantite;

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
	public void setImage(byte[] image) {
		this.image = image;
	}

	/**
	 * Getter du path de l'image dans la base de données
	 * 
	 * @return le path de l'image
	 */
	public String getImagePathDB() {
		return imagePathDB;
	}

	/**
	 * Setter du path de l'image
	 * 
	 * @param imagePathDB - Le path à setter
	 */
	public void setImagePathDB(String imagePathDB) {
		this.imagePathDB = imagePathDB;
	}

	/**
	 * Permet d'effacer l'image du produit
	 */
	public void effacerImage() {
		this.image = null;
	}

	/**
	 * Permet de cloner le produit en profondeur
	 * 
	 * @return Le clone du produit
	 */
	@Override
	public Object clone() {
		Produit clone = null;
		try {
			// On récupère l'instance à renvoyer par l'appel de la
			// méthode super.clone()
			clone = (Produit) super.clone();

		} catch (CloneNotSupportedException cnse) {
			// Ne devrait jamais arriver car nous implémentons
			// l'interface Cloneable
			cnse.printStackTrace(System.err);
		}

		return clone;
	}
}
