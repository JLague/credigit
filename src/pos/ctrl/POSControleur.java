package pos.ctrl;

import java.util.ArrayList;
import java.util.List;

import exception.ExceptionCreationCompte;
import exception.ExceptionProduitEtablissement;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import pos.application.POSApplication;
import pos.modele.DataVendeur;
import pos.modele.DataVue;
import pos.modele.LigneFacture;
import pos.modele.Produit;
import pos.modele.TableauDeBord;
import pos.vue.POSControleurVue;

public class POSControleur implements IPOSControleur {

	/**
	 * Objet de l'application. On s'en sert pour changer la scène
	 */
	private POSApplication app;

	private TableauDeBord tb;

	/**
	 * Le contrôleur de la vue du POS
	 */
	private POSControleurVue vue;

	/**
	 * Constructeur servant à instantier un contrôleur du POS
	 * 
	 * @param posApplication l'application du POS
	 */
	public POSControleur(POSApplication posApplication) {
		this.app = posApplication;
		this.tb = new TableauDeBord();
		this.vue = new POSControleurVue(this);
	}

	/**
	 * Load la vue principale du POS à l'aide de l'application
	 */
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
	public void produitSelectionne(int ligne, int colonne) {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputEntree(String input) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean creerProduit(DataVue data) throws ExceptionProduitEtablissement {
		return tb.ajouterProduit(new Produit(data));

	}

	@Override
	public boolean connexion(String username, String password) {
		return tb.connecter(username, password);
	}

	@Override
	public void paiementEmpreinte() {
		// TODO Auto-generated method stub

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
	public List<Produit> getListeProduits() {
		return tb.getListeProduits();
	}

	@Override
	public Produit getProduitFromString(String nom) {
		return tb.getProduitFromString(nom);
	}

	public ArrayList<Produit> search(String text) {
		return tb.search(text);
	}

	public void creerVendeur(DataVendeur data) throws ExceptionCreationCompte, ExceptionProduitEtablissement {
		tb.creerNouveauVendeur(data);
	}

	/**
	 * @return le nom du vendeur ou null si personne n'est connecté
	 */
	public String getNomVendeur() {
		return tb.getNomVendeur();
	}
}
