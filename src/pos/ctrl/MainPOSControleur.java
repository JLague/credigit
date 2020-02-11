package pos.ctrl;

import java.io.IOException;

import javafx.scene.Scene;
import pos.vue.MainPOSControleurVue;

public class MainPOSControleur implements IPOSControleur {

	private MainPOSControleurVue vue;
	
	public MainPOSControleur() throws IOException {
		vue = new MainPOSControleurVue();
	}
	
	@Override
	public Scene getScene() {
		return vue.getScene();
	}

}
