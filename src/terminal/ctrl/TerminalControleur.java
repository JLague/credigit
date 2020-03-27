package terminal.ctrl;

import javafx.scene.Scene;
import commun.*;
import terminal.application.TerminalApplication;
import terminal.utils.EmpreinteUtil;
import terminal.vue.TerminalControleurVue;

public class TerminalControleur {

	/**
	 * Transaction courrante
	 */
	private Transaction trans;

	/**
	 * Vue associé à ce controleur
	 */
	private TerminalControleurVue vue;

	public TerminalControleur(TerminalApplication terminalApplication) {
		vue = new TerminalControleurVue(this);
		trans = new Transaction();
		actualiser();

	}

	public Scene getScene() {
		return vue.getScene();
	}

	public void actualiser() {
		vue.actualiser(trans);
	}

	public void effectuerTransaction() {
		byte[] empreinte = EmpreinteUtil.getEmpreinte();
	}
}
