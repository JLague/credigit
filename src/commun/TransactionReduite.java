package commun;

import java.io.Serializable;

/**
 * Classe représentant une transaction avec moins d'information pour stocker
 * dans la base de données des clients
 * 
 * @author Bank-era Corp.
 *
 */
public class TransactionReduite implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * État de la transaction
	 */
	private EtatTransaction etat;

	/**
	 * Heure à laquelle la transaction a été effectué
	 */
	private String heure;

	/**
	 * Valeur du sous-total de la facture
	 */
	private float sousTotal;

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
	 * Nom de l'établissement
	 */
	private String nomEtablissement;

	public TransactionReduite() {

	}

	public TransactionReduite(Transaction trans) {
		this.etat = trans.getEtat();
		setHeure(trans.getHeure());
		setSousTotal(trans.getSousTotal());
		setMontantTaxes(trans.getMontantTaxes());
		setMontantTotal(trans.getMontantTotal());
		setNomEtablissement(trans.getNomEtablissement());
		setNumero(trans.getNumero());
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
	 * @return le numéro de la facture
	 */
	public long getNumero() {
		return numero;
	}

	/**
	 * modifie le numéro de la facture
	 * 
	 * @param numero
	 */
	public void setNumero(long numero) {
		this.numero = numero;
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

	/**
	 * @return le sous-total
	 */
	public float getSousTotal() {
		return sousTotal;
	}

	/**
	 * @return le montant des taxes
	 */
	public float getMontantTaxes() {
		return montantTaxes;
	}

	/**
	 * @return le montant total
	 */
	public float getMontantTotal() {
		return montantTotal;
	}

	@Override
	public String toString() {
		return Long.toString(numero);
	}

	/**
	 * Set le nom de l'établissement
	 * 
	 * @param nomEtablissement le nom de l'établissement
	 */
	public void setNomEtablissement(String nomEtablissement) {
		this.nomEtablissement = nomEtablissement;
	}

	/**
	 * @return le nom de l'établissement
	 */
	public String getNomEtablissement() {
		return nomEtablissement;
	}

	public void setEtat(EtatTransaction pEtat) {
		this.etat = pEtat;
	}

	public EtatTransaction getEtat() {
		return etat;
	}
}
