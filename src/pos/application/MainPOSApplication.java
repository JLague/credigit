package pos.application;

import javafx.application.Application;
import javafx.stage.Stage;
import pos.ctrl.MainPOSControleur;

public class MainPOSApplication extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		MainPOSControleur ctrl = new MainPOSControleur();
		stage.setScene(ctrl.getScene());
		stage.setTitle("POS");
		stage.setFullScreen(true);
		//stage.sizeToScene();
		stage.show();		
	}

}
