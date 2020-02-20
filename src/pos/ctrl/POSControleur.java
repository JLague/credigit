package pos.ctrl;

import javafx.scene.Scene;
import pos.application.POSApplication;
import pos.modele.DataVue;
import pos.vue.POSControleurVue;

public class POSControleur implements IPOSControleur {

	/**
	 * Objet de l'application. On s'en sert pour changer la scène
	 */
	private POSApplication app;

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
	public void annulerItem() {
		// TODO Auto-generated method stub

	}

	@Override
	public void annulerCommande() {
		// TODO Auto-generated method stub

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
}
