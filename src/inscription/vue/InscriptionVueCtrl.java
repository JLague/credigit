package inscription.vue;

import java.io.IOException;

import inscription.controleur.InscriptionCtrl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class InscriptionVueCtrl {

	@FXML
	private ScrollPane root;

	private InscriptionCtrl ctrl;
	private Scene scene;

	public InscriptionVueCtrl(InscriptionCtrl ctrl) {
		this.ctrl = ctrl;

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("InscriptionVue.fxml"));

			loader.setController(this);

			root = loader.load();

			scene = new Scene(root);

		} catch (IOException e) {
			System.err.println("Erreur de chargement du fxml!");
			e.printStackTrace();
		}
	}

	public Scene getScene() {
		return scene;
	}

}
