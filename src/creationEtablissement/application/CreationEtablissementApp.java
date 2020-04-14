package creationEtablissement.application;

import creationEtablissement.controleur.CreationEtablissementCtrl;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Classe permettant de lancer l'application de création d'établissement.
 * 
 * @author Bank-era Corp.
 *
 */
public class CreationEtablissementApp extends Application {

	/**
	 * Contrôleur de l'application
	 */
	private CreationEtablissementCtrl ctrl;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		ctrl = new CreationEtablissementCtrl();
		stage.getIcons()
				.add(new Image(getClass().getResource("/images/etablissement/ic_empreinte.png").toExternalForm()));
		stage.setScene(ctrl.getScene());
		stage.setTitle("Création d'un établissement");
		stage.sizeToScene();
		stage.setResizable(true);
		stage.show();
	}

}
