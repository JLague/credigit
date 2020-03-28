package terminal.ctrl;

import javafx.scene.Scene;

import java.io.IOException;

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

		try {
			serveur = new ServeurTerminal(this);
			serveur.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

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
