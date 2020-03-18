package creationEtablissement.application;

import creationEtablissement.controleur.CreationEtablissementCtrl;
import javafx.application.Application;
import javafx.stage.Stage;


public class CreationEtablissementApp extends Application {
	
	private CreationEtablissementCtrl ctrl;
	
	private Stage stage;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		ctrl = new CreationEtablissementCtrl();
		stage.setScene(ctrl.getScene());
		stage.setTitle("Création d'un établissement");
		stage.sizeToScene();
		stage.setResizable(true);
		stage.show();
	}

}
