package terminal.ctrl;

import javafx.scene.Scene;
import commun.*;
import terminal.application.TerminalApplication;
import terminal.vue.TerminalControleurVue;

public class TerminalControleur {

	/**
	 * Tableau de bord de l'application
	 */
	private TableauDeBord tb;

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
		tb = new TableauDeBord();
		tb.setTransaction(new Transaction());
		trans = tb.getTransaction();

		actualiser();

	}

	public Scene getScene() {
		return vue.getScene();
	}

	public void actualiser() {
		vue.actualiser(trans);
	}
}
