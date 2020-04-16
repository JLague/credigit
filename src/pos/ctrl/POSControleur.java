package pos.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.util.Arrays;

import commun.DataProduit;
import commun.DataVendeur;
import commun.Etablissement;
import commun.EtatTransaction;
import commun.LigneFacture;
import commun.Produit;
import commun.TableauDeBord;
import commun.Transaction;
import commun.Vendeur;
import commun.exception.ExceptionCreationCompte;
import commun.exception.ExceptionProduitEtablissement;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import pos.application.POSApplication;
import pos.modele.ClientPOS;
import pos.modele.ConnexionPOS;
import pos.vue.POSControleurVue;

/**
 * 
 * Classe servant de contrôleur à l'application POS
 * 
 * @author Bank-era Corp.
 *
 */
public class POSControleur implements IPOSControleur {

	/**
	 * Objet de l'application. On s'en sert pour changer la scène
	 */
	private POSApplication app;

	/**
	 * Tableau de bord de l'application
	 */
	private TableauDeBord tb;

	/**
	 * Objet permettant la connexion à la base de données
	 */
	private ConnexionPOS connexion;

	/**
	 * Le contrôleur de la vue du POS
	 */
	private POSControleurVue vue;

	/**
	 * Client qui communique avec le server (temrinal)
	 */
	private ClientPOS clientPOS;

	/**
	 * Constructeur servant à instantier un contrôleur du POS
	 * 
	 * @param posApplication l'application du POS
	 */
	public POSControleur(POSApplication posApplication) {
		this.app = posApplication;
		this.tb = new TableauDeBord();
		this.vue = new POSControleurVue(this);
		this.connexion = new ConnexionPOS();

		clientPOS = new ClientPOS();
	}

	@Override
	public void chargerScene(Scene scene, String title, boolean fullscreen) {
		app.chargerScene(scene, title, fullscreen);
	}

	@Override
	public Scene getScene() {
		return vue.getScene();
	}

	@Override
	public void enleverProduit(Produit produit) {
		tb.enleverProduit(produit);
	}

	@Override
	public boolean creerProduit(DataProduit data) throws ExceptionProduitEtablissement {
		return connexion.ajouterProduit(data);
	}

	@Override
	public boolean connexion(String username, String password, String nomEtablissement)
			throws ExceptionProduitEtablissement {
		Vendeur vendeur = connexion.connecter(username, password, nomEtablissement);

		if (vendeur != null) {
			tb.setVendeur(vendeur);

			Etablissement e = connexion.getEtablissement();
			tb.setEtablissement(e);

			// Instancie les transaction de l'établissement s'il n'y en avait pas
			if (e.getTransactions() == null) {
				e.setTransactions(new ArrayList<Transaction>());
			}
		}

		return vendeur != null;
	}

	@Override
	public void paiementEmpreinte() {
		tb.getTransaction().setEtat(EtatTransaction.EMPREINTE);
		this.transferTerminal();
		try {
			Transaction transRetour = clientPOS.retourTrans();
			if (transRetour.getEtat() == EtatTransaction.CONFIRMATION) {

				// Ajoute la transaction à l'établissement
				Etablissement etablissement = tb.getEtablissement();
				Transaction transReduite = Transaction.reduireTransactionEtablissement(transRetour);
				etablissement.ajouterTransaction(transReduite);
				connexion.updateEtablissement();

				// Crée une nouvelle transaction
				vue.createNewTransaction();

			} else {
				transRetour.setEtat(EtatTransaction.SCAN);
				tb.setTransaction(transRetour);
			}

			transferTerminal();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<StringProperty> getPrixProperties() {
		return tb.getProperties();
	}

	@Override
	public ObservableList<LigneFacture> getLignesFacture() {
		return tb.getLignesFacture();
	}

	@Override
	public void creerNouvelleTransaction() {
		tb.creerNouvelleTransaction();

	}

	@Override
	public void ajouterProduitATransaction(Produit produit) {
		tb.addProduitATransaction(produit);
	}

	@Override
	public void ajouterProduitsATransaction(List<Produit> produits) {
		tb.addProduitsATransaction(produits);

	}

	@Override
	public List<Produit> getInventaire() {
		return tb.getInventaire();
	}

	@Override
	public ArrayList<Produit> search(String text) {
		return tb.search(text);
	}

	@Override
	public void creerVendeur(DataVendeur data) throws ExceptionCreationCompte, ExceptionProduitEtablissement {
		connexion.ajouterCompteVendeur(data);
	}

	@Override
	public String getNomVendeur() {
		return tb.getNomVendeur();
	}

	@Override
	public long getNumeroEtablissement(String nom) throws ExceptionProduitEtablissement {
		return connexion.getNumeroEtablissement(nom);
	}

	@Override
	public void updateEtablissement() {
		connexion.updateEtablissement();
	}

	@Override
	public void modifierProduit(Produit ancien, Produit nouveau) throws ExceptionProduitEtablissement {
		if (!Arrays.areEqual(ancien.getImage(), nouveau.getImage()) || ancien.getSku() != nouveau.getSku()) {
			connexion.removeImageFromDatabase(ancien);

			nouveau.setImagePathDB(connexion.uploadImageToDatabase(nouveau));
		} else {
			nouveau.setImagePathDB(ancien.getImagePathDB());
		}
		
		tb.getEtablissement().modifierProduit(ancien, nouveau);

		connexion.updateEtablissement();
	}

	@Override
	public void supprimerProduit(Produit produit) {
		getInventaire().remove(produit);
		connexion.removeImageFromDatabase(produit);
		connexion.updateEtablissement();
	}

	@Override
	public void transferTerminal() {
		tb.getTransaction().setNomEtablissement(tb.getEtablissement().getNom());
		clientPOS.send(tb.getTransaction());
	}

}
