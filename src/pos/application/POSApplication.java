package pos.application;

import javafx.application.Application;
import javafx.stage.Stage;
import pos.ctrl.POSControleur;

public class POSApplication extends Application{
	
	private POSControleur ctrl;
	private Stage stage;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		ctrl = new POSControleur(this);
		stage.setScene(ctrl.getScene());
		stage.setTitle("Connexion");
		stage.setFullScreen(true);
		//stage.sizeToScene();
		stage.show();
	}
	
	/**
	 * Permet de changer la scène liée à l'application
	 * 
	 * @param scene la nouvelle scène
	 * @param title le nouveau titre de l'application
	 */
	public void chargerVuePOS()
	{
		stage.setScene(ctrl.getScene());
		stage.setTitle("POS");
		stage.setFullScreen(true);
	}
}
