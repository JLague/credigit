package terminal.vue;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import terminal.ctrl.TerminalControleur;

public class TerminalControleurVue {

	private Pane root;
	private Scene scene;
	private TerminalControleur ctrl;

	public TerminalControleurVue(TerminalControleur ctrl) {
		this.ctrl = ctrl;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("vueTerminal.fxml"));
		loader.setController(this);
		
		try {
			root = loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		scene = new Scene(root);
	}

	public Scene getScene() {
		return scene;
	}

}
