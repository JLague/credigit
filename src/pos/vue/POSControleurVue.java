package pos.vue;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class POSControleurVue implements IPOSControleurVue {

	private VBox root;

	private Scene scene;

	public POSControleurVue() throws IOException {
		//System.out.println(getClass().getResource("POS.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("POS.fxml"));
		root = loader.load();
		loader.setController(this);
		scene = new Scene(root);
		
		scene.getStylesheets().add("styles/POS.css");
		root.getStyleClass().add("view1");

	}

	@Override
	public Scene getScene() {
		return this.scene;
	}

}
