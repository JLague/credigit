package terminal.application;

import terminal.ctrl.TerminalControleur;
import javafx.application.Application;
import javafx.stage.Stage;

public class TerminalApplication extends Application {

	private TerminalControleur ctrl;
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
		this.stage.sizeToScene();
		this.stage.setResizable(true);
		this.stage.show();
	}
}
