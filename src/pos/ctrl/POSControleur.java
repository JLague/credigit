package pos.ctrl;

import java.util.List;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import pos.application.POSApplication;
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
	public void chargerScene(Scene scene, String title) {
		app.chargerScene(scene, title);
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
	public void creerProduit(DataVue data) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean connexion(String username, String password) {
		// TODO Auto-generated method stub
		return false;
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
}
