package pos.modele;

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
	//private List<Produits>;
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
	 * 
	 * @param heure
	 * @param pourcentageTaxes
	 */
	public Transaction(/*Etablissement etablissement, */String heure, /*arrayList<Produits> produits*/ float pourcentageTaxes) {
		// TODO Auto-generated constructor stub
	}

}
