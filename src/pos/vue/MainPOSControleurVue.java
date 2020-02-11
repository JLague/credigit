package pos.vue;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class MainPOSControleurVue implements IPOSControleurVue {
	
	private Scene scene;
	
	private BorderPane root;
	
	/**
	 * Constructeur par défaut qui instantie la vue
	 * 
	 * @throws IOException
	 */
	public MainPOSControleurVue() throws IOException {
		// Load first view
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPOS.fxml"));
		loader.setController(this);
		root = loader.load();
		scene = new Scene(root);
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}
}
