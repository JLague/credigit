package terminal.ctrl;

import javafx.scene.Scene;
import terminal.application.TerminalApplication;
import terminal.vue.TerminalControleurVue;

public class TerminalControleur {
	
	private TerminalApplication app;
	private TerminalControleurVue vue;
	
	public TerminalControleur(TerminalApplication terminalApplication) {
		this.app = terminalApplication;
		vue = new TerminalControleurVue(this);
	}

	public Scene getScene() {
		return vue.getScene();
	}

}
