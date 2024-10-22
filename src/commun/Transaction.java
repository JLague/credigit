package commun;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Cette classe métier s'occupe des transactions
 * 
 * @author Bank-era Corp.
 */
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Constante soustraite au temps présent en millisescondes afin d'éviter que le
	 * numéro de facture soit trop long
	 */
	private static final long CONST = 1585281727947L;

	/**
	 * Pourcentage de taxe à appliquer
	 */
	private transient static float pourcentageTaxes = 0.15f;

	/**
	 * La propriété correspondant au sous-total
	 */
	private transient StringProperty sousTotalProperty;

	/**
	 * La propriété correspondant au taxes
	 */
	private transient StringProperty taxesProperty;

	/**
	 * La propriété correspondant au total
	 */
	private transient StringProperty totalProperty;

	/**
	 * État de la transaction
	 */
	private EtatTransaction etat;

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
	 * Établissement qui délivre la facture
	 */
	private Etablissement etablissement;

	/**
	 * Formatteur pour l'argent
	 */
	private transient NumberFormat cf;

	/**
	 * Les lignes de la facture. Chaque ligne correspond à un produit différent et
	 * contient la quantité et le prix.
	 */
	private transient ObservableList<LigneFacture> lignesFacture;

	/**
	 * Nom de l'établissement
	 */
	private String nomEtablissement;

	/**
	 * Array contenant toutes les lignes de la facture
	 */
	public ArrayList<LigneFacture> ligneFactureArray;

	/**
	 * Ne pas effacer, constructeur utilisé par POJO
	 */
	public Transaction() {
	}

	/**
	 * Constructeur utilisé pour les nouvelles transactions
	 * 
	 * @param etablissement - L'établissement de la transaction
	 */
	public Transaction(Etablissement etablissement) {
		this(getHeureCourante(), pourcentageTaxes, new ArrayList<Produit>(),
				((long) System.currentTimeMillis() - CONST), etablissement);
	}

	/**
	 * Constructeur utilisé lorsqu'on veut reprendre une transaction
	 * 
	 * @param heure            - L'heure à laquelle la transaction est effecuté
	 * @param pourcentageTaxes - pourcentage de taxe à appliquer selon la région
	 * @param produits         - liste des produits qui seront chargés au client
	 *                         (panier)
	 * @param numero           - le numéro de la transaction
	 */

	public Transaction(String heure, float pourcentageTaxes, ArrayList<Produit> produits, long numero,
			Etablissement etablissement) {
		cf = NumberFormat.getCurrencyInstance(new Locale("en", "CA"));

		this.heure = heure;
		Transaction.pourcentageTaxes = pourcentageTaxes;
		this.produits = produits;
		this.numero = numero;

		this.sousTotalProperty = new SimpleStringProperty();
		this.taxesProperty = new SimpleStringProperty();
		this.totalProperty = new SimpleStringProperty();

		lignesFacture = FXCollections.observableArrayList();

		this.etablissement = etablissement;

		this.etat = EtatTransaction.SCAN;

		nomEtablissement = this.etablissement.getNom();

		addProduits(produits);

	}

	/**
	 * Permet d'aller chercher l'heure courante en String
	 * 
	 * @return l'heure courante
	 */
	private static String getHeureCourante() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return ("" + dtf.format(now));
	}

	/**
	 * @return l'objet qui représente l'établissement
	 */
	public Etablissement getEtablissement() {
		return etablissement;
	}

	/**
	 * Change l'établissement de la présente facture
	 */
	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
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
	 * 
	 * @param produits
	 */
	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	/**
	 * Getter de l'array de lignes de la facture
	 * 
	 * @return l'array de lignes de la facture
	 */
	public ArrayList<LigneFacture> getLigneFactureArray() {
		return ligneFactureArray;
	}

	/**
	 * Setter de l'array de lignes de la facture
	 * 
	 * @param ligneFactureArray - L'array à set
	 */
	public void setLigneFactureArray(ArrayList<LigneFacture> ligneFactureArray) {
		this.ligneFactureArray = ligneFactureArray;
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
	 * Ajouter un produit au panier de l'utilisateur
	 * 
	 * @param produit à ajouter
	 */
	public void addProduit(Produit produit) {
		LigneFacture temp = null;

		for (LigneFacture ligne : lignesFacture) {
			if (ligne.getProduit().equals(produit)) {
				temp = ligne;
			}
		}

		if (temp != null) {
			temp.setQuantite(temp.getQuantite() + 1);
		} else {
			lignesFacture.add(new LigneFacture(produit, 1));
		}

		calculerPrix();
	}

	/**
	 * Ajouter des produits au panier de l'utilisateur
	 * 
	 * @param produits à ajouter
	 */
	public void addProduits(List<Produit> produits) {
		for (Produit produit : produits) {
			addProduit(produit);
		}
	}

	/**
	 * Retire un produit
	 */
	public void removeProduit(Produit produit) {
		LigneFacture tmp = null;

		for (LigneFacture ligne : lignesFacture) {
			if (ligne.getProduit().equals(produit)) {
				tmp = ligne;
			}
		}

		if (tmp != null) {
			lignesFacture.remove(tmp);
		}

		calculerPrix();
	}

	/**
	 * Calcul le sous-total
	 */
	private void calculerPrix() {
		// Rénitialisation des prix
		sousTotal = 0;
		montantTaxes = 0;
		montantTotal = 0;

		// Calcul des prix
		for (LigneFacture ligne : lignesFacture) {
			sousTotal += ligne.getPrix();
			montantTaxes = sousTotal * pourcentageTaxes;
			montantTotal = sousTotal + montantTaxes;
		}

		// Association du nouveau prix au propriétés
		sousTotalProperty.set(cf.format(sousTotal));
		taxesProperty.set(cf.format(montantTaxes));
		totalProperty.set(cf.format(montantTotal));
	}

	/**
	 * 
	 * @return la liste observable des lignes de la facture
	 */
	public ObservableList<LigneFacture> getLignesFacture() {
		return this.lignesFacture;
	}

	/**
	 * @return le pourcentage de taxe
	 */
	public float getPourcentageTaxes() {
		return pourcentageTaxes;
	}

	/**
	 * set le pourcentage de taxe
	 * 
	 * @param pourcentageTaxes - le nouveau pourcentage de taxe
	 */
	public void setPourcentageTaxes(float pourcentageTaxes) {
		Transaction.pourcentageTaxes = pourcentageTaxes;
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
	 * @return la propriété correspondant au sous-total
	 */
	public StringProperty sousTotalProperty() {
		return sousTotalProperty;
	}

	/**
	 * @return la propriété correspondant au taxes
	 */
	public StringProperty taxesProperty() {
		return taxesProperty;
	}

	/**
	 * @return la propriété correspondant au total
	 */
	public StringProperty totalProperty() {
		return totalProperty;
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

	/**
	 * Méthode qui update l'array de lignes de facture et qui envoie l'objet dans
	 * l'ObjectOutputStream. Le oos.reset() est nécessaire pour s'assurer que
	 * l'objet soit bien renvoyé puisqu'il a été updaté.
	 * 
	 * @param oos l'output stream où on envoie l'objet
	 */
	public void serialize(ObjectOutputStream oos) {

		if (lignesFacture != null)
			ligneFactureArray = new ArrayList<LigneFacture>(lignesFacture);

		try {
			oos.reset();
			oos.writeObject(this);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Modifie l'état de la transaction 
	 * 
	 * @param pEtat La nouvelle état de la transaction
	 */
	public void setEtat(EtatTransaction pEtat) {
		this.etat = pEtat;
	}

	/**
	 * Retourne l'état de la transaction
	 * 
	 * @return L'État de la transaction
	 */
	public EtatTransaction getEtat() {
		return etat;
	}

	/**
	 * Permet d'effacer l'établissement de la transaction pour la stocker dans la
	 * base de données
	 */
	public void effacerEtablissement() {
		this.etablissement = null;
	}

	/**
	 * Méthode permettant de réduire la transaction pour la stocker dans la base de
	 * données des clients
	 * 
	 * @param trans - La transaction à réduire
	 * @return la transaction réduite
	 */
	public static Transaction reduireTransactionClient(Transaction trans) {
		Transaction transReduite = new Transaction();
		transReduite.setEtat(trans.getEtat());
		transReduite.setHeure(trans.getHeure());
		transReduite.setLigneFactureArray(null);
		transReduite.setSousTotal(trans.getSousTotal());
		transReduite.setMontantTaxes(trans.getMontantTaxes());
		transReduite.setMontantTotal(trans.getMontantTotal());
		transReduite.setNomEtablissement(trans.getNomEtablissement());
		transReduite.setNumero(trans.getNumero());
		transReduite.setProduits(null);

		return transReduite;
	}

	/**
	 * Méthode permettant de réduire la transaction pour la stocker dans la base de
	 * données des établissements
	 * 
	 * @param trans - La transaction à réduire
	 * @return la transaction réduite
	 */
	public static Transaction reduireTransactionEtablissement(Transaction trans) {
		Transaction transReduite = new Transaction();
		transReduite.setEtat(trans.getEtat());
		transReduite.setHeure(trans.getHeure());
		transReduite.setLigneFactureArray(trans.getLigneFactureArray());

		for (LigneFacture ligne : transReduite.getLigneFactureArray()) {
			ligne.getProduit().effacerImage();
		}

		transReduite.setSousTotal(trans.getSousTotal());
		transReduite.setMontantTaxes(trans.getMontantTaxes());
		transReduite.setMontantTotal(trans.getMontantTotal());
		transReduite.setNomEtablissement(trans.getNomEtablissement());
		transReduite.setNumero(trans.getNumero());
		transReduite.setProduits(null);

		return transReduite;
	}
}
