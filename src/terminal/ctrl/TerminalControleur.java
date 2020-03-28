package terminal.ctrl;

import javafx.scene.Scene;
import commun.*;
import terminal.application.TerminalApplication;
import terminal.modele.ServeurTerminal;
import terminal.vue.TerminalControleurVue;

public class TerminalControleur {

	/**
	 * Tableau de bord de l'application
	 */
	private TableauDeBord tb;

	/**
	 * Vue associé à ce controleur
	 */
	private TerminalControleurVue vue;

	/**
	 * S'occupe d'établir et d'intéragir avec le terminal
	 */
	private ServeurTerminal serveur;

	public TerminalControleur(TerminalApplication terminalApplication) {
		vue = new TerminalControleurVue(this);
		tb = new TableauDeBord();

		serveur = new ServeurTerminal(this);

	}

	public void updateTransaction() {
		tb.setTransaction(Transaction.deserialize());
		actualiser();
	}

	public Scene getScene() {
		return vue.getScene();
	}

	public void actualiser() {
		vue.actualiser(tb.getTransaction());
	}
}
