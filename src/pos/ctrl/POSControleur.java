package pos.ctrl;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.stage.Stage;
import pos.application.MainPOSApplication;
import pos.vue.LoginPOSControleurVue;

public class POSControleur implements IPOSControleur {

	private LoginPOSControleurVue vue;
	
	public POSControleur() throws IOException {
		vue = new LoginPOSControleurVue(this);
	}
	
	@Override
	public Scene getScene() {
		return vue.getScene();
	}
	
	public void launchMainView()
	{
		try {
			new MainPOSApplication().start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
