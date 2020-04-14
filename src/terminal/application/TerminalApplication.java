package terminal.application;

import terminal.ctrl.TerminalControleur;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Classe permettant de démarrer le terminal
 * 
 * @author Bank-era Corp.
 *
 */
public class TerminalApplication extends Application {

	/**
	 * Le contrôleur du terminal
	 */
	private TerminalControleur ctrl;

	/**
	 * La vue
	 */
	private Stage stage;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		ctrl = new TerminalControleur(this);
		this.stage.setScene(ctrl.getScene());
		this.stage.setTitle("Terminal");
		stage.setFullScreenExitHint("");
		this.stage.sizeToScene();
		this.stage.setFullScreen(true);
		this.stage.setResizable(true);
		this.stage.show();
	}
}
