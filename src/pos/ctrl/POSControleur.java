package pos.ctrl;

import java.io.IOException;

import javafx.scene.Scene;
import pos.vue.POSControleurVue;

public class POSControleur implements IPOSControleur {

	private POSControleurVue vue;
	
	public POSControleur() throws IOException {
		vue = new POSControleurVue();
	}
	
	@Override
	public Scene getScene() {
		return vue.getScene();
	}

}
