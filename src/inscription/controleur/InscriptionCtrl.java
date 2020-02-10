package inscription.controleur;

import inscription.vue.InscriptionVueCtrl;
import javafx.scene.Scene;

public class InscriptionCtrl {

	private InscriptionVueCtrl vue;

	public InscriptionCtrl() {
		vue = new InscriptionVueCtrl(this);
	}

	public Scene getScene() {
		return vue.getScene();
	}
}
