package pos.application;

import javafx.application.Application;
import javafx.stage.Stage;
import pos.ctrl.POSControleur;

public class POSApplication extends Application{
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		POSControleur ctrl = new POSControleur();
		stage.setScene(ctrl.getScene());
		stage.setTitle("Connexion");
		//stage.setFullScreen(true);
		stage.sizeToScene();
		stage.show();
		
	}
}
