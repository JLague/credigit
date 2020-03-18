package inscription.application;

import inscription.controleur.InscriptionCtrl;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Application de départ de l'inscription
 * 
 * @author Bank-era Corp.
 *
 */
public class InscriptionApp extends Application {

	/**
	 * Contrôleur principal de l'inscription
	 */
	private InscriptionCtrl ctrl;

	@Override
	public void start(Stage stage) throws Exception {
		ctrl = new InscriptionCtrl();

		stage.setTitle("Credigit/Inscription");
		stage.setScene(ctrl.getScene());
		stage.setResizable(true);
		stage.setFullScreen(true);
		stage.getIcons()
				.add(new Image(getClass().getResource("/images/inscription/ic_empreinte.png").toExternalForm()));
		stage.show();
	}

	/**
	 * Démarre l'application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch();
	}
}
