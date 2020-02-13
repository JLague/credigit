package pos.modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe métier s'occupe des transactions
 */
public class Transaction {
	/**
	 * Heure à laquelle la transaction a été effectué
	 */
	private String heure;
	/**
	 * Liste des produits sélectionnés par l'utilisateur
	 */
	private List<Produit> produits;
	/**
	 * Valeur du sous-total de la facture
	 */
	private float sousTotal;
	/**
	 * Pourcentage de taxe a appliqué
	 */
	private float pourcentageTaxes = 0.15f;
	/**
	 * Montant de taxe que nous devons rajouter à la facture
	 */
	private float montantTaxes;
	/**
	 * Montant total de la facture qui sera facturé au client
	 */
	private float montantTotal;
	/**
	 * Numéro de la transaction
	 */
	private long numero;

	/**
	 * Constructeur utilisé pour les nouvelles transactions
	 */
	public Transaction() {
	}

	/**
	 * utilisé lorsqu'on veut reprendre une transaction
	 * 
	 * @param heure            - L'heure à laquelle la transaction est effecuté
	 * @param pourcentageTaxes - pourcentage de taxe à appliquer selon la région
	 * @param etablissement    - Établissement où a lieu la transaction
	 * @param produits         - liste des produits qui seront chargés au client
	 *                         (panier)
	 */
	public Transaction(Etablissement etablissement, String heure, float pourcentageTaxes, ArrayList<Produit> produits) {
	}

	/**
	 * @return l'objet qui représente l'établissement
	 */
	public Etablissement getEtablissement() {
	}

	/**
	 * Change l'établissement de la présente facture
	 */
	public void setEtablissement() {
	}

	/**
	 * @return l'heure de la transaction
	 */
	public String getHeure() {
		return heure;
	}

	/**
	 * @param l'heure de la transaction
	 */
	public void setHeure(String heure) {
		this.heure = heure;
	}

	/**
	 * @return la liste de produit
	 */
	public List<Produit> getProduits() {
		return produits;
	}

	/**
	 * set la liste de produits (le panier)
	 * @param produits
	 */
	public void setProduits(List<Produit> produits) {
	}

	/**
	 * @return le numéro de la facture
	 */
	public long getNumero() {
		return numero;
	}

	/**
	 * modifie le numéro de la facture
	 * @param numero
	 */
	public void setNumero(long numero) {
		this.numero = numero;
	}

	/**
	 * Ajouter un produit au pos
	 * @param produit à ajouter
	 */
	public void addproduit(Produit produit) {
	}

	/**
	 * Ajouter des produits au panier de l'utilisatuer
	 * @param produits à ajouter
	 */
	public void addproduits(List<Produit> produits) {
	}

	/**
	 * @return le pourcentage de taxe
	 */
	public float getPourcentageTaxes() {
		return pourcentageTaxes;
	}

	/**
	 * set le pourcentage de taxe
	 * @param pourcentageTaxes - le nouveau pourcentage de taxe
	 */
	public void setPourcentageTaxes(float pourcentageTaxes) {
		this.pourcentageTaxes = pourcentageTaxes;
	}

	/**
	 * @return 
	 */
	public float getSousTotal() {
		return sousTotal;
	}

	/**
	 * @param sousTotal le sousTotal à modifier
	 */
	public void setSousTotal(float sousTotal) {
		this.sousTotal = sousTotal;
	}

	/**
	 * @param montantTaxes le montantTaxes à modifier
	 */
	public void setMontantTaxes(float montantTaxes) {
		this.montantTaxes = montantTaxes;
	}

	/**
	 * @param montantTotal le montantTotal à modifier
	 */
	public void setMontantTotal(float montantTotal) {
		this.montantTotal = montantTotal;
	}

	public float getMontantTaxes() {
		return montantTaxes;
	}

	public float getMontantTotal() {
		return montantTotal;
	}
	
	public String toString() {
		return "numero";
	}

}
