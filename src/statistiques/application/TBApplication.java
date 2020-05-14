package statistiques.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import statistiques.ctrl.TBControleur;

/**
 * Classe permettant de lancer l'application du tableau de statistiques
 * 
 * @author Bank-era Corp.
 *
 */
public class TBApplication extends Application {

	/**
	 * Le contrôleur principal du POS
	 */
	private TBControleur ctrl;

	/**
	 * La fenêtre de l'application
	 */
	private Stage stage;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		ctrl = new TBControleur(this);
		stage.setScene(ctrl.getScene());
		stage.setTitle("Connexion");
		stage.sizeToScene();
		stage.setResizable(true);
		stage.getIcons().add(new Image(getClass().getResource("/images/pos/ic_empreinte.png").toExternalForm()));
		stage.show();
	}

	/**
	 * Méthode permettant de charger une nouvelle scène dans le stage
	 * 
	 * @param scene      - La nouvelle scène à charger
	 * @param title      - Le titre de la scène
	 * @param fullscreen - Si elle est en fullscreen ou on
	 */
	public void chargerScene(Scene scene, String title, boolean fullscreen) {
		stage.setScene(scene);
		stage.setTitle(title);
		stage.setFullScreenExitHint("");
		stage.setResizable(true);
		stage.setFullScreen(fullscreen);
	}
}
