package inscription.application;

import inscription.controleur.InscriptionCtrl;
import javafx.application.Application;
import javafx.stage.Stage;

public class InscriptionApp extends Application {

	private InscriptionCtrl ctrl;

	@Override
	public void start(Stage stage) throws Exception {
		ctrl = new InscriptionCtrl();

		stage.setTitle("Credigit/Inscription");
		stage.setScene(ctrl.getScene());
		stage.setResizable(false);
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
