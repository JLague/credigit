package statistiques.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import statistiques.ctrl.TBControleur;

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

	@Override
	public void chargerScene(Scene scene, String title, boolean fullscreen) {
		stage.setScene(scene);
		stage.setTitle(title);
		stage.setFullScreenExitHint("");
		stage.setResizable(true);
		stage.setFullScreen(fullscreen);
	}
}
