package pos.ctrl;

import javafx.scene.Scene;
import pos.application.POSApplication;
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
	 * @param app l'application du POS
	 */
	public POSControleur(POSApplication app) {
		this.app = app;
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
}
